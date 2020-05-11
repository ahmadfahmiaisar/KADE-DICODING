package inn.mroyek.submission5kade.presentation.ui.match

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.presentation.model.Matchs

interface MatchContract : BaseServiceCallBack {
    fun loadMatchs(listMacth: List<Matchs?>)
}