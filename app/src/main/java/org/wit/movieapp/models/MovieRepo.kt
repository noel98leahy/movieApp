package org.wit.movieapp.models

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object class used to get movies from api
 * makes use of a singleton pattern so that only one
 * instance is created
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */

object MovieRepo {

    //reference to api
    val api: MovieApi

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/") //gets the json
            .addConverterFactory(GsonConverterFactory.create()).build() //converts it

        api = retrofit.create(MovieApi::class.java) //assignment
    }

    //gets popular movies
        fun getPopMovie(page: Int = 1, success: (movies: List<MovieModel>) -> Unit, error: () ->Unit) {
        api.getPopularMovies(page = page).enqueue(object : Callback<GetMovies> {
            override fun onResponse(
                call: Call<GetMovies>, response: Response<GetMovies>
            ) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null) {
                       success.invoke(responseBody.movies)
                    } else{
                       error.invoke()
                    }
                }
            }
            override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                error.invoke()
            }
        })
    }


    //get top rated movies
    fun getTopRatedMovies(page: Int = 1, success: (movies: List<MovieModel>) -> Unit, error: () -> Unit){
        api.getTopRatedMovies(page = page).enqueue(object : Callback<GetMovies>{

            override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>){

            if(response.isSuccessful){
                val responseBody = response.body()
                if (responseBody != null) {
                    success.invoke(responseBody.movies)
                } else {
                    error.invoke()
                }
            }

    }

            override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                error.invoke()
            }
})
    }
}