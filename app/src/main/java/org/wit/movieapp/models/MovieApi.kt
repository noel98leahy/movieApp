package org.wit.movieapp.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API interface that has references to popular movies and top rated
 * contains my api key
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "7e9bd64abb53a09e54b35bfad502fdf5",
        @Query("page") page: Int
    ): Call<GetMovies>

@GET("movie/top_rated")
    fun getTopRatedMovies(
    @Query("api_key") apiKey: String = "7e9bd64abb53a09e54b35bfad502fdf5",
    @Query("page") page: Int
): Call<GetMovies>

}