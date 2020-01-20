package inn.mroyek.submission3kade.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import inn.mroyek.submission3kade.ui.match.NextMatchFragment
import inn.mroyek.submission3kade.ui.match.PrevMatchFragment


class ViewPagerAdapterMatch(
    fragmentManager: FragmentManager,
    private val bundle: Bundle?
) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PrevMatchFragment(bundle)
            }
            else -> NextMatchFragment(bundle)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Previous Match"
            else -> "Next Match"
        }
    }

}
