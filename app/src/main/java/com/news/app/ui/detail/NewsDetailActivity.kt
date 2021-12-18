package com.news.app.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.news.app.BR
import com.news.app.R
import com.news.app.model.ArticleItem
import com.news.app.usecase.FetchArticleUseCase
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class NewsDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var fetchArticleUseCase: FetchArticleUseCase

    companion object {
        const val EXTRA_ARTICLE = "id"
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)
        val id: Int = intent.extras?.getInt(EXTRA_ARTICLE) ?: 0

        fetchArticleUseCase.fetchArticleById(id)
            .subscribe(
                { article: ArticleItem ->
                    val visibility = if (article.description.isEmpty()) View.GONE else View.VISIBLE
                    binding.setVariable(BR.item, article)
                    binding.setVariable(BR.spaceVisibility, visibility)
                },
                { error: Throwable -> Timber.w(error) }
            )
    }
}