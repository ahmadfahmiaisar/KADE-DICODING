package inn.mroyek.submission5kade.presentation.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.Constants
import inn.mroyek.submission5kade.presentation.model.AllTeams
import inn.mroyek.submission5kade.presentation.model.Matchs
import inn.mroyek.submission5kade.presentation.ui.detailmatch.DetailMatchActivity
import inn.mroyek.submission5kade.presentation.ui.detailteams.DetailTeamsActivity
import inn.mroyek.submission5kade.presentation.ui.match.MatchsAdapter
import inn.mroyek.submission5kade.presentation.ui.team.AllTeamsAdapter
import kotlinx.android.synthetic.main.fragment_favorite_match.view.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment(), FavoriteContract {

    private val presenter = FavoritePresenter()
    private val adapterFav = GroupAdapter<GroupieViewHolder>()
    private val adapterTeam = GroupAdapter<GroupieViewHolder>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presenter.bind(this)
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rv_favorite.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            adapter = adapterFav
        }
        view.rv_favorite_team.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            adapter = adapterTeam
        }
    }

    override fun showFavorites(listFavorite: List<Matchs>) {
        adapterFav.clear()
        listFavorite.forEach {
            adapterFav.add(MatchsAdapter(it) {
                startActivity<DetailMatchActivity>(Constants.MATCH to it, Constants.KEY to "match")
            })
        }
    }

    override fun showTeamFavorite(listTeamFavorite: List<AllTeams>) {
        adapterTeam.clear()
        listTeamFavorite.forEach {
            adapterTeam.add(AllTeamsAdapter(it) {
                startActivity<DetailTeamsActivity>(Constants.TEAMS to it)
            })
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.getFavoriteMatch()
        presenter.getFavoriteTeams()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }
}
