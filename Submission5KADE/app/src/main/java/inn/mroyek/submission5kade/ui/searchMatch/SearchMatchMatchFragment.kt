package inn.mroyek.submission5kade.ui.searchMatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.Constants
import inn.mroyek.submission5kade.model.pojo.Search
import inn.mroyek.submission5kade.network.ApiRepository
import inn.mroyek.submission5kade.ui.detailmatch.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_search_match.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class SearchMatchMatchFragment : Fragment(), SearchMatchContract,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private var presenter = SearchMatchPresenter(ApiRepository())
    private val searchAdapter = GroupAdapter<GroupieViewHolder>()
    private var strQuery: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.bindCallBack(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sv_match.setOnQueryTextListener(this)
//        presenter.getSearchMatch(strQuery)
    }


    override fun showMatch(listMacth: MutableList<Search>) {
        searchAdapter.clear()
        listMacth.forEach {
            searchAdapter.add(SearchMatchAdapter(it) {
                startActivity<DetailMatchActivity>(
                    Constants.MATCH_SEARCH to it,
                    Constants.KEY to "match_search"
                )
            })
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchAdapter.clear()
        strQuery = query
        getSearch(strQuery)
        return false
    }

    private fun getSearch(query: String?) {
        rv_search_match.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
        if (query != null) {
            presenter.getSearchMatch(strQuery)
        }
        searchAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }

}
