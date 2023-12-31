package com.example.appcentnewsapp.ui.fragment.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentnewsapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentnewsapp.base.model.BaseResponse
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.databinding.FragmentNewsBinding
import com.example.appcentnewsapp.network.NetworkHelper

import com.example.appcentnewsapp.ui.fragment.news.recycler.NewsRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_NEWS = "my_news"
class NewsFragment : Fragment() {

    private var binding : FragmentNewsBinding? = null
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter
    private var newsInFragment: ArrayList<ArticleResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    val homeIntent = Intent(Intent.ACTION_MAIN)
                    homeIntent.addCategory(Intent.CATEGORY_HOME)
                    homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(homeIntent)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            newsInFragment = it.getParcelableArrayList(ARG_NEWS)
            prepareNewsRecyclerAdapter(newsInFragment)
        }
        btnSearchOnClickListener()
        btnCancelOnClickListener()
    }
    private fun prepareNewsRecyclerAdapter(newsData: ArrayList<ArticleResponse>?) {
        newsRecyclerAdapter = NewsRecyclerAdapter(recyclerViewItemClickListener)
        binding?.recyclerNews?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.recyclerNews?.adapter = newsRecyclerAdapter
        handleNews(newsData)
        //newsRecyclerAdapter.setNews(newsData)
    }

    private val recyclerViewItemClickListener = object :
        RecyclerViewItemClickListener<ArticleResponse?> {
        override fun onClick(item: ArticleResponse?) {
            navigateToNewDetailFragment(item)
        }

    }

    private fun navigateToNewDetailFragment(articleResponse: ArticleResponse?){
        val isFavorite = articleResponse?.isFavorite ?: false
        findNavController().navigate(

        NewsFragmentDirections.actionNewsFragmentToNewDetailFragment(
            articleResponse?.title,
            articleResponse?.description,
            articleResponse?.urlToImage,
            articleResponse?.url,
            articleResponse?.author,
            articleResponse?.publishedAt,
            isFavorite
        )
        )
    }
    private fun getQueryNews(query: String) {
        NetworkHelper.newsService.getQueryNews(query)
            .enqueue(object : Callback<BaseResponse<List<ArticleResponse>?>>{
                override fun onResponse(
                    call: Call<BaseResponse<List<ArticleResponse>?>>,
                    response: Response<BaseResponse<List<ArticleResponse>?>>
                ) {
                    val newsResponse = response.body()?.articles as ArrayList<ArticleResponse>?
                    handleNews(newsResponse)
                }

                override fun onFailure(
                    call: Call<BaseResponse<List<ArticleResponse>?>>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }

            })
    }
    private fun handleNews(news: ArrayList<ArticleResponse>?){
        if(news == null || news.size == 0){
            binding?.tvNothingFound?.visibility = View.VISIBLE
            binding?.recyclerNews?.visibility = View.INVISIBLE
        }
        else{
            newsInFragment = news
            binding?.tvNothingFound?.visibility = View.INVISIBLE
            binding?.recyclerNews?.visibility = View.VISIBLE
            newsRecyclerAdapter.setNews(newsInFragment)
        }
    }
    private fun getQueryString(): String {
        return binding?.editSearch?.text.toString()
    }
    private fun btnSearchOnClickListener(){
        binding?.btnSearch?.setOnClickListener{
            val queryString = getQueryString()
            if(queryString != "")
                getQueryNews(queryString)
        }
    }
    private fun btnCancelOnClickListener(){
        binding?.btnCancel?.setOnClickListener {
            binding?.editSearch?.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}