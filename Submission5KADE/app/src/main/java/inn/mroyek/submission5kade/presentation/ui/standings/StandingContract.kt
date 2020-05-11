package inn.mroyek.submission5kade.presentation.ui.standings

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.data.remote.model.Table

interface StandingContract : BaseServiceCallBack {
    fun showStanding(standing: List<Table?>)
}