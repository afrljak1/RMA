package ba.etf.rma23.projekat

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import ba.etf.rma23.projekat.*
import ba.etf.rma23.projekat.data.*
import ba.etf.rma23.projekat.data.repositories.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.net.URL


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DBTest {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule(MainActivity::class.java)


    //pri testiranju zamjenite hash sa vašim i id igre umjesto 22 postavite na neki drugi broj
    // da ne bi došlo do konfuzije oko rezultata testova ako dva studenta istovremeno testiraju svoj kod
    private val HASH = "ca0ee672-440b-45b2-8a12-75b80f4fbdd3"
    private val idIGRE = 22


    private val countNotOnline =
        "SELECT COUNT(*) AS broj_reviews FROM gamereview WHERE online=false"

    private fun executeCountAndCheck(query: String, column: String, value: Long) {
        var rezultat = db.rawQuery(query, null)
        rezultat.moveToFirst()
        var brojOdgovora = rezultat.getLong(0)
        MatcherAssert.assertThat(brojOdgovora, `is`(equalTo(value)))
    }

    companion object {
        private lateinit var context: Context
        private lateinit var baza: String
        private lateinit var db: SQLiteDatabase

        suspend fun obrisi() {
            var client: OkHttpClient = OkHttpClient()
            var builder: Request.Builder = Request.Builder()
                .url(URL("https://rma23ws.onrender.com/account/" + AccountGamesRepository.getHash() + "/gamereviews"))
                .delete()
            var request: Request = builder.build()
            withContext(Dispatchers.IO) {
                var response: Response = client.newCall(request).execute()
            }
        }

        @BeforeClass
        @JvmStatic
        fun setup() = runBlocking {
            val scenario = ActivityScenario.launch(
                MainActivity::class.java
            )

            context = ApplicationProvider.getApplicationContext<Context>()

            baza = context.databaseList().minBy { x -> x.length }
            db = SQLiteDatabase.openDatabase(
                context.getDatabasePath(baza).absolutePath,
                null,
                SQLiteDatabase.OPEN_READONLY
            )
            db.rawQuery("DELETE FROM gamereview", null)
            obrisi()
        }
    }
    @Test
    fun a0_countOfflineReviews() = runBlocking {
        AccountGamesRepository.setHash(HASH)
        var rez = GameReviewsRepository.getOfflineReviews(context)
        executeCountAndCheck(countNotOnline, "broj_reviews", rez.size.toLong())

    }

    @Test
    fun a1_sendOfflineAndCount() = runBlocking {
        var rez1 = GameReviewsRepository.getOfflineReviews(context)
        var rez = GameReviewsRepository.sendOfflineReviews(context)
        executeCountAndCheck(countNotOnline, "broj_reviews", rez1.size.toLong() - rez)
    }

    @Test
    fun a2_noOfflineReviews() = runBlocking {
        executeCountAndCheck(countNotOnline, "broj_reviews", 0)
    }

/*
    @Test
    fun a3_sendWhileOffline() = runBlocking {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
        Thread.sleep(2000)
        var rez =
            GameReviewsRepository.sendReview(context, GameReview(3, "dobro", idIGRE, false, "", ""))
        assert(!rez) { "Should return false" }
        executeCountAndCheck(countNotOnline, "broj_reviews", 1)
    }

    @Test
    fun a4_enableInternetAndSendOffline() = runBlocking {
        var uia = InstrumentationRegistry.getInstrumentation().uiAutomation
        uia.executeShellCommand("svc wifi enable")
        uia.executeShellCommand("svc data enable")
        Thread.sleep(2000)
        var rez = GameReviewsRepository.sendOfflineReviews(context)
        assertEquals(rez, 1)
        executeCountAndCheck(countNotOnline, "broj_reviews", 0)

    }

 */

    @Test
    fun a5_getOnlineReviewsForGame() = runBlocking {
        var rez = GameReviewsRepository.getReviewsForGame(idIGRE, context)
        assertEquals(rez.size, 1)
    }

    @Test
    fun a6_deleteAllAndCheckNumReviewsOnline() = runBlocking {
        obrisi()
        var rez = GameReviewsRepository.getReviewsForGame(idIGRE, context)
        assertEquals(rez.size, 0)
    }


}



