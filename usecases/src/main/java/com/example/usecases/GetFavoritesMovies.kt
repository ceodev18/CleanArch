package com.example.usecases

import com.example.data.repository.MoviesRepository
import com.example.domain.Movie

class GetFavoritesMovies(private val moviesRepository: MoviesRepository) {

    suspend fun invoke() : List<Movie> {
        return moviesRepository.favoriteMovies()
    }

}