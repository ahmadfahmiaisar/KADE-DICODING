package inn.mroyek.submission5kade.ui.detailmatch

import android.database.sqlite.SQLiteConstraintException
import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.base.DbApplication
import inn.mroyek.submission5kade.db.FavoriteMatchEntity
import inn.mroyek.submission5kade.model.pojo.Matchs
import inn.mroyek.submission5kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.*
import java.sql.SQLClientInfoException

class DetailMatchPresenter(
    private val repository: ApiRepository,
    private val backroundScheduler: Scheduler = Schedulers.io(),
    private val mainSchedull: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<DetailMatchContract>() {

    fun getDetailMatch(idMatchEvent: String?) {
        disposable.add(
            repository.getDetailMatchs(idMatch = idMatchEvent)
                ?.subscribeOn(backroundScheduler)
                ?.observeOn(mainSchedull)
                ?.subscribe({
                    callback?.showDetailMatch(it?.get(0))
                }, {
                    callback?.onFail(it.message!!)
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
                ?.subscribeOn(backroundScheduler)
                ?.observeOn(mainSchedull)
                ?.subscribe({ listItem ->
                    if (listItem?.isNotEmpty()!!) {
                        idTeamHome?.let {
                            callback?.showTeamHome(listItem[0])
                        }
                        idTeamAway?.let {
                            callback?.showTeamAway(listItem[0])
                        }
                    } else {
                        callback?.onFail("gak ada $idTeam")
                    }
                }, {
                    callback?.onFail(it.message!!)
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
            callback?.saveFavorite()
        } catch (e: SQLClientInfoException) {
            callback?.onFail(e.message!!)
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
            callback?.removeFavorite()
        } catch (e: SQLiteConstraintException) {
            callback?.onFail(e.message!!)
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
            return@use callback?.saveExisFavorite(result.isNotEmpty())
        }
    }
}