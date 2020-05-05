package org.wit.movieapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_accounts.*
import org.wit.movieapp.R
import org.wit.movieapp.activities.AccountActivity

/**
 * Account Fragment that has button to send you to
 * account details
 * @author Noel Leahy
 * @date 05/05/2020
 */
class AccountFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAccount.setOnClickListener{
            val intent = Intent(activity, AccountActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}