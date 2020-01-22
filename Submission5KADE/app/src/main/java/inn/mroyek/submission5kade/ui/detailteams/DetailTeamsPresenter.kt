package inn.mroyek.submission5kade.ui.detailteams

import android.database.sqlite.SQLiteConstraintException
import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.base.DbApplication
import inn.mroyek.submission5kade.db.FavoriteTeamsEntity
import inn.mroyek.submission5kade.model.pojo.AllTeams
import inn.mroyek.submission5kade.network.ApiRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.*
import java.sql.SQLClientInfoException

class DetailTeamsPresenter (
    private val repository: ApiRepository,
    private val backgroundScheduler: Scheduler = Schedulers.io(),
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : BasePresenter<DetailTeamsContract>(){
    fun getTeam(idTeam: String?){
        disposable.add(
            repository.getDetailTeam(idTeam)
                ?.subscribeOn(backgroundScheduler)
                ?.observeOn(mainScheduler)
                ?.subscribe({team ->
                    team.let {
                        callback?.showDetailTeams(team!![0])
                    }
//                    callback?.showDetailTeams()
                }, {
                    callback?.onFail(it.message!!)
                })!!
        )
    }
    fun addFavoriteTeam(team: AllTeams){
        try {
            DbApplication.database?.use {
                insert(
                    FavoriteTeamsEntity.TABLE_FAV_TEAM,
                    FavoriteTeamsEntity.ID_TEAM to team.idTeam.toString(),
                    FavoriteTeamsEntity.LEAGUE to team.strLeague,
                    FavoriteTeamsEntity.TEAM to team.strTeam,
                    FavoriteTeamsEntity.BADGE to team.strTeamBadge
                )
            }
            callback?.saveFavorite()
        } catch (e: SQLClientInfoException){
            callback?.onFail(e.message!!)
        }
    }
    fun removeFavoriteTeam(idTeam: String){
        try {
            DbApplication.database?.use {
                delete(
                    FavoriteTeamsEntity.TABLE_FAV_TEAM,
                    "(${FavoriteTeamsEntity.ID_TEAM} = {idTeam})",
                    "idTeam" to idTeam
                )
            }
            callback?.removeFavorite()
        } catch (e: SQLiteConstraintException){
            callback?.onFail(e.message!!)
        }
    }

    fun checkExistTeam(idTeam: String){
        DbApplication.database?.use {
            val result = select(FavoriteTeamsEntity.TABLE_FAV_TEAM, FavoriteTeamsEntity.ID_TEAM)
                .whereArgs(
                    FavoriteTeamsEntity.ID_TEAM + " = {${FavoriteTeamsEntity.ID_TEAM}}",
                    FavoriteTeamsEntity.ID_TEAM to idTeam
                )
                .limit(1)
                .exec { parseList(classParser<String>()) }
            return@use callback?.saveExisFavorite(result.isNotEmpty())
        }
    }
}