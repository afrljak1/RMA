package ba.etf.rma23.projekat.data.repositories
import ba.etf.rma23.projekat.data.repositories.GamesRepository.getGamesByName
import kotlinx.coroutines.*


sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}




object GamesRepository {

    private val api: Api = IGDBApiConfig.Adapter.retrofit
    private var userAge: Int? = null


    suspend fun searchRequest(
        query: String
    ) : GetGamesResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.searchGames(query)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

    suspend fun getGamesByName(name: String): List<Game> {
        return withContext(Dispatchers.IO) {
            val response = IGDBApiConfig.Adapter.retrofit.getGamesByName(name)
            var responseBody = "fields name, platforms.name, first_release_date, rating, cover.url"
           // val responseBody = response.body()
            if (response.isSuccessful) {
                 responseBody = response.body().toString()
                return@withContext (responseBody) as List<Game>
            } else {
                emptyList()
            }
        }
    }

    suspend fun getGamesSafe(name: String): List<Game> {
        if (userAge == null) { //ako gine korisnika nisu postavljene ili su null vraca se prazna lista
            return emptyList()
        }
        val games = getGamesByName(name)
        return games.filter { game -> game.isSafeForUserAge() }
    }


    fun setUserAge(age: Int) {
        userAge = age
    }
}

private fun Game.isSafeForUserAge(): Boolean {
    val userAge : Int = 15
    return userAge != null
}




   /*fun getName() {
        val scope = CoroutineScope(Job() + Dispatchers.IO /*+ coroutineExceptionHandler*/)
        scope.launch {
            // Vrti se poziv servisa i suspendira se rutina dok se `withContext` ne zavrsi
            //print("REZULTAT ")

            val result = getGamesByName("Mario")   //prva

            //val result = GamesRepository.getGamesSafe("Hitman") //druga

            print("REZULTAT " + result)
            //println("asdasd")
            //println(result.toString())
        }

    }


    */





/*val scope = CoroutineScope(Job() + Dispatchers.IO /*+ coroutineExceptionHandler*/)
        scope.launch {
            // Vrti se poziv servisa i suspendira se rutina dok se `withContext` ne zavrsi
            //print("REZULTAT ")

            val result = GamesRepository.getGamesByName("Mario")   //prva

            //val result = GamesRepository.getGamesSafe("Hitman") //druga

            print("REZULTAT "+result)
            //println("asdasd")
            //println(result.toString())

 */