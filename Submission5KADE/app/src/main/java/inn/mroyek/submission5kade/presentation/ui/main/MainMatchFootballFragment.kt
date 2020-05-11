package inn.mroyek.submission5kade.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import inn.mroyek.submission5kade.BaseApp
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.Constants
import inn.mroyek.submission5kade.common.loadImageStr
import inn.mroyek.submission5kade.common.logD
import inn.mroyek.submission5kade.data.remote.model.DetailLeague
import inn.mroyek.submission5kade.presentation.model.Leagues
import inn.mroyek.submission5kade.presentation.ui.detailLeagues.DetailLeaguesContract
import inn.mroyek.submission5kade.presentation.ui.detailLeagues.DetailLeaguesPresenter
import kotlinx.android.synthetic.main.detail_league.*
import kotlinx.android.synthetic.main.fragment_main_match_football.*
import javax.inject.Inject


class MainMatchFootballFragment : Fragment(), DetailLeaguesContract {
//    private val presenter = DetailLeaguesPresenter(ApiRepository())
    @Inject
    lateinit var presenter : DetailLeaguesPresenter

    private var leagueid: Leagues? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        presenter.bind(this)
        return inflater.inflate(R.layout.fragment_main_match_football, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.bind(this)
//        (requireActivity() as BaseApp).fragmentInjector .inject(this)
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

   /* override fun showProgress(show: Boolean) {
        progressbar.visibility = if (show) View.VISIBLE else View.GONE
    }*/

    override fun getDetailLeagues(detailLeague: DetailLeague?) {
        tv_title_league_detail.text = detailLeague?.strLeague
        tv_desc_detail_league.setContent(detailLeague?.strDescriptionEN)
        iv_logo_league_detail.loadImageStr(detailLeague?.strBadge)
    }


    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}