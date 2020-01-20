package inn.mroyek.submission2kade.ui.match

import inn.mroyek.submission2kade.base.BaseServiceCallBack
import inn.mroyek.submission2kade.model.pojo.Matchs

interface MatchContract : BaseServiceCallBack {
    fun loadMatchs(listMacth: List<Matchs?>)
}