package org.wit.movieapp.Fragments

/**
 * Fragment of MainActivity
 * This class allows users to sign in (WIP)
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
import kotlinx.android.synthetic.main.fragment_signin.*
import org.wit.movieapp.R
import org.wit.movieapp.activities.SignInActivity

class SignInFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signin, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInBtn.setOnClickListener {
         val intent = Intent(activity, SignInActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}


