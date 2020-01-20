package inn.mroyek.submission5kade.ui.detailLeagues

import inn.mroyek.submission5kade.base.BaseServiceCallBack
import inn.mroyek.submission5kade.model.response.DetailLeague

interface DetailLeaguesContract : BaseServiceCallBack {
//    fun showProgress(show: Boolean)
    fun getDetailLeagues(detailLeague: DetailLeague?)
}