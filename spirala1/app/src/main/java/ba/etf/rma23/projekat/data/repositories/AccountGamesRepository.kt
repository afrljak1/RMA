package ba.etf.rma23.projekat.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class AccountGamesRepository {
    companion object {
        private var hash: String = "32501d34-89ae-4bc3-91b9-c40395e66d96"
        private var userAge: Int? = null
        private val api: Api = AccountApiConfig.Adapter.retrofit

        @JvmStatic
        fun getHash(): String {
            return hash
        }

        fun setHash(acHash: String): Boolean {
            hash = acHash
            return true
        }

        fun setAge(age: Int): Boolean { //funkcija setAge za lokalno postavljanje godina
            return if (age in 3..100) {
                userAge = age
                true
            } else {
                false
            }
        }

        fun getUserAge(): Int? { //pomocna funkcija za dobavljanje userAge
            return userAge
        }


        suspend fun removeNonSafe(aid: String): Boolean {
            return withContext(Dispatchers.IO) {
                val response = AccountGamesRepository.api.removeNonSafeGames(aid)
                response.isSuccessful
            }
        }
    }
}