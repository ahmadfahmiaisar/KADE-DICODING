package inn.mroyek.submission4kade.ui.detailLeagues

import inn.mroyek.submission4kade.base.BaseServiceCallBack
import inn.mroyek.submission4kade.model.response.DetailLeague

interface DetailLeaguesContract : BaseServiceCallBack{
    fun getDetailLeagues(detailLeague: DetailLeague?)
}