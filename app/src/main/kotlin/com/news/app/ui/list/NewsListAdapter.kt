package com.news.app.ui.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.news.app.BR
import com.news.app.R
import com.news.app.model.ArticleItem
import com.news.app.ui.detail.NewsDetailActivity

class NewsListAdapter : ListAdapter<ArticleItem, ArticleViewModel>(ArticleDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewModel {
        return ArticleViewModel(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewModel, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = R.layout.item_news_list
}

class ArticleViewModel(
    private val binding: ViewDataBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(articleItem: ArticleItem) {
        val onClickListener: View.OnClickListener = View.OnClickListener { view ->
            navigateToDetail(view.context, articleItem.id)
        }
        binding.setVariable(BR.item, articleItem)
        binding.setVariable(BR.onClickListener, onClickListener)
        binding.executePendingBindings()
    }

    private fun navigateToDetail(context: Context, id: Int) {
        val intent = Intent(context, NewsDetailActivity::class.java)
        intent.putExtra(NewsDetailActivity.EXTRA_ARTICLE, id)
        context.startActivity(intent)
    }
}

class ArticleDiffUtil : DiffUtil.ItemCallback<ArticleItem>() {
    override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean =
        oldItem.title == newItem.title && oldItem.imageUrl == newItem.imageUrl
}