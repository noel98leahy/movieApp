package org.wit.movieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *
 * data class for movie
 * uses gson to serialize data
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */
@Parcelize
data class MovieModel(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("genre") val genre: String
): Parcelable