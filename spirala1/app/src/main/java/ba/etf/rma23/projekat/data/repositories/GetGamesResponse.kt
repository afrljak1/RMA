package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class GetGamesResponse(
    @SerializedName("name") val title: String,
    @SerializedName("platform") val games: List<Game> = listOf(),
    @SerializedName("first_release_date") val releaseDate: String,
)

