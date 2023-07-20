package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import androidx.room.*
import java.io.File


fun deleteDatabase(context: Context, databaseName: String) { //funkcija za brisanje baze podataka
    val databasePath = context.getDatabasePath(databaseName).path
    val databaseFile = File(databasePath)

    if (databaseFile.exists()) {
        val deleted = databaseFile.delete()
        if (deleted) {

            // Baza podataka je uspješno izbrisana
        } else {

            // Pogreška prilikom brisanja baze podataka
        }
    } else {
        // Baza podataka nije pronađena
    }
}

@Dao
interface GameReviewDao {
    @Query("SELECT * FROM gamereview WHERE online = 0")
    suspend fun getOfflineReviews(): List<GameReview>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: GameReview)

    @Update
    suspend fun updateReview(review: GameReview)

    @Transaction
    @Delete
    suspend fun deleteReview(gameReview: GameReview)

    @Query("UPDATE gamereview SET online = 1 WHERE igdb_id IN (:reviewIds)")
    suspend fun markReviewsAsOnline(reviewIds: List<Int>)

    @Query("SELECT * FROM gamereview WHERE igdb_id = :igdb_id")
    suspend fun getReviewsForGame(igdb_id: Int): List<GameReview>
}
