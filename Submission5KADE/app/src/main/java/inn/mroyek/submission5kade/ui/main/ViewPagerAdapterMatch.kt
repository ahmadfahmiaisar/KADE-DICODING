package inn.mroyek.submission5kade.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import inn.mroyek.submission5kade.ui.match.NextMatchFragment
import inn.mroyek.submission5kade.ui.match.PrevMatchFragment
import inn.mroyek.submission5kade.ui.team.AllTeamsFragment


@Suppress("DEPRECATION")
class ViewPagerAdapterMatch(
    fragmentManager: FragmentManager,
    private val bundle: Bundle?
) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PrevMatchFragment(bundle)
            1 -> NextMatchFragment(bundle)
            else -> AllTeamsFragment(bundle)
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Previous Match"
            1 -> "Next Match"
            else -> "Teams"
        }
    }

}
