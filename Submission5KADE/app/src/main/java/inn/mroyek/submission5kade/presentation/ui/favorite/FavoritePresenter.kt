package inn.mroyek.submission5kade.presentation.ui.favorite

import inn.mroyek.submission5kade.presentation.base.BasePresenter
import inn.mroyek.submission5kade.presentation.base.DbApplication
import inn.mroyek.submission5kade.data.local.FavoriteMatchEntity
import inn.mroyek.submission5kade.data.local.FavoriteTeamsEntity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavoritePresenter : BasePresenter<FavoriteContract>() {
    fun getFavoriteMatch() {
        DbApplication.database?.use {
            val result = select(FavoriteMatchEntity.TABLE_FAV)
                .exec { parseList(classParser<FavoriteMatchEntity>()) }
            val listMatchFav = result.map {
                it.transform()
            }
            return@use view?.showFavorites(listMatchFav)
        }
    }
    fun getFavoriteTeams(){
        DbApplication.database?.use {
            val result = select(FavoriteTeamsEntity.TABLE_FAV_TEAM)
                .exec { parseList(classParser<FavoriteTeamsEntity>()) }
            val listTeamFav = result.map {
                it.transform()
            }
            return@use view?.showTeamFavorite(listTeamFav)
        }
    }
}