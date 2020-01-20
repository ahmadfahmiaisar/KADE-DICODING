package inn.mroyek.submission5kade.ui.detailteams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.Constants
import inn.mroyek.submission5kade.common.loadImageStr
import inn.mroyek.submission5kade.common.toastLong
import inn.mroyek.submission5kade.model.pojo.AllTeams
import inn.mroyek.submission5kade.model.response.TeamModel
import inn.mroyek.submission5kade.network.ApiRepository
import kotlinx.android.synthetic.main.activity_detail_teams.*

class DetailTeamsActivity : AppCompatActivity(), DetailTeamsContract {

    private val presenter = DetailTeamsPresenter(ApiRepository())
    private var team: AllTeams? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teams)
        presenter.bindCallBack(this)
        initView()
    }

    private fun initView() {
        this.title = "Detail Teams"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        team = intent?.getParcelableExtra(Constants.TEAMS)
        presenter.getTeam(team?.idTeam)
    }

    override fun showDetailTeams(team: TeamModel?) {
        tv_country_detailteam.text = team?.strCountry
        tv_league_detail_team.text = team?.strLeague
        tv_desc_detailteam.text = team?.strStadiumDescription
        tv_team_detail.text = team?.strTeam
        tv_website_detailteam.text = team?.strWebsite
        iv_banner_detailteam.loadImageStr(team?.strTeamBanner)
        iv_logo_detailTeam.loadImageStr(team?.strTeamLogo)
    }


    override fun onFail(msg: String) {
        toastLong(msg)
    }
}
