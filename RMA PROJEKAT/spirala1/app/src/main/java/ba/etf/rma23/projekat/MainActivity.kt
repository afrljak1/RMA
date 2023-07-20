package ba.etf.rma23.projekat

import android.content.Context
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.etf.rma23.projekat.data.repositories.GameReview
import ba.etf.rma23.projekat.data.repositories.GameReviewDatabase
//import ba.etf.rma23.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")


     override fun onConfigurationChanged(newConfig: Configuration) {
         super.onConfigurationChanged(newConfig)
         if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
             findViewById<BottomNavigationView>(R.id.bottom_nav).visibility= View.GONE
         } else {
             findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.VISIBLE
         }
     }

     override fun onCreate(savedInstanceState: Bundle?) { //implementiran navcontroller
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)


         val database = GameReviewDatabase.getDatabase(this)
         val gameReviewDao = database.gameReviewDao()


         val gameReview = GameReview(rating = 5, review = "Odlična igra!", igdb_id = 21, online = true, "", "")


        /* val databaseName = "gamereviews"
         val context: Context = applicationContext
         GameReviewDatabase.deleteDatabase(context, databaseName)

         */

         GlobalScope.launch {
             gameReviewDao.insertReview(gameReview)
             withContext(Dispatchers.Main) {
             }
         }

         val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         val navController = navHostFragment.navController
         val navView: BottomNavigationView = findViewById(R.id.bottom_nav)

         navView.setupWithNavController(navController)

     }
    }

