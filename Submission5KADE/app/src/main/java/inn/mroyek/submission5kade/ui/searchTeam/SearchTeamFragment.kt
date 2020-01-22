package inn.mroyek.submission5kade.ui.searchTeam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.Constants
import inn.mroyek.submission5kade.model.pojo.AllTeams
import inn.mroyek.submission5kade.network.ApiRepository
import inn.mroyek.submission5kade.ui.detailteams.DetailTeamsActivity
import inn.mroyek.submission5kade.ui.team.AllTeamsAdapter
import kotlinx.android.synthetic.main.fragment_search_team.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class SearchTeamFragment : Fragment(), SearchTeamContract, SearchView.OnQueryTextListener {

    private val presenter = SearchTeamPresenter(ApiRepository())
    private val adapterSearch = GroupAdapter<GroupieViewHolder>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.bindCallBack(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sv_team.setOnQueryTextListener(this)
    }
    override fun showTeam(listTeam: MutableList<AllTeams>) {
        adapterSearch.clear()
        listTeam.forEach {
            adapterSearch.add(AllTeamsAdapter(it){
                startActivity<DetailTeamsActivity>(Constants.TEAMS to it)
            })
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        adapterSearch.clear()
        rv_search_team.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterSearch
        }
        presenter.getSearchTeam(query)
        adapterSearch.notifyDataSetChanged()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }
}
