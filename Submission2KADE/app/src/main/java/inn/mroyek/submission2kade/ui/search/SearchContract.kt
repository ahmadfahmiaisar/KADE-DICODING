package inn.mroyek.submission2kade.ui.search

import inn.mroyek.submission2kade.base.BaseServiceCallBack
import inn.mroyek.submission2kade.model.pojo.Search

interface SearchContract : BaseServiceCallBack {
    fun showMatch(listMacth: MutableList<Search>)
}