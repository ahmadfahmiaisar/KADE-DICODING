package inn.mroyek.submission5kade.ui.searchTeam

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.pojo.AllTeams

interface SearchTeamContract : BaseServiceCallBack {
    fun showTeam(listTeam: MutableList<AllTeams>)
}