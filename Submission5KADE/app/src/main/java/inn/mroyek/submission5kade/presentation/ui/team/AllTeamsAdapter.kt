package inn.mroyek.submission5kade.presentation.ui.team

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.common.loadImageStr
import inn.mroyek.submission5kade.presentation.model.AllTeams
import kotlinx.android.synthetic.main.item_rv_teams.view.*

class AllTeamsAdapter(
    private val teams: AllTeams?,
    private val onItemClick: () -> Unit
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.tv_teams.text = teams?.strTeam
        view.iv_logo_team.loadImageStr(teams?.strTeamBadge)
        view.tv_team_league.text = teams?.strLeague
        view.setOnClickListener {
            onItemClick()
        }
    }

    override fun getLayout(): Int = R.layout.item_rv_teams

}