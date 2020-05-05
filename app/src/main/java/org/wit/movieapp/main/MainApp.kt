package org.wit.movieapp.main
/**
 * Main Class that will start the app
 *
 * @author Noel Leahy
 * @version 1
 * @date 03/04/2020
 * */

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainApp : Application(), AnkoLogger {




    override fun onCreate() {
        super.onCreate()

        info("Movie Review App started")
    }
}