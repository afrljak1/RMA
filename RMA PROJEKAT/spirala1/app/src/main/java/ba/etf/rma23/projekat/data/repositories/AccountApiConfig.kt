package ba.etf.rma23.projekat.data.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AccountApiConfig {

    companion object {
        var baseURL = "https://rma23ws.onrender.com"
    }

    fun postaviBaseURL(baseUrl: String): Unit {
        baseURL = baseUrl
    }


    object Adapter {
        val retrofit: Api = Retrofit.Builder()
            .baseUrl(IGDBApiConfig.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

}

