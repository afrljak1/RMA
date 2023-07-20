package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.data.repositories.AccountApiConfig.Companion.baseURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiAdapter {
    val retrofit : Api = Retrofit.Builder()
        .baseUrl("https://api.igdb.com/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}



