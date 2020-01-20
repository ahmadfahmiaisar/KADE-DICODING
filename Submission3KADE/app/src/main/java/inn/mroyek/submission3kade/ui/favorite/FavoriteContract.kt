package inn.mroyek.submission3kade.ui.favorite

import inn.mroyek.submission3kade.base.BaseServiceCallBack
import inn.mroyek.submission3kade.model.pojo.Matchs

interface FavoriteContract : BaseServiceCallBack {
    fun showFavorites(listFavorite: List<Matchs>)
}