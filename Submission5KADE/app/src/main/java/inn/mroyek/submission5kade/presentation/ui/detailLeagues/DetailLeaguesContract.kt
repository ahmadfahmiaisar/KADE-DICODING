package inn.mroyek.submission5kade.presentation.ui.detailLeagues

import inn.mroyek.submission5kade.presentation.base.BaseServiceCallBack
import inn.mroyek.submission5kade.data.remote.model.DetailLeague

interface DetailLeaguesContract : BaseServiceCallBack {
//    fun showProgress(show: Boolean)
    fun getDetailLeagues(detailLeague: DetailLeague?)
}