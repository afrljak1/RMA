package ba.etf.rma23.projekat.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @Headers(
        "Client-ID: sianxn2x02d0941g0q1j492rtghf7i",
        "Authorization: Bearer 3gbqw5t3yx8y6fyd7b7wppqw07si4r"
    )


    @POST("games/")
    suspend fun getGamesByName(
        @Query("search") name: String): Response<List<Game>>

    @DELETE("/account/{aid}/game")
    suspend fun removeNonSafeGames(@Path("aid") aid: String):  Response<List<Game>>

    @POST("search")
    suspend fun searchGames(
        @Query("query") query: String): Response<GetGamesResponse>




}

suspend fun getGamesByName(name: String): List<Game> {
    return withContext(Dispatchers.IO) {
        val response = IGDBApiConfig.Adapter.retrofit.getGamesByName(name)
        val responseBody = "fields name, platforms.name, first_release_date, rating, cover.url"
        if (response.isSuccessful) {
            val responseBody = response.body()
            return@withContext responseBody ?: emptyList()
        } else {
            emptyList()
        }
    }
}

