package org.wit.movieapp.Fragments

/**
 * Fragment of MainActivity
 *
 * This class will allow users to login in (WIP)
 *
 * @author Noel Leahy
 * @version 1
 * @date 03/04/2020
 */
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_create.*
import org.wit.movieapp.R
import org.wit.movieapp.activities.CreateAccActivity



class CreateAccFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     return inflater.inflate(R.layout.fragment_create, null)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnCreate.setOnClickListener {

           val intent = Intent(activity, CreateAccActivity::class.java)
            activity?.startActivity(intent)
        }

    }

}
