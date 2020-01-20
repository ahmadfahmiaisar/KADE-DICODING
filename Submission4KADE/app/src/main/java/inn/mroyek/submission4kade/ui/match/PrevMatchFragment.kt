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
import kotlinx.android.synthetic.main.fragment_prev_match.view.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class PrevMatchFragment(bundle: Bundle?) : Fragment(), MatchContract {

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
        return inflater.inflate(R.layout.fragment_prev_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leagueid = id?.getParcelable("id")
        view.rv_prev_match.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMatch
            logD("adapter", "$adapter, $adapterMatch")
        }
        val tipeMatch = "eventspastleague.php"
        presenter.getMatch(mathchType = tipeMatch, leaguesId = leagueid?.id.toString())

    }

    override fun loadMatchs(listMacth: List<Matchs?>) {
        adapterMatch.clear()
        listMacth.forEach {
            adapterMatch.add(MatchsAdapter(it) {
//                logD("SENDIT", "$it")
                /*val intent = Intent(context, DetailMatchActivity::class.java)
                intent.putExtra(Constants.KEY, "match")
                intent.putExtra(Constants.MATCH, "$it")
                context!!.startActivity(intent)*/
                startActivity<DetailMatchActivity>(Constants.MATCH to it, Constants.KEY to "match")
//                logD("SENDBUNDLE", "$intent")
                logD("SENDING", "$it")
            })
        }
        adapterMatch.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }
}
