package inn.mroyek.submission5kade.presentation.ui.favorite

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.presentation.model.AllTeams
import inn.mroyek.submission5kade.presentation.model.Matchs

interface FavoriteContract : BaseServiceCallBack {
    fun showFavorites(listFavorite: List<Matchs>)
    fun showTeamFavorite(listTeamFavorite: List<AllTeams>)
}