package ba.etf.rma23.projekat.data.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import ba.etf.rma23.projekat.R

class ReviewFragment : Fragment() {
    private lateinit var reviewTextView: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var reviewButton: Button
    private lateinit var ratingButton: Button
    private lateinit var button: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review, container, false)

        reviewTextView = view.findViewById(R.id.review_textview)
        ratingBar = view.findViewById(R.id.ratingBar)
        reviewButton = view.findViewById(R.id.review_button)
        ratingButton = view.findViewById(R.id.rating_button)
        button = view.findViewById(R.id.button_send)


        return view
    }
}
