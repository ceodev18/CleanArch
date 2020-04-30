package com.example.cleanarchme.views.main

import com.example.cleanarchme.views.main.MainContract
import com.example.domain.Movie
import com.example.usecases.GetPopularMovies

class MainInteractorImpl(private val getPopularMovies: GetPopularMovies) :
    MainContract.MainInteractor {

    override suspend fun retrieveMovies(): List<Movie> {
        return getPopularMovies.invoke()
    }


}

