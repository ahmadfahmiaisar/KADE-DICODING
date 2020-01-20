package inn.mroyek.submission4kade.ui.favorite

import inn.mroyek.submission4kade.base.BaseServiceCallBack
import inn.mroyek.submission4kade.model.pojo.Matchs

interface FavoriteContract : BaseServiceCallBack {
    fun showFavorites(listFavorite: List<Matchs>)
}