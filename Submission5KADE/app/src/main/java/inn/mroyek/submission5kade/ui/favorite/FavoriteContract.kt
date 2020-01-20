package inn.mroyek.submission5kade.ui.favorite

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.pojo.Matchs

interface FavoriteContract : BaseServiceCallBack {
    fun showFavorites(listFavorite: List<Matchs>)
}