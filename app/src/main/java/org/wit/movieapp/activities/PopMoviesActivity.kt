package org.wit.movieapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import org.jetbrains.anko.intentFor

import org.wit.movieapp.R
import org.wit.movieapp.models.MovieModel
import org.wit.movieapp.models.MovieRepo

/**
 * List of the popular movies from the API
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */

class PopMoviesActivity: AppCompatActivity() {

    private lateinit var rc: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    private lateinit var popularLayout: LinearLayoutManager
    //we change this later
     var popularPage = 1 //setting the page to 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_movies)
        rc = findViewById(R.id.popular_movies)
        popularLayout = LinearLayoutManager(this)
        rc.layoutManager = popularLayout

        movieAdapter = MovieAdapter(mutableListOf()) {movie -> showDetails(movie)} //mutable list to allow to add movies as we scroll
        rc.adapter = movieAdapter


        getPopulareMovies()

    }




    private  fun getPopulareMovies(){
        //called from the movie repository class
        MovieRepo.getPopMovie(popularPage, ::onGetPopMovie, ::onError)
    }


      private  fun onGetPopMovie(movies: List<MovieModel>) {
            movieAdapter.addMovies(movies)
          scrollListener()
        }

      private  fun onError() {
            Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_LONG)
                .show()
        }


    //used to add new page to when scrolling down
        private  fun scrollListener(){
            rc.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int){
                    val totalItems = popularLayout.itemCount
                    val visibleItems = popularLayout.itemCount
                    val firstItem = popularLayout.findFirstVisibleItemPosition()

                    //if first item plus visible items is greater than total divieded by 3
                    if (firstItem + visibleItems >= totalItems / 3) {
                        rc.removeOnScrollListener(this)
                        popularPage++ //adds new page
                        getPopulareMovies()
                    }
                }

    })
        }


    //function that puts data into the keys to be passed to MovieDetailActivity
    private fun showDetails(movie: MovieModel) {
        //setting the new activity
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(DETAILS_BACKDROP, movie.backdropPath)
        intent.putExtra(DETAILS_TITLE, movie.title)
        intent.putExtra(DETAILS_DATE, movie.releaseDate)
        intent.putExtra(DETAILS_RATING, movie.rating)
        intent.putExtra(DETAILS_OVERVIEW, movie.overview)
        startActivity(intent)
    }



}

