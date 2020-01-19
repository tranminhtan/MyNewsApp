package com.news.app.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.news.app.BR
import com.news.app.R
import com.news.app.ui.list.ArticleItem

class NewsDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTICLE = "article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)
        val article: ArticleItem = (intent.extras!!.getParcelable(EXTRA_ARTICLE)!!)
        val visibility = if (article.description.isNotEmpty()) View.VISIBLE else View.GONE
        binding.setVariable(BR.item, article)
        binding.setVariable(BR.spaceVisibility, visibility)

    }
}