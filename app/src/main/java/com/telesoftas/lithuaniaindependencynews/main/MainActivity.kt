package com.telesoftas.lithuaniaindependencynews.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.telesoftas.lithuaniaindependencynews.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        showFragment()
    }

    private fun showFragment() {
        supportFragmentManager.beginTransaction()
                .add(
                        R.id.fragmentFrameLayout,
                        ArticlesFragment.newInstance(),
                        FRAG_TAG
                ).commit()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    companion object {
        const val FRAG_TAG = "articleFragment"
        fun createIntent(context: Context): Intent {
            return Intent(
                    context,
                    MainActivity::class.java
            )
        }
    }
}
