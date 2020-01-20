package inn.mroyek.submission5kade.ui.leagues


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.Constants
import inn.mroyek.submission5kade.common.logD
import inn.mroyek.submission5kade.model.data.ListDataLeague
import inn.mroyek.submission5kade.ui.main.MainMatchFootballFragment
import kotlinx.android.synthetic.main.fragment_leagues_football.view.*


/**
 * A simple [Fragment] subclass.
 */
class LeaguesFootballFragment : Fragment() {

    var listLeague = ListDataLeague.league
    private val adapterLeagues = GroupAdapter<GroupieViewHolder>()
    private val bundle = Bundle()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leagues_football, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterLeagues.clear()
        listLeague.forEach {
            adapterLeagues.add(LeaguesAdapter(it) {
                bundle.putParcelable(Constants.LEAGUEID, it)
                logD("BUNDLESEND", "$bundle")
                loadingFragment(MainMatchFootballFragment(), "mainmenu")
            })
        }
        view.rv_league.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterLeagues
            setHasFixedSize(true)
        }
        adapterLeagues.notifyDataSetChanged()

    }
    private fun loadingFragment(fragment: Fragment, tag: String) {
        fragment.arguments = bundle
        logD("BUNDLESENDD", "$bundle")
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout_mainBottom, fragment, tag)
            ?.addToBackStack("back")
            ?.commit()
    }
}
