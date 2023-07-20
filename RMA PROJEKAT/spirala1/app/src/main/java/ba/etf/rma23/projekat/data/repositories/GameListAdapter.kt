package ba.etf.rma23.projekat.data.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import ba.etf.rma23.R
import ba.etf.rma23.projekat.R


class GameListAdapter(
    private var game: List<Game>, private val onItemClicked: (game1: Game) -> Unit

) : RecyclerView.Adapter<GameListAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_videogame, parent, false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int = game.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        holder.game_title.text = game[position].title;

            holder.game_title.text = game[position].title
            holder.game_rating.text = game[position].rating.toString()
            holder.release_date.text = game[position].releaseDate
            holder.game_platform.text = game[position].platform.toString()

        holder.itemView.setOnClickListener{ onItemClicked(game[position]) }

        }


    fun updateGames(game: List<Game>) {
        this.game = game
        notifyDataSetChanged()
    }

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val game_rating: TextView = itemView.findViewById(R.id.game_rating_textview)
        val game_title: TextView = itemView.findViewById(R.id.item_title_textview)
        val release_date: TextView = itemView.findViewById(R.id.game_release_date_textview)
        val game_platform: TextView = itemView.findViewById(R.id.game_platform_textview)
    }
}