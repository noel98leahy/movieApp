package org.wit.movieapp.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import org.wit.movieapp.R
import org.wit.movieapp.models.MovieModel
import org.wit.movieapp.models.MovieRepo

/**
 * Top rated movies listed
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */

class TopRatedMoviesActivity: AppCompatActivity()  {

    private lateinit var rc: RecyclerView
    private lateinit var movie: MovieAdapter
    private lateinit var topRatedLayout: LinearLayoutManager
    var topRatedPage = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_rated)

        //sets the recyclerView to top rated movies layout
        rc = findViewById(R.id.top_rated_movies)
        topRatedLayout = LinearLayoutManager(this)
        rc.layoutManager = topRatedLayout
        movie = MovieAdapter(mutableListOf()) {movie -> showDetails(movie)  }//mutable list so we can add when we scroll
        rc.adapter = movie


        getTopRatedMovies()

    }

    private fun getTopRatedMovies() {
        MovieRepo.getTopRatedMovies(topRatedPage, ::onGetUpComingMovies, ::onError)
    }

    private fun onGetUpComingMovies(movies: List<MovieModel>) {
        movie.addMovies(movies)
        scrollListener()
    }

    private fun onError() {
        Toast.makeText(this, "Failed to Add Upcoming Movies from API", Toast.LENGTH_SHORT).show()
    }

    private fun scrollListener() {
        rc.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItem = topRatedLayout.findFirstVisibleItemPosition()
                val totalItem = topRatedLayout.itemCount
                val visibleItem = topRatedLayout.itemCount

                if (firstItem + visibleItem >= totalItem / 3)
                    rc.removeOnScrollListener(this)
                topRatedPage++
                getTopRatedMovies()
            }
        })
    }



    //adds movie details to the keys to be passed to new activity
   private fun showDetails(movie: MovieModel) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(DETAILS_BACKDROP, movie.backdropPath)
        intent.putExtra(DETAILS_TITLE, movie.title)
        intent.putExtra(DETAILS_DATE, movie.releaseDate)
        intent.putExtra(DETAILS_RATING, movie.rating)
        intent.putExtra(DETAILS_OVERVIEW, movie.overview)

        startActivity(intent)
    }


}