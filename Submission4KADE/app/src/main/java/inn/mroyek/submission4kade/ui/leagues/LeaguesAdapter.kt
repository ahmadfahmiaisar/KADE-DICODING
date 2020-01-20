package inn.mroyek.submission4kade.ui.leagues

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import inn.mroyek.submission4kade.R
import inn.mroyek.submission4kade.common.loadImageStr
import inn.mroyek.submission4kade.model.pojo.Leagues
import kotlinx.android.synthetic.main.item_rv_league.view.*


class LeaguesAdapter (
    private val leagues: Leagues,
    private val onItemCLicked: () -> Unit
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView
        view.tv_league.text = leagues.title
        view.img_league.loadImageStr(leagues.img)

        view.setOnClickListener {
            onItemCLicked()
        }
    }

    override fun getLayout(): Int = R.layout.item_rv_league

}
