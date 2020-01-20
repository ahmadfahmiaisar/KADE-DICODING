package inn.mroyek.submission3kade.ui.detailmatch

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import inn.mroyek.submission3kade.R
import inn.mroyek.submission3kade.common.*
import inn.mroyek.submission3kade.model.pojo.MatchDetail
import inn.mroyek.submission3kade.model.pojo.Matchs
import inn.mroyek.submission3kade.model.pojo.Search
import inn.mroyek.submission3kade.model.response.MatchModel
import inn.mroyek.submission3kade.model.response.TeamModel
import inn.mroyek.submission3kade.network.ApiRepository
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchContract {

    private val presenter = DetailMatchPresenter(ApiRepository())
    private val detailMatchAdapter = GroupAdapter<GroupieViewHolder>()
    private val listItem = mutableListOf<Any>()
    private var match: Matchs? = null
    private var menuItem: Menu? = null

    private var matchSearch: Search? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        presenter.bindCallBack(this)
        setupView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuItem = menu
        match?.eventId?.let {
            presenter.checkExisMatch(it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_add_favorite -> {
                match?.let {
                    presenter.addFavorite(it)
                }
            }
            R.id.menu_rem_favorite -> {
                match?.eventId?.let {
                    presenter.removeFavorite(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        this.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rv_detail_match.apply {
            layoutManager = LinearLayoutManager(this@DetailMatchActivity)
            adapter = detailMatchAdapter
        }
        val key = intent?.getStringExtra(Constants.KEY)
        when (key) {
            Constants.MATCH_SEARCH -> {
                matchSearch = intent?.getParcelableExtra(Constants.MATCH_SEARCH)
                logD("GETKEY", "$matchSearch")
                presenter.getDetailMatch(matchSearch?.eventId)
                presenter.getDetailTeam(matchSearch?.homeTeamId, matchSearch?.awayTeamId)
            }
            Constants.MATCH -> {
                logD("GETKEY", "$match")
                match = intent?.getParcelableExtra(Constants.MATCH)
                logD("GETKEY", "$match")
                presenter.getDetailMatch(match?.eventId)
                presenter.getDetailTeam(match?.homeTeamId, match?.awayTeamId)
            }

        }

    }

    override fun showDetailMatch(match: MatchModel?) {
        tv_TimeDate_detail.text = this.match?.matchDate?.parsingDate("yyyy-MM-dd")
        tv_hometeam_name.text = match?.strHomeTeam
        tv_awayteam_name.text = match?.strAwayTeam
        tv_home_score.text = match?.intHomeScore
        tv_away_score.text = match?.intAwayScore

        initMatchDetail(match)
    }


    private fun initMatchDetail(match: MatchModel?) {
        listItem.add("Detail Match")
        listItem.add(
            MatchDetail(
                tvLeft = match?.strHomeGoalDetails,
                tvMid = "Goals",
                tvRight = match?.strAwayGoalDetails
            )
        )
        listItem.add(
            MatchDetail(
                tvLeft = match?.intHomeShots,
                tvMid = "Shoot",
                tvRight = match?.intAwayShots
            )
        )
        listItem.add(
            MatchDetail(
                tvLeft = match?.strHomeGoalDetails,
                tvMid = "Goal Keeper",
                tvRight = match?.strAwayGoalDetails
            )
        )
        listItem.add(
            MatchDetail(
                tvLeft = match?.strHomeGoalDetails,
                tvMid = "Goals Details",
                tvRight = match?.strAwayGoalDetails
            )
        )

        listItem.forEach {
            when (it) {
                is MatchDetail -> detailMatchAdapter.add(DetailMatchAdapter(it))
            }
        }
    }


    override fun showTeamHome(team: TeamModel?) {
        iv_home_team.loadImageStr(team?.strTeamBadge)
    }

    override fun showTeamAway(team: TeamModel?) {
        iv_away_team.loadImageStr(team?.strTeamBadge)
    }


    override fun onFail(msg: String) {
        toastLong(msg)
    }

    override fun saveFavorite() {
        menuItem?.clear()
        menuInflater.inflate(R.menu.rem_favorite, menuItem)
        toastLong("tambahkan ke favorite")
    }

    override fun removeFavorite() {
        menuItem?.clear()
        menuInflater.inflate(R.menu.add_favorite, menuItem)
        toastLong("hapus dari favorite")
    }

    override fun saveExisFavorite(saved: Boolean) {
        val intMenu = if (saved) R.menu.rem_favorite else R.menu.add_favorite
        menuInflater.inflate(intMenu, menuItem)
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }
}
