package inn.mroyek.submission5kade.ui.match

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.parsingDate
import inn.mroyek.submission5kade.model.pojo.Matchs
import kotlinx.android.synthetic.main.item_match_adapter.view.*

class MatchsAdapter(
    private val match: Matchs?,
    private val onItemClick: () -> Unit) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.tv_date_time_match.text = match?.matchDate?.parsingDate("yyyy-MM-dd")
        view.tv_home_team_match.text = match?.homeTeamName
        view.tv_home_score_match.text = match?.homeScore ?: "0"
        view.tv_away_team_match.text = match?.awayTeamName
        view.tv_away_score_match.text = match?.awayScore ?: "0"

        view.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.item_match_adapter

}