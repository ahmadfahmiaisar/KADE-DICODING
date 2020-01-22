package inn.mroyek.submission5kade.ui.detailteams

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private var itemMenu: Menu? = null
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.itemMenu = menu
        team?.idTeam?.let {
            presenter.checkExistTeam(it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_add_favorite -> {
                team?.let {
                    presenter.addFavoriteTeam(it)
                }
            }
            R.id.menu_rem_favorite -> {
                team?.idTeam?.let {
                    presenter.removeFavoriteTeam(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun saveFavorite() {
        itemMenu?.clear()
        menuInflater.inflate(R.menu.rem_favorite, itemMenu)
        toastLong("tambahkan ke favorite")
    }

    override fun removeFavorite() {
        itemMenu?.clear()
        menuInflater.inflate(R.menu.add_favorite, itemMenu)
        toastLong("hapus dari favorite")
    }

    override fun saveExisFavorite(saved: Boolean) {
        val intMenu = if (saved) R.menu.rem_favorite else R.menu.add_favorite
        menuInflater.inflate(intMenu, itemMenu)
    }

    override fun onFail(msg: String) {
        toastLong(msg)
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }
}
