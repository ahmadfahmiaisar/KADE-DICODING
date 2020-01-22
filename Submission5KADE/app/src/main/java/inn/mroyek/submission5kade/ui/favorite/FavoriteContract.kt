package inn.mroyek.submission5kade.ui.favorite

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.pojo.AllTeams
import inn.mroyek.submission5kade.model.pojo.Matchs
import inn.mroyek.submission5kade.model.response.TeamModel

interface FavoriteContract : BaseServiceCallBack {
    fun showFavorites(listFavorite: List<Matchs>)
    fun showTeamFavorite(listTeamFavorite: List<AllTeams>)
}