package inn.mroyek.submission5kade.ui.searchMatch

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.pojo.Search

interface SearchMatchContract : BaseServiceCallBack {
    fun showMatch(listMacth: MutableList<Search>)
}