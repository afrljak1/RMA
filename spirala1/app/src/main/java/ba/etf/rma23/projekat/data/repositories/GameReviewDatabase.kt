package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.File


@Database(entities = [GameReview::class], version = 1)
abstract class GameReviewDatabase : RoomDatabase() {
    abstract fun gameReviewDao(): GameReviewDao

    companion object {
        @Volatile
        private var INSTANCE: GameReviewDatabase? = null

        fun getDatabase(context: Context): GameReviewDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameReviewDatabase::class.java,
                    "gamereview"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun deleteDatabase(context: Context, databaseName: String) {
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

    }

}

