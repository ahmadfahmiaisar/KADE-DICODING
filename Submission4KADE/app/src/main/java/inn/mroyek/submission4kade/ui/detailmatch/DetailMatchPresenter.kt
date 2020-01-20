package inn.mroyek.submission4kade.ui.detailmatch

import android.database.sqlite.SQLiteConstraintException
import inn.mroyek.submission4kade.base.BasePresenter
import inn.mroyek.submission4kade.common.logD
import inn.mroyek.submission4kade.base.DbApplication
import inn.mroyek.submission4kade.db.FavoriteEntity
import inn.mroyek.submission4kade.model.pojo.Matchs
import inn.mroyek.submission4kade.network.ApiRepository
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
                insert(FavoriteEntity.TABLE_FAV,
                    FavoriteEntity.ID_MATCH to match.eventId,
                    FavoriteEntity.ID_HOME_TEAM to match.homeTeamId,
                    FavoriteEntity.ID_AWAY_TEAM to match.awayTeamId,
                    FavoriteEntity.NAME_HOME_TEAM to match.homeTeamName,
                    FavoriteEntity.NAME_AWAY_TEAM to match.awayTeamName,
                    FavoriteEntity.SCORE_HOME to match.homeScore,
                    FavoriteEntity.SCORE_AWAY to match.awayScore,
                    FavoriteEntity.DATE_MATCH to match.matchDate
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
                    FavoriteEntity.TABLE_FAV,
                    "(${FavoriteEntity.ID_MATCH} = {idMatch})",
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
            val result = select(FavoriteEntity.TABLE_FAV, FavoriteEntity.ID_MATCH)
                .whereArgs(
                    FavoriteEntity.ID_MATCH + " = {${FavoriteEntity.ID_MATCH}}",
                    FavoriteEntity.ID_MATCH to idMatch
                )
                .limit(1)
                .exec { parseList(classParser<String>()) }
            return@use callback?.saveExisFavorite(result.isNotEmpty())
        }
    }
}