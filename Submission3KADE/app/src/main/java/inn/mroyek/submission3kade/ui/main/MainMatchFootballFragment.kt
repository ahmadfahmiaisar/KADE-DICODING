package inn.mroyek.submission3kade.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import inn.mroyek.submission3kade.R
import inn.mroyek.submission3kade.common.Constants
import inn.mroyek.submission3kade.common.loadImageStr
import inn.mroyek.submission3kade.common.logD
import inn.mroyek.submission3kade.model.response.DetailLeague
import inn.mroyek.submission3kade.model.pojo.Leagues
import inn.mroyek.submission3kade.network.ApiRepository
import inn.mroyek.submission3kade.ui.detailLeagues.DetailLeaguesContract
import inn.mroyek.submission3kade.ui.detailLeagues.DetailLeaguesPresenter
import kotlinx.android.synthetic.main.detail_league.*
import kotlinx.android.synthetic.main.fragment_main_match_football.*


class MainMatchFootballFragment : Fragment(), DetailLeaguesContract {

    private val presenter = DetailLeaguesPresenter(ApiRepository())
    private var leagueid: Leagues? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presenter.bindCallBack(this)
        return inflater.inflate(R.layout.fragment_main_match_football, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        id = intent?.getParcelableExtra(Constants.MATCH_SEARCH)
        val intent = arguments
        leagueid = intent?.getParcelable(Constants.LEAGUEID)
        logD("BUNDLERECEIVE", "$leagueid")
        presenter.getDetailLeagues(leagueid?.id!!)


        val bundle = arguments
        bundle?.putParcelable("id", leagueid)
        val adapter = ViewPagerAdapterMatch(
            childFragmentManager, bundle
        )
        view_page_match.adapter = adapter
        tab_match.setupWithViewPager(view_page_match)
    }

    override fun getDetailLeagues(detailLeague: DetailLeague?) {
        tv_title_league_detail.text = detailLeague?.strLeague
        tv_desc_detail_league.setContent(detailLeague?.strDescriptionEN)
        iv_logo_league_detail.loadImageStr(detailLeague?.strLogo)
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }
}