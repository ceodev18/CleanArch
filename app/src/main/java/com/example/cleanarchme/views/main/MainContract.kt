package com.example.cleanarchme.views.main

import com.example.domain.Movie

interface MainContract {

    interface MainView {
        fun setupMovies(movies: List<Movie>)
    }

    interface MainPresenter {
        fun attach(view: MainView)
        fun detach()
        fun onLoadMovies()
    }

    interface MainInteractor {
        suspend fun retrieveMovies() : List<Movie>
    }

}