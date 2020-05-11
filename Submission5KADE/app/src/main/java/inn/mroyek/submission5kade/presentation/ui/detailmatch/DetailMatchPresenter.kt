package inn.mroyek.submission5kade.presentation.ui.detailmatch

import android.database.sqlite.SQLiteConstraintException
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import inn.mroyek.submission5kade.presentation.base.DbApplication
import inn.mroyek.submission5kade.data.local.FavoriteMatchEntity
import inn.mroyek.submission5kade.data.remote.repository.match.MatchRepository
import inn.mroyek.submission5kade.presentation.model.Matchs
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.*
import java.sql.SQLClientInfoException
import javax.inject.Inject

class DetailMatchPresenter @Inject constructor(
    private val repository: MatchRepository
) : BasePresenter<DetailMatchContract>() {

    fun getDetailMatch(idMatchEvent: String?) {
        view?.showProgress(true)
        disposable.add(
            repository.getDetailMatch(idMatch = idMatchEvent!!)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                .doOnComplete{ view?.showProgress(false) }
                ?.subscribe({
                    view?.showDetailMatch(it?.get(0))
                }, {
                    view?.onFail(it.message!!)
                })!!
        )
    }

    fun getDetailTeam(idTeamHome: String?, idTeamAway: String?) {
        getTeams(idTeamHome = idTeamHome)
        getTeams(idTeamAway = idTeamAway)
    }

    private fun getTeams(idTeamHome: String? = null, idTeamAway: String? = null) {
        val idTeam = idTeamHome ?: idTeamAway
        disposable.add(
            repository.getDetailTeam(idTeam)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ listItem ->
                    if (listItem?.isNotEmpty()!!) {
                        idTeamHome?.let {
                            view?.showTeamHome(listItem[0])
                        }
                        idTeamAway?.let {
                            view?.showTeamAway(listItem[0])
                        }
                    } else {
                        view?.onFail("gak ada $idTeam")
                    }
                }, {
                    view?.onFail(it.message!!)
                })!!
        )
    }

    fun addFavorite(match: Matchs) {
        try {
            DbApplication.database?.use {
                insert(
                    FavoriteMatchEntity.TABLE_FAV,
                    FavoriteMatchEntity.ID_MATCH to match.eventId,
                    FavoriteMatchEntity.ID_HOME_TEAM to match.homeTeamId,
                    FavoriteMatchEntity.ID_AWAY_TEAM to match.awayTeamId,
                    FavoriteMatchEntity.NAME_HOME_TEAM to match.homeTeamName,
                    FavoriteMatchEntity.NAME_AWAY_TEAM to match.awayTeamName,
                    FavoriteMatchEntity.SCORE_HOME to match.homeScore,
                    FavoriteMatchEntity.SCORE_AWAY to match.awayScore,
                    FavoriteMatchEntity.DATE_MATCH to match.matchDate
                )
            }
            view?.saveFavorite()
        } catch (e: SQLClientInfoException) {
            view?.onFail(e.message!!)
        }
    }

    fun removeFavorite(idMatch: String) {
        try {
            DbApplication.database?.use {
                delete(
                    FavoriteMatchEntity.TABLE_FAV,
                    "(${FavoriteMatchEntity.ID_MATCH} = {idMatch})",
                    "idMatch" to idMatch
                )
            }
            view?.removeFavorite()
        } catch (e: SQLiteConstraintException) {
            view?.onFail(e.message!!)
        }
    }

    fun checkExisMatch(idMatch: String) {
        DbApplication.database?.use {
            val result = select(FavoriteMatchEntity.TABLE_FAV, FavoriteMatchEntity.ID_MATCH)
                .whereArgs(
                    FavoriteMatchEntity.ID_MATCH + " = {${FavoriteMatchEntity.ID_MATCH}}",
                    FavoriteMatchEntity.ID_MATCH to idMatch
                )
                .limit(1)
                .exec { parseList(classParser<String>()) }
            return@use view?.saveExisFavorite(result.isNotEmpty())
        }
    }
}