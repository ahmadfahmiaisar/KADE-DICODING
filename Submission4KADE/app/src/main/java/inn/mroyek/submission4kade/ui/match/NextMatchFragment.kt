package inn.mroyek.submission4kade.ui.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import inn.mroyek.submission4kade.R
import inn.mroyek.submission4kade.common.Constants
import inn.mroyek.submission4kade.common.logD
import inn.mroyek.submission4kade.model.pojo.Leagues
import inn.mroyek.submission4kade.model.pojo.Matchs
import inn.mroyek.submission4kade.network.ApiRepository
import inn.mroyek.submission4kade.ui.detailmatch.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment(bundle: Bundle?) : Fragment(), MatchContract {

    private val presenter = MatchPresenter(ApiRepository())
    private val adapterMatch = GroupAdapter<GroupieViewHolder>()
    private var leagueid: Leagues? = null
    private val id = bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.bindCallBack(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leagueid = id?.getParcelable("id")
        view.rv_next_match.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMatch
            logD("adapter", "$adapter, $adapterMatch")
        }
        val typeMatch = "eventsnextleague.php"
        presenter.getMatch(mathchType = typeMatch, leaguesId = leagueid?.id.toString())
    }

    override fun loadMatchs(listMacth: List<Matchs?>) {
        adapterMatch.clear()
        listMacth.forEach {
            adapterMatch.add(MatchsAdapter(it) {
                startActivity<DetailMatchActivity>(Constants.MATCH to it, Constants.KEY to "match")
            })
        }
        adapterMatch.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }

}
