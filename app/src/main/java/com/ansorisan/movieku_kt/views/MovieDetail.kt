package com.ansorisan.movieku_kt.views

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.ansorisan.movieku_kt.BuildConfig
import com.ansorisan.movieku_kt.databinding.ActivityMovieDetailBinding
import com.ansorisan.movieku_kt.models.Movies
import com.bumptech.glide.Glide

class MovieDetail : AppCompatActivity() {
    private var _binding: ActivityMovieDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.parcelable<Movies>(movie)?.let { intent.getStringExtra(genres)
            ?.let { genres -> setupData(it, genres) } }
    }

    private fun setupData(movie: Movies, genres: String){
       with(movie) {
           binding.apply {
               Glide.with(this@MovieDetail).load("${BuildConfig.PHOTO_BASE_URL}${movie.posterPath}").into(posterDetail)
               titleDetail.text = title
               releaseDateDetail.text = releaseDate
               ratingText.text = String.format("%.1f", voteAverage)
               ratingBar.rating = voteAverage?.div(2) ?: 0f
               genreDetail.text = genres.dropLast(2)
               overview.text = movie.overview
           }
       }

    }

    private inline fun <reified T: Parcelable> Intent.parcelable(key: String): T? = when {
        Build.VERSION.SDK_INT >= 33 ->getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    companion object {
        const val movie = "movie"
        const val genres = "genres"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}