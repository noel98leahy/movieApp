package org.wit.movieapp.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.wit.movieapp.Fragments.AccountFragment


import org.wit.movieapp.Fragments.CreateAccFragment
import org.wit.movieapp.Fragments.MovieFragment
import org.wit.movieapp.Fragments.SignInFragment
import org.wit.movieapp.R
import org.wit.movieapp.main.MainApp

/**
 * Main activity that is opened up
 * handles switching between different fragments
 * this is the activity that the fragments are based off
 * @author Noel Leahy
 * @date 05/05/2020
 */
class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    //ui
    lateinit var app: MainApp
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        app = application as MainApp
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        displayFragment(-1)//picks the default screen


    }

    //option to choose different screens
    fun displayFragment(id: Int) {

        val fragments = when (id) {
            R.id.nav_movies -> {
                MovieFragment()
            }

            R.id.nav_create -> {
                CreateAccFragment()
            }
            R.id.nav_signIn -> {
                SignInFragment()
            }
            R.id.nav_account -> {
                AccountFragment()
            }
            else ->  { //by default this fragment will be displayed
                MovieFragment()
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.relative, fragments).commit()


    }

    //what happens when a option is picked
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        displayFragment(item.itemId)

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



}


