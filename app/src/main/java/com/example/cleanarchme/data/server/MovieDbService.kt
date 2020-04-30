package com.example.cleanarchme.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MovieDbResult
}