package inn.mroyek.submission4kade.ui.search

import inn.mroyek.submission4kade.base.BaseServiceCallBack
import inn.mroyek.submission4kade.model.pojo.Search

interface SearchContract : BaseServiceCallBack {
    fun showMatch(listMacth: MutableList<Search>)
}