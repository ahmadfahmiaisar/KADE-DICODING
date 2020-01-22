package inn.mroyek.submission5kade.ui.standings

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.response.Table

interface StandingContract : BaseServiceCallBack {
    fun showStanding(standing: List<Table?>)
}