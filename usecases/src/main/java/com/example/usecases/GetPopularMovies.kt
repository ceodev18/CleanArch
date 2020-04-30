package com.example.usecases

import com.example.data.repository.MoviesRepository

class GetPopularMovies(private val moviesRepository: MoviesRepository) {

    suspend fun invoke() = moviesRepository.popularMovies()

}