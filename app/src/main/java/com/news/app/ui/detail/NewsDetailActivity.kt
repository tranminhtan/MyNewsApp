package com.news.app.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.news.app.BR
import com.news.app.R
import com.news.app.model.ArticleItem
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        const val EXTRA_ARTICLE = "id"
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)
        val id: Int = intent.extras?.getInt(EXTRA_ARTICLE) ?: 0

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsDetailViewModel::class.java)
        lifecycle.addObserver(viewModel)

        viewModel.article.observe(this, { article: ArticleItem ->
            val visibility = if (article.description.isEmpty()) View.GONE else View.VISIBLE
            binding.setVariable(BR.item, article)
            binding.setVariable(BR.spaceVisibility, visibility)
        })
        viewModel.fetchArticle(id)
    }
}