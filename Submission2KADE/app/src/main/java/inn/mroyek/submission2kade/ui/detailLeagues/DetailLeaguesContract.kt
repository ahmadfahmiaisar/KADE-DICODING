package inn.mroyek.submission2kade.ui.detailLeagues

import inn.mroyek.submission2kade.base.BaseServiceCallBack
import inn.mroyek.submission2kade.model.response.DetailLeague

interface DetailLeaguesContract : BaseServiceCallBack{
    fun getDetailLeagues(detailLeague: DetailLeague?)
}