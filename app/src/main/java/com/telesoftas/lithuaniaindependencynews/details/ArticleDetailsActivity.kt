package com.telesoftas.lithuaniaindependencynews.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.telesoftas.lithuaniaindependencynews.GlideApp
import com.telesoftas.lithuaniaindependencynews.R
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import com.telesoftas.lithuaniaindependencynews.utils.formater.DateFormaterImpl
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        initToolbar()
        setUpView()
        populateViewsWithData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private fun setUpView() {
        val artictle = intent.getParcelableExtra<Article>(KEY_ARTICLE)
        readOnlineButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(artictle.url))
            startActivity(intent)
        }

    }


    private fun populateViewsWithData() {
        val artictle = intent.getParcelableExtra<Article>(KEY_ARTICLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.transitionName = artictle.title + artictle.publishedAt
        }
        titleTextView.text = artictle.title
        descriptionTextView.text = artictle.description
        authorTextView.text = artictle.author
        articlePublishedTextView.text = DateFormaterImpl().getFormatedString(artictle.publishedAt)
        GlideApp
                .with(this)
                .load(artictle.urlToImage)
                .placeholder(R.drawable.img_placeholder_large)

                .into(imageView)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
        collapsingToolbar.title = getString(R.string.app_name)
        collapsingToolbar.setExpandedTitleColor(
                ContextCompat.getColor(this, android.R.color.transparent)
        )
    }

    companion object {
        const val KEY_ARTICLE = "key.article"
        fun createIntent(context: Context, article: Article): Intent {
            val intent = Intent(
                    context,
                    ArticleDetailsActivity::class.java
            )
            intent.putExtra(KEY_ARTICLE, article)
            return intent
        }
    }
}
