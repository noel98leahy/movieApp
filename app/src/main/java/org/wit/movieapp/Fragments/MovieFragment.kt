package org.wit.movieapp.Fragments


/**
 * Fragment of MainActivity
 *
 * This class will display a list of movies (WIP)
 *
 * @author Noel Leahy
 * @version 1
 * @date 03/04/2020
 */

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movies.*
import org.wit.movieapp.R
import org.wit.movieapp.activities.PopMoviesActivity
import org.wit.movieapp.activities.TopRatedMoviesActivity

class MovieFragment : Fragment(){





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

           val rootView =  inflater.inflate(R.layout.fragment_movies, null)
            return rootView


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        popMovie.setOnClickListener {

            val intent = Intent(activity, PopMoviesActivity::class.java)
            activity?.startActivity(intent)
        }

        topRatedBtn.setOnClickListener {
            val intent = Intent(activity, TopRatedMoviesActivity::class.java)
            activity?.startActivity(intent)
        }


    }

}