package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
//import ba.etf.rma23.projekat.data.repositories.GameRepository.getAllGames
//import ba.etf.rma23.R
import ba.etf.rma23.projekat.R

class GameDetailsFragment : Fragment() {
    private var game: Game? = null
    private lateinit var title: TextView
    private lateinit var image: ImageView
    private lateinit var platform: TextView
    private lateinit var release_date: TextView
    private lateinit var esrb: TextView
    private lateinit var developer: TextView
    private lateinit var publisher: TextView
    private lateinit var genre: TextView
    private lateinit var descpription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_details, container, false)
        title = view.findViewById(R.id.item_title_textview)
        image = view.findViewById(R.id.cover_imageview)
        platform = view.findViewById(R.id.platform_textview)
        release_date = view.findViewById(R.id.release_date_textview)
        esrb = view.findViewById(R.id.esrb_rating_textview)
        developer = view.findViewById(R.id.developer_textview)
        publisher = view.findViewById(R.id.publisher_textview)
        genre = view.findViewById(R.id.genre_textview)
        descpription = view.findViewById(R.id.description_textview)


        val args = arguments
        if (args != null) {
            val title = args.getString("item_title_textview")
            if (title != null) {
                game = getGameByTitle(title)
                if (game != null) {
                    populateDetails()
                } else
                    activity?.finish()
            }
        }

        return view
    }

    private fun populateDetails() {
        title.text = game?.title
        platform.text = game?.platform.toString()
        release_date.text = game?.releaseDate
        esrb.text = game?.esrbRating
        developer.text = game?.developer
        publisher.text = game?.publisher
        genre.text = game?.genre
        descpription.text = game?.description

        val context: Context = image.context

            /* val id = context.resources
            .getIdentifier(game?.coverImage, "drawable", context.packageName)
        if (id != 0) {
            image.setImageResource(id)

        }
*/
        var id: Int = context.resources
            .getIdentifier(game?.coverImage, "drawable", context.packageName)
        if (id === 0) id = context.resources
            .getIdentifier("pubg", "drawable", context.packageName)
        image.setImageResource(id)
}

    private fun getGameByTitle(name:String): Game?{
        val games: ArrayList<Game> = arrayListOf()
       // games.addAll(getAllGames())
        return games.find { game -> name == game.title }
    }
}

