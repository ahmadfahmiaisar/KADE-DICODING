package inn.mroyek.submission3kade.ui.match

import inn.mroyek.submission3kade.base.BaseServiceCallBack
import inn.mroyek.submission3kade.model.pojo.Matchs

interface MatchContract : BaseServiceCallBack {
    fun loadMatchs(listMacth: List<Matchs?>)
}