package org.wit.movieapp.activities


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_card.view.*
import org.wit.movieapp.R
import org.wit.movieapp.models.MovieModel

/**
 * Adapter Class used for putting the movie_card view into the recylerView.
 * Also handles onMovieClick function
 * @author Noel Leahy
 * @Date 05/05/2020
 */

class MovieAdapter(
    var movies: MutableList<MovieModel>, val onMovieClick: (movie: MovieModel) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MainHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(p0?.context).inflate(R.layout.movie_card, p0, false))
    }


    override fun getItemCount(): Int = movies.size


    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val movie = movies[holder.adapterPosition]
        holder.bind(movie)

    }

    //shows movies and also adds to the end of the list a new collection
    fun addMovies(movieList: List<MovieModel>){
        var size = this.movies.size
        this.movies.addAll(movieList)
        var newSize = this.movies.size
        notifyItemRangeInserted(size, newSize)

    }


    inner class MainHolder (itemview: View): RecyclerView.ViewHolder(itemview){


        fun bind(movie: MovieModel) {

        val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)


            itemView.item_movie_title.text = movie.title
            itemView.item_movie_rating.text = movie.rating.toString()
            itemView.item_movie_release_date.text = movie.releaseDate
            itemView.item_movie_genre.text = movie.genre
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w342${movie.posterPath}").into(poster)//loads in image using Glide
            itemView.setOnClickListener { onMovieClick.invoke(movie) } //when a movie is clicked this is invoked
        }


        }

    }

