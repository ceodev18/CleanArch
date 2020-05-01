package com.example.cleanarchme.views.main

import com.example.cleanarchme.views.main.MainContract
import com.example.domain.Movie
import com.example.usecases.GetFavoritesMovies
import com.example.usecases.GetPopularMovies

class MainInteractorImpl(
    private val getPopularMovies: GetPopularMovies,
    private val getFavoritesMovies: GetFavoritesMovies
) :
    MainContract.MainInteractor {

    override suspend fun retrieveMovies(): List<Movie> {
        return getPopularMovies.invoke()
    }

    override suspend fun retrieveFavoritesMovies(): List<Movie> {
        return getFavoritesMovies.invoke()
    }


}

