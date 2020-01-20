package inn.mroyek.submission3kade.ui.main

import inn.mroyek.submission3kade.R
import inn.mroyek.submission3kade.ui.leagues.LeaguesFootballFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import inn.mroyek.submission3kade.ui.favorite.FavoriteMatchFragment
import inn.mroyek.submission3kade.ui.search.SearchMatchFragment
import kotlinx.android.synthetic.main.activity_main_menu_bottom.*

class MainMenuBottomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_bottom)
        if (savedInstanceState == null) {
            loadingFragment(LeaguesFootballFragment(), "League Fragment")
        }
        navigationBottomView.setNavigationChangeListener { view, position ->
            when (view.id) {
                R.id.c_item_leagues -> {
                    loadingFragment(LeaguesFootballFragment(), "League Fragment")
                    return@setNavigationChangeListener
                }
                R.id.c_item_search -> {
                    loadingFragment(SearchMatchFragment(), "Search Fragment")
                    return@setNavigationChangeListener
                }
                R.id.c_item_match -> {
                    loadingFragment(FavoriteMatchFragment(), "Favorite Fragment")
                    return@setNavigationChangeListener
                }
            }
        }
    }

    private fun loadingFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout_mainBottom, fragment, tag)
            .commit()
    }

}
