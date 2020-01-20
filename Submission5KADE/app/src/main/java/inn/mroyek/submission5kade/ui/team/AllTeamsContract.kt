package inn.mroyek.submission5kade.ui.team

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.pojo.AllTeams
import inn.mroyek.submission5kade.model.response.TeamModel

interface AllTeamsContract : BaseServiceCallBack {
    fun getTeams(listTeams: List<AllTeams?>)
}