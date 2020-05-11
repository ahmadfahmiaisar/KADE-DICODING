package inn.mroyek.submission5kade.presentation.ui.detailmatch

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.data.remote.model.MatchModel
import inn.mroyek.submission5kade.data.remote.model.TeamModel

interface DetailMatchContract : BaseServiceCallBack {
    fun showProgress(show: Boolean)
    fun showDetailMatch(match: MatchModel?)
    fun showTeamHome(team: TeamModel?)
    fun showTeamAway(team: TeamModel?)
    fun onFail(msg: String)
    //favorite
    fun saveFavorite()
    fun removeFavorite()
    fun saveExisFavorite(saved: Boolean)
}