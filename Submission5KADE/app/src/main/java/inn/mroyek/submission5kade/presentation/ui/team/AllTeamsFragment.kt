package inn.mroyek.submission5kade.presentation.ui.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.android.support.DaggerFragment
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.Constants
import inn.mroyek.submission5kade.presentation.model.AllTeams
import inn.mroyek.submission5kade.presentation.model.Leagues
import inn.mroyek.submission5kade.presentation.ui.detailteams.DetailTeamsActivity
import kotlinx.android.synthetic.main.fragment_all_teams.view.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class AllTeamsFragment(bundle: Bundle?) : DaggerFragment(), AllTeamsContract {
//    private val presenter = AllTeamsPresenter(ApiRepository())
    @Inject
    lateinit var presenter : AllTeamsPresenter

    private val adapterTeam = GroupAdapter<GroupieViewHolder>()
    private var leagueId: Leagues? = null
    private val id = bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.bind(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leagueId = id?.getParcelable("id")
        view.rv_teams.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTeam
        }
        presenter.getTeam(idLeague = leagueId?.id.toString())
    }

    override fun getTeams(listTeams: List<AllTeams?>) {
        adapterTeam.clear()
        listTeams.forEach {
            adapterTeam.add(AllTeamsAdapter(it) {
                startActivity<DetailTeamsActivity>(Constants.TEAMS to it)
            })
        }
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}
