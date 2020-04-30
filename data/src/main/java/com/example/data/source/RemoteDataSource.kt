package com.example.data.source

import com.example.domain.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(apiKey: String, findLastRegion: String): List<Movie>
}