package com.example.usecases

import com.example.data.repository.MoviesRepository
import com.example.domain.Movie

class GetMovieById(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(id: Int): Movie {
        return moviesRepository.findById(id)
    }
}