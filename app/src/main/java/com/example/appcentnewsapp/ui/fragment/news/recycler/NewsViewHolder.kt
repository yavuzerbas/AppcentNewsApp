package com.example.appcentnewsapp.ui.fragment.news.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.appcentnewsapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.databinding.ItemNewBinding
import com.squareup.picasso.Picasso

class NewsViewHolder(
    private val binding: ItemNewBinding,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener<ArticleResponse?>
) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(item: ArticleResponse?){
        binding.tvItemNewTitle.text = "${item?.title}"
        binding.tvItemNewDescription.text = "${item?.description}"

        Picasso
            .get()
            .load(item?.urlToImage)
            .into(binding.imgItemNew)

        binding.root.setOnClickListener{
            recyclerViewItemClickListener.onClick(item)
        }
    }
}