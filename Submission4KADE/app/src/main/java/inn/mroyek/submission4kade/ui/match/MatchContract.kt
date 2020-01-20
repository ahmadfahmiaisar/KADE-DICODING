package inn.mroyek.submission4kade.ui.match

import inn.mroyek.submission4kade.base.BaseServiceCallBack
import inn.mroyek.submission4kade.model.pojo.Matchs

interface MatchContract : BaseServiceCallBack {
    fun loadMatchs(listMacth: List<Matchs?>)
}