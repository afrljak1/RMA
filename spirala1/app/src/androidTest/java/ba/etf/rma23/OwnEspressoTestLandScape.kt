package ba.etf.rma23

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
/*

@RunWith(AndroidJUnit4::class)
class OwnEspressoTestLandScape {

    @get:Rule
    val activityruleorientation = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setuporientation() {  //metoda koja postavlja orijentaciju
        activityruleorientation.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }
*/
    //Prvo sam definirala Rule-pravilo koje označava da je pravilo definisano na nivou klase
    //Nakon toga sam koristila anotaciju @Before koja znači da će se izvršiti prije svakog testa, te unutar toga sam kreirala metodu setuporientation()
    //Ova metoda omogućuva postavljanje u landscape orijentaciji
    //Testni scenario provjerava da li se unutar GameDetailsFragmenta prva igra ima odredeni title, platform, te esrb_rating
    //Testni slucaj za GameDetailsFragment koristi metodu onView unutar koje se nalaze 2 matchera unutar kojih sam proslijedila odgovarajuci id, te tekst

/*
    @Test
    fun testLandscape() {
        onView(allOf(withId(R.id.item_title_textview), withText("PUBG: Battlegrounds")))
        onView(allOf(withId(R.id.platform_textview), withText("Stadia streaming platform")))
        onView(allOf(withId(R.id.esrb_rating_textview), withText("9.7")))

       /* onView(withId(R.id.platform_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.cover_imageview)).check(matches(isDisplayed()))
        onView(withId(R.id.esrb_rating_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.developer_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.publisher_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.description_textview)).check(matches(isDisplayed()))*/
    }

    //Testni scenario podrazumijeva testiranje iz landsape u portrait orijentaciju
    //Ovaj test prvo pronalazi RecyclerView sa odgovarajucim id elementom
    //Test zapocinje u landscape orijentaciji, te se nakon toga pomocu requestedOrientation orijentacija postavlja na portrait
    //Nakon toga se ponovo provjerava da li se nalazi RecyclerView, te se orijentacija ponovo postavlja u pocetnu - landscape orijentacija

    @Test
    fun testLandscapeToPortrait() {
        onView(withId(R.id.game_list))
            .check(matches(isDisplayed()))
        activityruleorientation.scenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        onView(withId(R.id.game_list))
            .check(matches(isDisplayed()))
        activityruleorientation.scenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.game_list))
            .check(matches(isDisplayed()))
    }
}


 */