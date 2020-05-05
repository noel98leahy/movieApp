package org.wit.movieapp.activities

import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log

import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_sign_in.*

import org.wit.movieapp.R

/**
 * Sign In Activity to handle the signing in of accounts
 * @author Noel Leahy
 * @date 05/05/2020
 */
class SignInActivity: AppCompatActivity() {


    //firebase
    private lateinit var ref: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    //ui
    private lateinit var currentUser: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        Log.e("SignInAcc", "The sign in activity")

        //init firebase
        database = FirebaseDatabase.getInstance()
        ref = database!!.reference!!.child("Users")
        auth = FirebaseAuth.getInstance()

        //currentUser = findViewById(R.id.currentUser)



        //handles sign in method when button to press
        signIn.setOnClickListener {
            signIn(email.text.toString(), password.text.toString())
        }

        Toast.makeText(this, "Please Sign In here", Toast.LENGTH_LONG).show()
    }

    //function to sign in
    fun signIn(email: String, password: String) {
        Log.e("$this", "Signing in with " + email)
        //checks email and password
        if (!valid(email, password)) {
            return
        }
        //calls signIn method
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { job ->
            if (job.isSuccessful) { //successful sigined in is true
                Log.e("$this", "Successfuly Logged In")
               val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//prevents going back
                  this?.startActivity(intent)

               // updateUI()
            } else {
                Log.e("$this", "Failed to log in")
                Toast.makeText(this, "Incorrect Password or Email", Toast.LENGTH_LONG).show()

            }
        }
    }

    //validation for user input
    fun valid(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) { //if empty
            Toast.makeText(this, "Please Enter an Email", Toast.LENGTH_LONG).show()
            return false
        }
        if (!email.contains("@") && !email.contains(".")) { //doesn't contain @ or .
            Toast.makeText(this, "Email must contain '@' and '.'", Toast.LENGTH_LONG).show()
            return false
        }
        if (TextUtils.isEmpty(password)) { //password
            Toast.makeText(this, "Please Enter a Password", Toast.LENGTH_LONG).show()
            return false
        }
        if (password.length < 5) { //too short
            Toast.makeText(this, "Password to short, must be 5 or greater", Toast.LENGTH_LONG)
                .show()
            return false
        }

        return true  //if all passes the returns true
    }


    //update ui in nav drawer so when signed in username goes onto it
    fun updateUI(){
        val curUser = auth.currentUser
        val userRef = ref.child("User")


        userRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("ErrorAdding", "Failed to add to menu")
            }

            override fun onDataChange(p0: DataSnapshot) {
                currentUser!!.text = p0.child("firstName").value as String
            }

        })


    }


}

