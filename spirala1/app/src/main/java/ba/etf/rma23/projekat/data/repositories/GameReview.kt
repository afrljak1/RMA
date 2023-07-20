package ba.etf.rma23.projekat.data.repositories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "gamereview")
@Entity
data class GameReview(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rating") val rating: Int?,
    @ColumnInfo(name = "review") val review: String?,
    @ColumnInfo(name = "igdb_id") val igdb_id: Int,
    @ColumnInfo(name = "online") val online: Boolean,
    @ColumnInfo(name = "student") val student: String?,
    @ColumnInfo(name = "timestamp") val timestamp: String?
)


