package inn.mroyek.submission3kade.ui.detailLeagues

import inn.mroyek.submission3kade.base.BaseServiceCallBack
import inn.mroyek.submission3kade.model.response.DetailLeague

interface DetailLeaguesContract : BaseServiceCallBack{
    fun getDetailLeagues(detailLeague: DetailLeague?)
}