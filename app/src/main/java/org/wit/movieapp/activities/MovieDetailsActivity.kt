package org.wit.movieapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*

import com.bumptech.glide.Glide
import com.google.firebase.database.*

import org.wit.movieapp.R
import org.wit.movieapp.models.MovieModel

/**
 * Detailed View of a Movie. this activity is viewed when a movie is clicked.
 *
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */



//global keys used to store movie details
const val DETAILS_BACKDROP = "extra_movie_backdrop"
const val DETAILS_TITLE = "extra_movie_title"
const val DETAILS_RATING = "extra_movie_rating"
const val DETAILS_DATE = "extra_movie_release_date"
const val DETAILS_OVERVIEW = "extra_movie_overview"



class MovieDetailsActivity: AppCompatActivity() {


    //ui
    private lateinit var backImage: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var date: TextView
    private lateinit var overview: TextView


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_details)

            backImage = findViewById(R.id.details_backdrop)
            title = findViewById(R.id.details_title)
            rating = findViewById(R.id.details_rating)
            date = findViewById(R.id.details_releaseDate)
            overview = findViewById(R.id.details_overview)



            //checks intent has extras
            val extras = intent.extras
            if(extras != null)
            {
                addDetails(extras)
            } else {
                finish()
            }


        }


    //adds details from the movie clicked using intent.extra and a bundle
    //and keys that temporarily stored the data
       private fun addDetails(extras: Bundle) {

        extras.getString("").let { backdropPath ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .into(backImage)
        }
        title.text = extras.getString(DETAILS_TITLE, "")
        rating.rating =
            extras.getFloat(DETAILS_RATING, 0f) / 2 //divide by two since rating is out of 10
        date.text = extras.getString(DETAILS_DATE, "")
        overview.text = extras.getString(DETAILS_OVERVIEW, "")

    }

}

