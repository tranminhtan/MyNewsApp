package com.news.app.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.news.app.BR
import com.news.app.R
import com.news.app.model.ArticleItem
import javax.inject.Inject

class NewsListAdapter @Inject constructor(
    private val onArticleClickListener: OnArticleClickListener
) : ListAdapter<ArticleItem, ArticleViewModel>(ArticleDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewModel {
        return ArticleViewModel(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false),
            onArticleClickListener
        )
    }

    override fun onBindViewHolder(holder: ArticleViewModel, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = R.layout.item_list_news
}

class ArticleViewModel(
    private val binding: ViewDataBinding,
    private val onArticleClickListener: OnArticleClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(articleItem: ArticleItem) {
        binding.setVariable(BR.item, articleItem)
        binding.setVariable(BR.onArticleClickListener, onArticleClickListener)
        binding.executePendingBindings()
    }
}

class ArticleDiffUtil : DiffUtil.ItemCallback<ArticleItem>() {
    override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean =
        oldItem.title == newItem.title && oldItem.imageUrl == newItem.imageUrl
}