package com.telesoftas.lithuaniaindependencynews.main

import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.telesoftas.lithuaniaindependencynews.R
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import com.telesoftas.lithuaniaindependencynews.utils.formater.DateFormaterImpl
import kotlinx.android.synthetic.main.view_article_item.view.*


class ArticlesAdapter(
        val glide: RequestManager,
        val list: List<Article>,
        private val clickListener: (Article, ImageView) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.view_article_item, parent,
                false
        )
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        val marginInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_MARGIN_DP,
                parent.resources.displayMetrics
        ).toInt()
        lp.setMargins(marginInPx, marginInPx, marginInPx, marginInPx)
        view.layoutParams = lp
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int, clickListener: (Article, ImageView) -> Unit) {
            val articleData = list[position]
            view.articleTextView.text = articleData.title
            view.articlePublishedTextView.text =
                    DateFormaterImpl().getFormatedString(articleData.publishedAt)
            val placeholder = ContextCompat.getDrawable(view.context, R.drawable.img_placeholder)
            if (articleData.urlToImage != null) {
                glide
                        .load(articleData.urlToImage)
                        .error(glide
                                .load(placeholder))
                        .into(view.articleImageView)
            } else {
                glide.clear(view.articleImageView)
                view.articleImageView.setImageDrawable(placeholder)
            }
            ViewCompat.setTransitionName(
                    view.articleImageView,
                    articleData.title + articleData.publishedAt)
            view.setOnClickListener { clickListener.invoke(articleData, view.articleImageView) }
        }
    }

    companion object {
        const val DEFAULT_MARGIN_DP = 4f
    }
}