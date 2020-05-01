package com.example.cleanarchme.views.main

import com.example.domain.Movie

interface MainContract {

    interface MainView {
        fun setupMovies(movies: List<Movie>)
        fun navigateToDetail(id: Int)
    }

    interface MainPresenter {
        fun attach(view: MainView)
        fun detach()
        fun onLoadMovies()
        fun onMovieClick(id: Int)
        fun onLoadFavoritesMovies()
    }

    interface MainInteractor {
        suspend fun retrieveMovies() : List<Movie>
        suspend fun retrieveFavoritesMovies(): List<Movie>
    }

}