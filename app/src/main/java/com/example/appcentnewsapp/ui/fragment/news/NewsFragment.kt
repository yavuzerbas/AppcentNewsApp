package com.example.appcentnewsapp.ui.fragment.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcentnewsapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.databinding.FragmentNewsBinding
import com.example.appcentnewsapp.network.NetworkHelper
import com.example.appcentnewsapp.ui.fragment.news.recycler.NewsRecyclerAdapter


class NewsFragment : Fragment() {

    private var binding : FragmentNewsBinding? = null
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareNewsRecyclerAdapter()
        getNews()
    }

    private fun prepareNewsRecyclerAdapter() {
        newsRecyclerAdapter = NewsRecyclerAdapter(recyclerViewItemClickListener)
    }

    private val recyclerViewItemClickListener = object : RecyclerViewItemClickListener<ArticleResponse?>{
        override fun onClick(item: ArticleResponse?) {
            navigateToNewDetailFragment(item)
        }

    }

    private fun navigateToNewDetailFragment(articleResponse: ArticleResponse?){
        //TODO CategoryListFragment.kt, 74
    }

    private fun getNews() {
        //NetworkHelper.newsService.getHomePageNews()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}