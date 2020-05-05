package org.wit.movieapp.models

import com.google.gson.annotations.SerializedName
/**
 * Used to get movies from the api
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */

data class GetMovies (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieModel>,
    @SerializedName("total_pages") val pages: Int
)