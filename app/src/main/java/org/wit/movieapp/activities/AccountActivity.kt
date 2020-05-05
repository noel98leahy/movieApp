package org.wit.movieapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.user_account_activity.*

import org.wit.movieapp.R

/**
 * Shows details of user that is currently signed in
 * also allows users to sign out
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */
class AccountActivity: AppCompatActivity() {

    //ui
    private lateinit var tvEmail: TextView
    private lateinit var tvFName: TextView
    private lateinit var tvLName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvVerified: TextView

    //firebase
    private lateinit var ref: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_account_activity)


        database = FirebaseDatabase.getInstance()
        ref = database!!.reference!!.child("Users")
        auth = FirebaseAuth.getInstance()

        tvEmail = findViewById(R.id.tvEmail)
        tvFName = findViewById(R.id.tvFName)
        tvLName = findViewById(R.id.tvLName)
        tvUsername = findViewById(R.id.tvUserName)
        tvVerified = findViewById(R.id.tvVerified)

        //handles button clicked
        btnSignOut.setOnClickListener{
            signOut()
        }

    }

    //onStart method that gets user info on firebase and prints to screen
    override fun onStart() {
        super.onStart()

        //current user and reference to user
        val user = auth.currentUser
        val userRef = ref.child(user!!.uid)

        //checks if no one is signed in
        if (userRef == null){

            Toast.makeText(this, "No One Signed In, Please Sign In", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            //if no one is signed in reutrns to main activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//prevents going back
            this?.startActivity(intent)
        }else {


            tvEmail.text = user.email //gets user email
            tvVerified.text = user.isEmailVerified.toString() //if verified prints true, else false

            userRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.e("failed", "Failed to add user")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    //adds user info to ui
                    tvFName.text = p0.child("firstName").value as String
                    tvLName.text = p0.child("lastName").value as String
                    tvUsername.text = p0.child("userName").value as String

                }

            }
            )
        }

    }



    //sign out method
    fun signOut() {
        FirebaseAuth.getInstance().signOut() //gets the reference to the user
        Toast.makeText(this,"Signing Out", Toast.LENGTH_SHORT).show()
        //return back toi main activity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//prevents going back
        this?.startActivity(intent)
    }
}