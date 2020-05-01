package com.example.usecases

import com.example.data.repository.MoviesRepository
import com.example.domain.Movie

class ToggleMovieFavorite(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(movie: Movie) = with(movie) {
        copy(favorite = !favorite).also {
            moviesRepository.updateMovie(it)
        }
    }

}