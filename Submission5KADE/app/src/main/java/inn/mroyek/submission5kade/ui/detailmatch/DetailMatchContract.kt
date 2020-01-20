package inn.mroyek.submission5kade.ui.detailmatch

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.response.MatchModel
import inn.mroyek.submission5kade.model.response.TeamModel

interface DetailMatchContract : BaseServiceCallBack {
    fun showDetailMatch(match: MatchModel?)
    fun showTeamHome(team: TeamModel?)
    fun showTeamAway(team: TeamModel?)
    fun onFail(msg: String)
    //favorite
    fun saveFavorite()
    fun removeFavorite()
    fun saveExisFavorite(saved: Boolean)
}