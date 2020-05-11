package inn.mroyek.submission5kade.presentation.ui.searchMatch

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.presentation.model.Search

interface SearchMatchContract : BaseServiceCallBack {
    fun showMatch(listMacth: List<Search>)
}