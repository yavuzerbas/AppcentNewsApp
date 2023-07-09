package com.example.appcentnewsapp.ui.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentnewsapp.base.listener.RecyclerViewItemClickListener
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.databinding.FragmentNewsBinding
import com.example.appcentnewsapp.ui.fragment.new_detail.NewDetailFragment

import com.example.appcentnewsapp.ui.fragment.news.recycler.NewsRecyclerAdapter
private const val ARG_NEWS = "my_news"
class NewsFragment : Fragment() {

    private var binding : FragmentNewsBinding? = null
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter
    private var newsInFragment: ArrayList<ArticleResponse>? = null

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
    }
    private fun prepareNewsRecyclerAdapter(newsData: ArrayList<ArticleResponse>?) {
        newsRecyclerAdapter = NewsRecyclerAdapter(recyclerViewItemClickListener)
        binding?.recyclerNews?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.recyclerNews?.adapter = newsRecyclerAdapter

        newsRecyclerAdapter.setNews(newsData)
    }

    private val recyclerViewItemClickListener = object :
        RecyclerViewItemClickListener<ArticleResponse?> {
        override fun onClick(item: ArticleResponse?) {
            navigateToNewDetailFragment(item)
        }

    }

    private fun navigateToNewDetailFragment(articleResponse: ArticleResponse?){
        /*val newDetailFragment = NewDetailFragment().apply {
            arguments = Bundle().apply {
                putString("imageUrl", articleResponse?.urlToImage)
                putString("authorName", articleResponse?.author)
                putString("date", articleResponse?.publishedAt.toString())
                putString("title", articleResponse?.title)
                putString("description", articleResponse?.description)
            }
        }*/
        //findNavController().navigate()
        //findNavController().navigate(NewsFragmentDirections)
        findNavController().navigate(
            NewsFragmentDirections.
                actionNewsFragmentToNewDetailFragment(
                    articleResponse?.title,
                    articleResponse?.description,
                    articleResponse?.urlToImage,
                    articleResponse?.url,
                    articleResponse?.author,
                    articleResponse?.publishedAt
                )
        )
    }

    private fun getNews() {
        //NetworkHelper.newsService.getHomePageNews()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        /** TODO DELETE
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param_news Parameter news.
         * @return A new instance of fragment NewsFragment.
         */
        @JvmStatic
        fun newInstance(param_news: ArrayList<ArticleResponse>) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_NEWS, param_news)
                }
            }
    }
}