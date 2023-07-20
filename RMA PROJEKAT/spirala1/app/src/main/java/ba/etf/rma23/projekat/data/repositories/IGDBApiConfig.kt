package ba.etf.rma23.projekat.data.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IGDBApiConfig {


   /* interface Api {
        @Headers(
            "Client-ID: sianxn2x02d0941g0q1j492rtghf7i",
            "Authorization: Bearer 3gbqw5t3yx8y6fyd7b7wppqw07si4r")
        @POST("games")
        suspend fun getGamesByName(): Response<List<Game>>
    }*/

    companion object{
        var baseURL = "https://api.igdb.com/v4/"
    }

    fun postaviBaseURL(baseUrl: String): Unit {
        AccountApiConfig.baseURL = baseUrl
    }

    object Adapter{
        val retrofit: Api = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
   }




}