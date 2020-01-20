package inn.mroyek.submission3kade.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import inn.mroyek.submission3kade.R
import inn.mroyek.submission3kade.common.Constants
import inn.mroyek.submission3kade.model.pojo.Matchs
import inn.mroyek.submission3kade.ui.detailmatch.DetailMatchActivity
import inn.mroyek.submission3kade.ui.match.MatchsAdapter
import kotlinx.android.synthetic.main.fragment_favorite_match.view.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment(), FavoriteContract {

    private val presenter = FavoritePresenter()
    private val adapterMatch = GroupAdapter<GroupieViewHolder>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presenter.bindCallBack(this)
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rv_favorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMatch
        }
    }
    override fun showFavorites(listFavorite: List<Matchs>) {
        adapterMatch.clear()
        listFavorite.forEach {
            adapterMatch.add(MatchsAdapter(it){
                startActivity<DetailMatchActivity>(Constants.MATCH to it, Constants.KEY to "match")
            })
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteMatch()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unBind()
    }
}
