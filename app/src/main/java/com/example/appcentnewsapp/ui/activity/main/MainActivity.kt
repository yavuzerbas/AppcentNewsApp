package com.example.appcentnewsapp.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.appcentnewsapp.R
import com.example.appcentnewsapp.data.model.response.ArticleResponse

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.fragmentContainerView)
        val newsData = intent.getParcelableArrayListExtra<ArticleResponse>("news")

        if (newsData != null) {
            val bundle = Bundle().apply {
                putParcelableArrayList(ARG_NEWS, newsData)
            }

            navController.navigate(R.id.newsFragment, bundle)
        }
    }

    companion object {
        const val ARG_NEWS = "my_news"
    }
}


