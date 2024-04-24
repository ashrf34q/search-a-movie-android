package com.example.searchmovie

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.searchmovie.R
import com.example.searchmovie.SavedFragment
import com.example.searchmovie.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import org.json.JSONObject

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener, SearchFragment.SearchDataListener {


    private lateinit var movieViewModel: MovieViewModel
    private lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        dbManager = DBManager(this)
        dbManager.open()

        setFragment(SearchFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.search_icon -> setFragment(SearchFragment())
            R.id.saved_icon -> setFragment(SavedFragment())
        }
        return true
    }


    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutFrgmnt, fragment).commit()
        }
    }

    override fun onUserInput(movieName: String) {

        val url = "http://www.omdbapi.com/?apikey=8479374e&plot=full&t=${movieName}"

        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val movie = JSONObject(response.toString())

                movieViewModel.setMovieData(
                    movie.getString("Title"),
                    movie.getString("Plot"),
                    movie.getString("Poster")
                )
                Log.w("onUserInput", "${movie.getString("Title")}  ${movie.getString("Poster")}")
            },
            {
                Log.w("onUserInput", "Error retrieving $movieName's data")
            })
        queue.add(request)

    }

    override fun onBookmark() {
//        TODO("Load current movie, taken from the ViewModel into the SQLite db")
        dbManager.insert(movieViewModel.movieTitle.toString()!!, movieViewModel.moviePlot.toString()!!)
        Log.w("MainActivity", "Inserted ${movieViewModel.movieTitle.toString()!!} into SQLite")
    }
}