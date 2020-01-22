package inn.mroyek.submission5kade.ui.search

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.pojo.Search

interface SearchContract : BaseServiceCallBack {
    fun showMatch(listMacth: MutableList<Search>)
}