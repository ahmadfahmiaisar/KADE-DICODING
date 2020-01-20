package inn.mroyek.submission5kade.ui.match

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.pojo.Matchs

interface MatchContract : BaseServiceCallBack {
    fun loadMatchs(listMacth: List<Matchs?>)
}