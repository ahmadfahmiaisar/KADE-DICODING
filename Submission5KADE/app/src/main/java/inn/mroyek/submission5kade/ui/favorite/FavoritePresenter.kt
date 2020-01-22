package inn.mroyek.submission5kade.ui.favorite

import inn.mroyek.submission5kade.base.BasePresenter
import inn.mroyek.submission5kade.base.DbApplication
import inn.mroyek.submission5kade.db.FavoriteMatchEntity
import inn.mroyek.submission5kade.db.FavoriteTeamsEntity
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
            return@use callback?.showFavorites(listMatchFav)
        }
    }
    fun getFavoriteTeams(){
        DbApplication.database?.use {
            val result = select(FavoriteTeamsEntity.TABLE_FAV_TEAM)
                .exec { parseList(classParser<FavoriteTeamsEntity>()) }
            val listTeamFav = result.map {
                it.transform()
            }
            return@use callback?.showTeamFavorite(listTeamFav)
        }
    }
}