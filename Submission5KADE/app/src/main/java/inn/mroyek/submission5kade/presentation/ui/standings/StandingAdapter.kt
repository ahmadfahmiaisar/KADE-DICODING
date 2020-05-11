package inn.mroyek.submission5kade.presentation.ui.standings

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.data.remote.model.Table
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingAdapter (private val itemStanding: Table?) : Item()
{
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.tv_standing_name.text = itemStanding?.name
        view.tv_standing_draw.text = itemStanding?.draw.toString()
        view.tv_standing_goalsagainst.text = itemStanding?.goalsagainst.toString()
        view.tv_standing_goalsdifferent.text = itemStanding?.goalsdifference.toString()
        view.tv_standing_goalsfor.text = itemStanding?.goalsfor.toString()
        view.tv_standing_loss.text = itemStanding?.loss.toString()
        view.tv_standing_played.text = itemStanding?.played.toString()
        view.tv_standing_win.text = itemStanding?.win.toString()
        view.tv_standing_total.text = itemStanding?.total.toString()

    }

    override fun getLayout(): Int = R.layout.item_standing
}
