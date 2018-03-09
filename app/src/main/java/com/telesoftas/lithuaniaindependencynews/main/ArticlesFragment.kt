package com.telesoftas.lithuaniaindependencynews.main

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.telesoftas.lithuaniaindependencynews.GlideApp
import com.telesoftas.lithuaniaindependencynews.R
import com.telesoftas.lithuaniaindependencynews.dependencyRetriever
import com.telesoftas.lithuaniaindependencynews.utils.base.fragment.BaseNetworkFragment
import com.telesoftas.lithuaniaindependencynews.utils.base.view.BaseNetworkView
import com.telesoftas.lithuaniaindependencynews.utils.entity.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_articles.*

class ArticlesFragment : BaseNetworkFragment(), BaseNetworkView, ArticlesScreen.View {
    private lateinit var presenter: ArticlesScreen.Presenter
    private lateinit var adapter: ArticlesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    var list = ArrayList<Article>()
    var isLastPage = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_articles, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjections()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)
        setupViews()
        presenter.onScreenLoaded()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    private fun setupInjections() {
        val retriever = context!!.dependencyRetriever
        val service = retriever.getService(ArticlesService::class.java)
        val model = ArticlesModel(service)
        val resolver = retriever.networkErrorResolver
        presenter = ArticlesPresenter(model, AndroidSchedulers.mainThread(), resolver)
    }

    private fun setupViews() {
        adapter = ArticlesAdapter(GlideApp.with(this), list, {clickedArticle ->
            onArticleClicked(clickedArticle)
        })
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = OrientationHelper.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)
    }

    private fun onArticleClicked(article: Article) {
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (progressView.visibility==View.GONE && !isLastPage) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    presenter.onLoadNext()
                }
            }
        }
    }

    override fun showArticles(list: List<Article>) {
        this.list.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun setAllItemsLoaded() {
        isLastPage = true
    }

    override fun getProgressView(): View? {
        return progressView
    }

    companion object {
        fun newInstance(): ArticlesFragment {
            return ArticlesFragment()
        }
    }
}