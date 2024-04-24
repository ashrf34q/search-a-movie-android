package com.example.searchmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel: ViewModel() {

    val movieTitle = MutableLiveData<String>()
    val moviePlot = MutableLiveData<String>()
    val moviePoster = MutableLiveData<String>()

    fun setMovieData(title: String, plot: String, poster: String) {
        movieTitle.value = title
        moviePlot.value = plot
        moviePoster.value = poster
    }

}