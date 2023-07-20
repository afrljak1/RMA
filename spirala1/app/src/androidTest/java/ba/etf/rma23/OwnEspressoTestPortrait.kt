package ba.etf.rma23
/*
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
//import ba.etf.rma23.projekat.MainActivity
import ba.etf.rma23.projekat.MainActivity
import org.hamcrest.CoreMatchers.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class OwnEspressoTestPortrait {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    // Testni scenario koji provjerava BottomNavigation i RecyclerView
    // Prvo se provjerava da li se BottomNavigation sastoji od Home i Details, te nakon toga se provjerava RecyclerView
    // Za RecyclerView sam uzela da se provjerava igra koja se nalazi na poziciji 4, odnosno sa indeksom 4 - FIFA 18
    // Unutar koda koji je zakomentarisan sam proslijedivala odgovarajuce id, te ovaj dio radi za sve recyclerview-e
    // Testni slucaj za BottomNavigation koristi metodu onView unutar koje se nalaze 2 matchera unutar kojih sam proslijedila odgovarajuci id, kao i tekst koji je potreban da bi se test ispravno izvrsio
    // Testni slucaj za RecyclerView koristi metodu hasDescendant koja sluzi za provjeru RecyclerView

    @Test
    fun testMenu() {
        onView(allOf(withId(R.id.homeItem), withText("Home")))
        onView(allOf(withId(R.id.gameDetailsItem), withText("Details")))
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4)).check(
            ViewAssertions.matches(
                allOf(
                    hasDescendant(withId(R.id.item_title_textview)), //PROVJERA DA LI SADRZI ODGOVARAJUCI ID
                    hasDescendant(withId(R.id.game_release_date_textview)),
                    hasDescendant(withId(R.id.game_platform_textview)),
                    hasDescendant(withId(R.id.game_rating_textview))


                    //OVAJ DIO KODA PROVJERAVA ZA IGRU KOJA IMA INDEX 4
                   /*  hasDescendant(withText("FIFA 18")), //PROVJERA DA LI SADRZI TEKST
                    hasDescendant(withText("September 29, 2017")),
                    hasDescendant(withText("Multi-platform")),
                    hasDescendant(withText("4.2"))*/

                )
            )
        )
    }


}


 */