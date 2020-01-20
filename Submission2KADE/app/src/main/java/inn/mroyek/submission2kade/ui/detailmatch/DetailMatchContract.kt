package inn.mroyek.submission2kade.ui.detailmatch

import inn.mroyek.submission2kade.base.BaseServiceCallBack
import inn.mroyek.submission2kade.model.response.MatchModel
import inn.mroyek.submission2kade.model.response.TeamModel

interface DetailMatchContract : BaseServiceCallBack {
    fun showDetailMatch(match: MatchModel?)
    fun showTeamHome(team: TeamModel?)
    fun showTeamAway(team: TeamModel?)
    fun onFail(msg: String)
}