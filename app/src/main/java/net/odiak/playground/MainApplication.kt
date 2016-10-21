package net.odiak.playground

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.picasso.Picasso

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        Picasso.with(this).setIndicatorsEnabled(true)
    }
}
