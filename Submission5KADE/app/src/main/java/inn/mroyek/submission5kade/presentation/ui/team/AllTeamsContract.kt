package inn.mroyek.submission5kade.presentation.ui.team

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.presentation.model.AllTeams

interface AllTeamsContract : BaseServiceCallBack {
    fun getTeams(listTeams: List<AllTeams?>)
}