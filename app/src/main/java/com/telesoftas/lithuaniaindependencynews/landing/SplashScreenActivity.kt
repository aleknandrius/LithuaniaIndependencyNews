package com.telesoftas.lithuaniaindependencynews.landing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.telesoftas.lithuaniaindependencynews.main.MainActivity

class SplashScreenActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.createIntent(this))
        finish()
    }
}