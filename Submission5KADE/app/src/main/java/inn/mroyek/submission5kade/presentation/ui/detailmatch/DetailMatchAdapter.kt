package inn.mroyek.submission5kade.presentation.ui.detailmatch

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.presentation.model.MatchDetail
import kotlinx.android.synthetic.main.item_detail_match.view.*

class DetailMatchAdapter (private val item: MatchDetail) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.tv_left.text = item.tvLeft ?: "nothing in api"
        view.tv_mid.text = item.tvMid ?: "nothing in api"
        view.tv_right.text = item.tvRight ?: "nothing in api"
    }

    override fun getLayout(): Int = R.layout.item_detail_match

}