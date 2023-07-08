package com.example.appcentnewsapp.ui.fragment.news.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentnewsapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.databinding.ItemNewBinding

class NewsRecyclerAdapter(
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener<ArticleResponse?>
) : RecyclerView.Adapter<NewsViewHolder>()
{
    private var news : List<ArticleResponse>? = null

    fun setNews(news : List<ArticleResponse>?){
        this.news = news
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding,recyclerViewItemClickListener)
    }

    override fun getItemCount(): Int {
        return news?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.bind(news?.get(position))
    }
}