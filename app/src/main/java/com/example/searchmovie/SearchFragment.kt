package com.example.searchmovie

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.example.searchmovie.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {


    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var activityCallback: SearchDataListener



    interface SearchDataListener {

        fun onUserInput(movieName: String)

        fun onBookmark() // since we're going to be using viewModel, don't pass anything here. In MainActivity
        // just pull from the ViewModel and store in SQLite
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            activityCallback = context as SearchDataListener
        }
        catch (e : ClassCastException) {
            throw ClassCastException("$context must implement SearchDataListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)

//        searchBinding.saveFab.visibility = View.GONE

        return searchBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchBinding.searchView.setOnQueryTextListener (object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                activityCallback.onUserInput(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.w("onQueryChange", newText.toString())
                return true
            }

        })


    }

}