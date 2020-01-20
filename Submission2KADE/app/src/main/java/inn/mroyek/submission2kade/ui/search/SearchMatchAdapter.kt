package inn.mroyek.submission2kade.ui.search

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import inn.mroyek.submission2kade.R
import inn.mroyek.submission2kade.common.parsingDate
import inn.mroyek.submission2kade.model.pojo.Search
import kotlinx.android.synthetic.main.item_match_adapter.view.*

class SearchMatchAdapter( private val itemMatch: Search?,
                          private val onItemClick: () -> Unit) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.tv_date_time_match.text = itemMatch?.dateEvent?.parsingDate("yyyy-MM-dd")
        view.tv_home_team_match.text = itemMatch?.homeTeamStr
        view.tv_home_score_match.text = itemMatch?.homeScoreInt
        view.tv_away_team_match.text = itemMatch?.awayTeamStr
        view.tv_away_score_match.text = itemMatch?.awayScoreInt

        view.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.item_match_adapter

}