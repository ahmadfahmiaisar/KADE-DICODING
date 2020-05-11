package inn.mroyek.submission5kade.presentation.ui.detailteams

import android.database.sqlite.SQLiteConstraintException
import inn.mroyek.submission5kade.presentation.base.BasePresenter
import inn.mroyek.submission5kade.presentation.base.DbApplication
import inn.mroyek.submission5kade.data.local.FavoriteTeamsEntity
import inn.mroyek.submission5kade.data.remote.repository.teams.TeamsRepository
import inn.mroyek.submission5kade.presentation.model.AllTeams
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.*
import java.sql.SQLClientInfoException
import javax.inject.Inject

class DetailTeamsPresenter @Inject constructor(
    private val repository: TeamsRepository
) : BasePresenter<DetailTeamsContract>(){
    fun getTeam(idTeam: String?){
        disposable.add(
            repository.getDetailTeam(idTeam!!)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({team ->
                    team.let {
                        view?.showDetailTeams(team!![0])
                    }
//                    callback?.showDetailTeams()
                }, {
                    view?.onFail(it.message!!)
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
            view?.saveFavorite()
        } catch (e: SQLClientInfoException){
            view?.onFail(e.message!!)
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
            view?.removeFavorite()
        } catch (e: SQLiteConstraintException){
            view?.onFail(e.message!!)
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
            return@use view?.saveExisFavorite(result.isNotEmpty())
        }
    }
}