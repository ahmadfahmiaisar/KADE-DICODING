package inn.mroyek.submission5kade.presentation.ui.main

import inn.mroyek.submission5kade.R
import inn.mroyek.submission5kade.presentation.ui.leagues.LeaguesFootballFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import inn.mroyek.submission5kade.presentation.ui.favorite.FavoriteMatchFragment
import kotlinx.android.synthetic.main.activity_main_menu_bottom.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_bottom)
        if (savedInstanceState == null) {
            loadingFragment(LeaguesFootballFragment(), "League Fragment")
        }
        navigationBottomView.setNavigationChangeListener { view, _ ->
            when (view.id) {
                R.id.c_item_leagues -> {
                    loadingFragment(LeaguesFootballFragment(), "League Fragment")
                    return@setNavigationChangeListener
                }
                R.id.c_item_match -> {
                    loadingFragment(FavoriteMatchFragment(), "Favorite Fragment")
                    return@setNavigationChangeListener
                }
           /*     R.id.c_item_search -> {
                    loadingFragment(SearchMatchMatchFragment(), "Search Match")
                    return@setNavigationChangeListener
                }
                R.id.c_item_search_team -> {
                    loadingFragment(SearchTeamFragment(), "Search Team")
                    return@setNavigationChangeListener
                }*/
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
