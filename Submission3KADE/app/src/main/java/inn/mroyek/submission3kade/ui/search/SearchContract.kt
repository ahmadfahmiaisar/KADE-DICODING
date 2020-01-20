package inn.mroyek.submission3kade.ui.search

import inn.mroyek.submission3kade.base.BaseServiceCallBack
import inn.mroyek.submission3kade.model.pojo.Search

interface SearchContract : BaseServiceCallBack {
    fun showMatch(listMacth: MutableList<Search>)
}