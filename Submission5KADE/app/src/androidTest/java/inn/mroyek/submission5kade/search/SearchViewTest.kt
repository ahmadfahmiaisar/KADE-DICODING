package inn.mroyek.submission5kade.search

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import inn.mroyek.submission5kade.R.id.*
import inn.mroyek.submission5kade.presentation.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchViewTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun homeTest(){
        delay(3000)
        /*onView(withId(rv_league)).check(matches(isDisplayed()))
        onView(withId(rv_league)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay()*/
        onView(withId(c_item_search)).check(matches(isDisplayed()))
        onView(withId(c_item_search)).perform(click())
        delay()
        /*onView(withId(sv_match)).check(matches(isDisplayed()))
        onView(withId(sv_match)).perform(click())
        delay()*/
        onView(withId(sv_match)).check(matches(isDisplayed()))
        delay()
        onView(withId(search_button)).perform(click())
//        onView(isAssignableFrom(SearchView::class.java)).perform(click())
//        delay()
//        onView(withId(sv_match)).perform( typeText("Arsenal"), pressImeActionButton())
        delay()
        onView(isAssignableFrom(EditText::class.java)).perform(click(), typeText("Arsenal"), pressImeActionButton())
        delay(5000)
        onView(withId(rv_search_match)).check(matches(isDisplayed()))
        onView(withId(rv_search_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        onView(withId(rv_search_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        delay()

    }
    private fun delay(time: Long = 1500){
        Thread.sleep(time)
    }

}