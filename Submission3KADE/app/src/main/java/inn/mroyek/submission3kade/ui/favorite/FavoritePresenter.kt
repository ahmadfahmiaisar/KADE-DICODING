package inn.mroyek.submission3kade.ui.favorite

import inn.mroyek.submission3kade.base.BasePresenter
import inn.mroyek.submission3kade.base.DbApplication
import inn.mroyek.submission3kade.db.FavoriteEntity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavoritePresenter : BasePresenter<FavoriteContract>() {
    fun getFavoriteMatch() {
        DbApplication.database?.use {
            val result = select(FavoriteEntity.TABLE_FAV)
                .exec { parseList(classParser<FavoriteEntity>()) }
            val listMatchFav = result.map {
                it.transform()
            }
            return@use callback?.showFavorites(listMatchFav)
        }
    }
}