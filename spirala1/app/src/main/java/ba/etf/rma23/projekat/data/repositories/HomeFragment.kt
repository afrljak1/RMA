package ba.etf.rma23.projekat.data.repositories

import android.content.IntentFilter
import android.media.CamcorderProfile.getAll
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.R
import kotlinx.coroutines.*


class HomeFragment : Fragment() {

    private lateinit var game_list: RecyclerView
    private lateinit var gameAdapter: GameListAdapter
    private lateinit var searchtext: EditText
    private lateinit var searchButton: Button
    private val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        searchtext = view.findViewById(R.id.search_query_edittext)
        searchButton = view.findViewById(R.id.search_button)

        arguments?.getString("search")?.let {
            searchtext.setText(it)
        }


        game_list = view.findViewById(R.id.game_list)
        game_list.layoutManager = GridLayoutManager(activity, 2)
        game_list.layoutManager = LinearLayoutManager(activity)

        gameAdapter = GameListAdapter(arrayListOf()) { game -> getDetailsGames(game) }
        game_list.adapter = gameAdapter

        searchButton.setOnClickListener{
            onClick();
        }

        return view
    }

    private fun onClick() {
        val toast = Toast.makeText(context, "Search start", Toast.LENGTH_SHORT)
        toast.show()
        search(searchtext.text.toString())
    }
    fun searchDone(games:List<Game>){
        val toast = Toast.makeText(context, "Search done", Toast.LENGTH_SHORT)
        toast.show()
        gameAdapter.updateGames(games)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun search(query: String){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Kreira se Coroutine na UI
        scope.launch{
            // Vrti se poziv servisa i suspendira se rutina dok se `withContext` ne zavrsii
            val result = GamesRepository.searchRequest(query)
            // Prikaze se rezultat korisniku na glavnoj niti
            when (result) {
                is GetGamesResponse -> searchDone(result.games)
                else-> onError()
            }
        }
    }




    private fun removeNonSafeGames() {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            val aid = "55" // ID korisnika na swaggeru
            val success = AccountGamesRepository.removeNonSafe(aid)
            // Ovdje možete obraditi rezultat brisanja igara
            withContext(Dispatchers.Main) {
                // Ažuriranje UI-a
            }
        }
    }


    //pokretanje data corutine

    suspend fun getName() {
        val scope = CoroutineScope(Job() + Dispatchers.IO /*+ coroutineExceptionHandler*/)
        scope.launch {

            val name = "Mario" // Naziv igre koji se pretražuje
            val games = GamesRepository.getGamesByName(name)
            withContext(Dispatchers.Main) {
                gameAdapter.updateGames(games)
            }

            // val result = GamesRepository.getGamesByName("Mario")
            // Display result of the network request to the user

        }
    }

            // Vrti se poziv servisa i suspendira se rutina dok se `withContext` ne zavrsi
            //print("REZULTAT ")

          /*  val result = getGamesByName("Mario")   //prva

            //val result = GamesRepository.getGamesSafe("Hitman") //druga

            print("REZULTAT " + result)
            //println("asdasd")
            //println(result.toString())
        }



    /*
    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)

    game_list = view.findViewById(R.id.game_list)
    game_list.layoutManager = GridLayoutManager(activity, 2)
    game_list.layoutManager = LinearLayoutManager(activity)

    gameAdapter = GameListAdapter(arrayListOf()) { game -> getDetailsGames(game) }
    game_list.adapter = gameAdapter

    // Inicijalizacija Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.igdb.com/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Kreiranje instanci IGDBService
    val igdbService = retrofit.create(IGDBService::class.java)

    // Dohvaćanje podataka o igrama
    val call = igdbService.getGames()
    call.enqueue(object : Callback<List<Game>> {
        override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
            if (response.isSuccessful) {
                val games = response.body()
                games?.let { gameAdapter.updateGames(it.take(10)) } // Prikazivanje samo prvih 10 igara
            } else {
                // Greška prilikom dobavljanja podataka
            }
        }

        override fun onFailure(call: Call<List<Game>>, t: Throwable) {
            // Greška prilikom slanja zahtjeva
        }
    })

    return view
}
     */

           */


    private fun getDetailsGames(game: Game) {
        val bundle = Bundle()
        bundle.putString("item_title_textview", game.title)
        val gameDetailsFragment = GameDetailsFragment()
       gameDetailsFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, gameDetailsFragment)
            .addToBackStack(null)
            .commit()
    }
}
