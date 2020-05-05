package org.wit.movieapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create.*
import org.wit.movieapp.R

/**
 * Create an Account activity to allow users to create accounts
 *
 * @author Noel Leahy
 * @date 05/05/2020
 */
class CreateAccActivity: AppCompatActivity() {


    //ui
    private lateinit var eUserName: EditText
    private lateinit var eFName: EditText
    private lateinit var eLName: EditText
    private lateinit var eEmail: EditText
    private lateinit var ePassword: EditText


    //firebase
    private lateinit var ref: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    //local variables
    private lateinit var userName: String
    private lateinit var fName: String
    private lateinit var lName: String
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        eUserName = findViewById(R.id.eUserName)
        eFName = findViewById(R.id.eFName)
        eLName = findViewById(R.id.eLName)
        eEmail = findViewById(R.id.eEMail)
        ePassword = findViewById(R.id.ePassword)


        database = FirebaseDatabase.getInstance()
        ref = database!!.reference!!.child("Users")
        auth = FirebaseAuth.getInstance()


        createBtn.setOnClickListener {
           createAcc()
        }


    }


    //handles creating account
    fun createAcc (){

        //takes user input and puts them into variables
        fName = eFName.text.toString()
        lName = eLName.text.toString()
        userName = eUserName.text.toString()
        email = eEmail.text.toString()
        password = ePassword.text.toString()


        //checks if valid input
        if(!valid(userName,email,password,fName,lName) ){
            return
        }

        //create account with password and email
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { test ->
            if (test.isSuccessful) {
                Log.e("Created Account:", "Account Created!")
                Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show()

                val userId = auth!!.currentUser!!.uid //gives unique user id


                //verify email user used
                verifyEmail() //verify email sent to user

                //update user database
                val currentUser = ref.child(userId) //gets the correct user path
                //adds user input to firebase storage
                currentUser.child("firstName").setValue(fName)
                currentUser.child("lastName").setValue(lName)
                currentUser.child("userName").setValue(userName)

                //change activity after user creates account
                val intent = Intent(this, SignInActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//prevents going back
                this?.startActivity(intent)
            } else {
                Log.e("Created Account", "Account not Created")
                Toast.makeText(this, "Account Failed", Toast.LENGTH_LONG).show()
            }
        }
    }



    //verify email
    fun verifyEmail(){

        val currentUser = auth.currentUser//gets current user
        //calls sendEmailVerification method
        currentUser!!.sendEmailVerification().addOnCompleteListener(this){ todo ->
            if(todo.isSuccessful){
                Log.e("$this", "Sent Verifying email to " + currentUser?.email)
                Toast.makeText(this, "Email sent to " + currentUser?.email, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed to send verifying email", Toast.LENGTH_LONG).show()
                Log.e("VerifyFailed", "Failed to send verifying email")
            }
        }
    }


    //checks validation for user input
    fun valid(username: String, email: String, password: String, fname: String, lname: String): Boolean {

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter an Email", Toast.LENGTH_LONG).show()
            return false
        }
        if(!email.contains("@") && !email.contains(".")){
            Toast.makeText(this, "Email must contain '@' and '.'", Toast.LENGTH_LONG).show()
            return false
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter a Password", Toast.LENGTH_LONG).show()
            return false
        }
        if (password.length < 5) {
            Toast.makeText(this, "Password to short, must be 5 or greater", Toast.LENGTH_LONG)
                .show()
            return false
        }
        if (username.length < 5){
            Toast.makeText(this,"Username too short, must be 5 or greater", Toast.LENGTH_SHORT).show()
            return false
        }
        if(fname.isEmpty()){
            Toast.makeText(this, "First Name is Empty, please enter first name", Toast.LENGTH_SHORT).show()
            return false
        }
        if(lname.isEmpty()){
            Toast.makeText(this, "Second Name is empty, please enter second name", Toast.LENGTH_SHORT).show()
            return false
        }

            return true
    }

}

