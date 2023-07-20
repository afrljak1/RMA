/*package ba.etf.rma23
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma23.projekat.data.repositories.GameData
import ba.etf.rma23.projekat.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestLayout {
    fun hasItemCount(n: Int) = object : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            assertTrue("View nije tipa RecyclerView", view is RecyclerView)
            var rv: RecyclerView = view as RecyclerView
            assertThat(
                "GetItemCount RecyclerView broj elementa: ",
                rv.adapter?.itemCount,
                Is(n)
            )
        }
    }

    @get:Rule
    var homeRule:ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    //@Test
   /* fun elementsTest(){
        onView(withId(R.id.logo_image)).check(isCompletelyLeftOf(withId(R.id.home_button)))
        onView(withId(R.id.home_button)).check(isTopAlignedWith(withChild(withId(R.id.home_button))))
        onView(withId(R.id.details_button)).check(isTopAlignedWith(withId(R.id.home_button)))
        onView(withId(R.id.search_button)).check(isCompletelyBelow(withId(R.id.home_button)))
        onView(withId(R.id.search_button)).check(isTopAlignedWith(withId(R.id.search_query_edittext)))
    }*/

    @Test
    fun recyclerViewTest(){
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(0)).check(matches(allOf(
            hasDescendant(withId(R.id.item_title_textview)),
            hasDescendant(withId(R.id.game_rating_textview)),
            hasDescendant(withId(R.id.game_release_date_textview)),
            hasDescendant(withId(R.id.game_platform_textview)),
            hasDescendant(withId(R.id.game_rating_textview))
        )))

        onView(withId(R.id.game_list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(0)).check(
            matches(hasDescendant(withId(R.id.item_title_textview)).also { isTopAlignedWith(
                withChild(withId(R.id.item_title_textview))
            ) }))
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(0)).check(
            matches(hasDescendant(withId(R.id.item_title_textview)).also { isCompletelyRightOf(
                withId(R.id.game_rating_textview)
            ) }))
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(0)).check(
            matches(hasDescendant(withId(R.id.game_rating_textview)).also { isCompletelyLeftOf(
                withId(R.id.game_platform_textview)
            ) }))
    }

    @Test
    fun recyclerViewTest2(){
        var prvaIgra = GameData.getAll().get(0)
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.scrollTo<ViewHolder>(allOf(
            hasDescendant(withText(prvaIgra.title)),
            hasDescendant(withText(prvaIgra.releaseDate)),
            hasDescendant(withText(prvaIgra.rating.toString()))
        )))
    }
    @Test
    fun recyclerViewTest3(){
        var prvaIgra = GameData.getAll().get(0)
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.actionOnItem<ViewHolder>(allOf(
            hasDescendant(withText(prvaIgra.title)),
            hasDescendant(withText(prvaIgra.releaseDate)),
            hasDescendant(withText(prvaIgra.rating.toString()))
        ),click()))
        onView(withText(prvaIgra.description)).check(matches(isCompletelyDisplayed()))
        onView(instanceOf(RecyclerView::class.java)).check(hasItemCount(prvaIgra.userImpressions.size))
    }
    @Test
    fun detailsDugmeNakonItemClick(){
        var prvaIgra = GameData.getAll().get(0)
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.actionOnItem<ViewHolder>(allOf(
            hasDescendant(withText(prvaIgra.title)),
            hasDescendant(withText(prvaIgra.releaseDate)),
            hasDescendant(withText(prvaIgra.rating.toString()))
        ),click()))
       // onView(withId(R.id.home_button)).perform(click())
        //onView(withId(R.id.details_button)).perform(click())
        onView(withText(prvaIgra.description)).check(matches(isCompletelyDisplayed()))
    }
}

 */