package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GameReviewApi{
    @POST("/account/{aid}/game/{gid}/gamereview")
    suspend fun sendReview(@Body gameReview: GameReview): Response<ResponseBody>

    @GET("/game/{gid}/gamereviews") suspend fun getReviewsForGame(@Body idIGRE: Int): Response<ResponseBody>
}


class GameReviewsRepository(private val gameReviewDao: GameReviewDao, private val context: Context) {

     suspend fun getGameReviewDao(): GameReviewDao {
        return gameReviewDao
    }

    suspend fun getOfflineReviews(): List<GameReview> {
        return gameReviewDao.getOfflineReviews()
    }

    suspend fun sendOfflineReviews(): Int {
        val offlineReviews = getOfflineReviews()
        val reviewIds = offlineReviews.map { it.igdb_id }
        gameReviewDao.markReviewsAsOnline(reviewIds)
        return offlineReviews.size
    }

    suspend fun getReviewsForGame(idIGRE: Int): List<GameReview> {
        return gameReviewDao.getReviewsForGame(idIGRE)
    }


   /* private fun sendReviewsToWebService(reviews: List<GameReview>): Int {
        var successfullySentCount = 0
        for (review in reviews) {
            if (sendReviewToWebService(review)) {
                successfullySentCount++
            }
        }
        return successfullySentCount
    }
}

    */


    companion object {
        suspend fun getOfflineReviews(context: Context): List<GameReview> {
            val database = GameReviewDatabase.getDatabase(context)
            val gameReviewDao = database.gameReviewDao()
            return gameReviewDao.getOfflineReviews()
        }

        suspend fun sendOfflineReviews(context: Context): Int {
            val database = GameReviewDatabase.getDatabase(context)
            val gameReviewDao = database.gameReviewDao()
            return GameReviewsRepository(gameReviewDao, context).sendOfflineReviews()
        }


        suspend fun getReviewsForGame(idIGRE: Int, context: Context): List<GameReview> {
            val database = GameReviewDatabase.getDatabase(context)
            val gameReviewDao = database.gameReviewDao()
            val repository = GameReviewsRepository(gameReviewDao, context)
            return repository.getReviewsForGame(idIGRE)
        }


        suspend fun sendReview(context: Context, gameReview: GameReview): Boolean {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://rma23ws.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val gameReviewApi = retrofit.create(GameReviewApi::class.java)

            try {
                val response = gameReviewApi.sendReview(gameReview)
                return response.isSuccessful
            } catch (e: Exception) {
                // Slanje nije uspješno, snimi kopiju recenzije u bazu
                val gameReviewDao = GameReviewDatabase.getDatabase(context).gameReviewDao()
                gameReviewDao.insertReview(gameReview)
                return false
            }
        }


    }

}







