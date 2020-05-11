package inn.mroyek.submission5kade.presentation.ui.searchTeam

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.presentation.model.AllTeams

interface SearchTeamContract : BaseServiceCallBack {
    fun showTeam(listTeam: MutableList<AllTeams>)
}