package com.example.cleanarchme.views.detail

import com.example.domain.Movie
import com.example.usecases.GetMovieById

class DetailInteractorImpl(private val getMovieById: GetMovieById) : DetailContract.DetailInteractor {

    override suspend fun findMovieById(id: Int): Movie {
        return getMovieById.invoke(id)
    }
}