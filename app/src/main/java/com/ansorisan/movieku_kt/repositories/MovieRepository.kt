package com.ansorisan.movieku_kt.repositories

import com.ansorisan.movieku_kt.BuildConfig
import com.ansorisan.movieku_kt.api.ApiConfig

class MovieRepository {
    private val client = ApiConfig.getApiService()

    suspend fun getPopularMovies(page: Int) = client.getPopularMovies(BuildConfig.API_KEY, page)
    suspend fun searchMovie(query: String, page: Int) = client.searchMovies(BuildConfig.API_KEY, query, page)
    suspend fun getGenres() = client.getGenres(BuildConfig.API_KEY)
}