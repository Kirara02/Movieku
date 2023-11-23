package com.ansorisan.movieku_kt.listeners

import com.ansorisan.movieku_kt.models.Movies

interface OnMovieClickListener {
    fun onMovieClick(movies: Movies, genres: String)
}