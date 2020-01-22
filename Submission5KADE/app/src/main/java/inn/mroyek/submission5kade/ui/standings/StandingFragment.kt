package inn.mroyek.submission5kade.ui.standings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.model.pojo.Leagues
import inn.mroyek.submission5kade.model.response.Table
import inn.mroyek.submission5kade.network.ApiRepository
import kotlinx.android.synthetic.main.fragment_standing.view.*


/**
 * A simple [Fragment] subclass.
 */
class StandingFragment(bundle: Bundle?) : Fragment(), StandingContract {
    private val presenter = StandingPresenter(ApiRepository())
    private val adapterStanding = GroupAdapter<GroupieViewHolder>()
    private var league: Leagues? = null
    private val id = bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.bindCallBack(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        league = id?.getParcelable("id")
        view.rv_standing.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterStanding
        }
        presenter.getStanding(id = league?.id.toString())
    }


    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }

    override fun showStanding(standing: List<Table?>) {
        adapterStanding.clear()
        standing.forEach {
            adapterStanding.add(StandingAdapter(it))
        }
    }
}
