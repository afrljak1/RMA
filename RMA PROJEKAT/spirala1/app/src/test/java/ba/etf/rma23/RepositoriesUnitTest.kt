package ba.etf.rma23
//import  ba.etf.rma23.data.repositories.*
//import  ba.etf.rma23.data.*
import  ba.etf.rma23.projekat.*
import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository
import ba.etf.rma23.projekat.data.repositories.Game
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import kotlinx.coroutines.*

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.net.URL

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RepositoriesUnitTest {

    suspend fun obrisi(){
        var client: OkHttpClient = OkHttpClient()
        var builder: Request.Builder = Request.Builder()
            .url(URL("https://rma23ws.onrender.com/account/" + AccountGamesRepository.getHash() + "/game"))
            .delete()
        var request: Request = builder.build()
        withContext(Dispatchers.IO) {
            var response: Response = client.newCall(request).execute()
        }
    }

    @Test
    fun a0_pripremiPocetak() = runBlocking {
        AccountGamesRepository.setHash("ca0ee672-440b-45b2-8a12-75b80f4fbdd3")
        obrisi()
    }
/*
   @Test
    fun a1_getIgreFromAccount() = runBlocking {
        var res = AccountGamesRepository.getSavedGames()
        assertThat(res.size,CoreMatchers.equalTo(0))
    }


    //ako vam sljedeći test nekada pada, a nekada prolazi provjerite da li je metoda saveGame suspended. Ako nije može se desiti da se završi izvršavanje funkcije nakon što padne provjera jer se izvršava u drugoj niti
    @Test
    fun a2_addOneGetSaved() = runBlocking {
        AccountGamesRepository.saveGame(Game(24273,"Age of Empires: The Age of Kings","","",10.0,"","","","","","",listOf<UserImpression>()))
        AccountGamesRepository.saveGame(Game(47076,"Age of Empires: Gold Edition","","",10.0,"","","","","","",listOf<UserImpression>()))

        var res = AccountGamesRepository.getSavedGames()
        assertThat(res.size,CoreMatchers.equalTo(2))

    }

    @Test
    fun a3_getSavedGameOtherAttributes() = runBlocking {
        var res = AccountGamesRepository.getSavedGames()
        assertThat(res.size,CoreMatchers.equalTo(2))
        assertThat(res.get(0).releaseDate,CoreMatchers.containsString("2006"))
        assertThat(res.get(1).releaseDate,CoreMatchers.containsString("1999"))
    }
*/
    @Test
    fun a4_getGamesByName() = runBlocking {
        var res = GamesRepository.getGamesByName("Age of Empires")
        assertThat(res, CoreMatchers.hasItem<Game>(Matchers.hasProperty("id",CoreMatchers.equalTo(24273))))
    }
/*
    @Test
    fun a5_getGamesSafe() = runBlocking {
        AccountGamesRepository.setAge(10)
        var res = GamesRepository.getGamesSafe("Hitman")
        assertThat(res, CoreMatchers.not(CoreMatchers.hasItem<Game>(Matchers.hasProperty("id",CoreMatchers.equalTo(11157)))))

        var res2 = GamesRepository.getGamesByName("Hitman")
        assertThat(res2, CoreMatchers.hasItem<Game>(Matchers.hasProperty("id",CoreMatchers.equalTo(11157))))
    }

    //ako vam ovaj test nekada pada, a nekada prolazi vjerovatno je ista greška kao u a2
    @Test
    fun a6_obrisiIgre() = runBlocking {
        var res=AccountGamesRepository.getSavedGames()
        assertThat(res.size,CoreMatchers.equalTo(2))
        AccountGamesRepository.removeGame(res.get(0))
        res=AccountGamesRepository.getSavedGames()
        assertThat(res.size,CoreMatchers.equalTo(1))
        AccountGamesRepository.removeGame(res.get(0))
        res=AccountGamesRepository.getSavedGames()
        assertThat(res.size,CoreMatchers.equalTo(0))
    }

    @Test
    fun a7_dodajIgruUOmiljeneISortiraj() = runBlocking {
        AccountGamesRepository.saveGame(Game(24273,"Age of Empires: The Age of Kings","","",10.0,"","","","","","",listOf<UserImpression>()))
        GamesRepository.getGamesByName("Age of Empires")
        var res = GamesRepository.sortGames()
        assertThat(res.get(0).id,CoreMatchers.equalTo(24273))
    }*/
}