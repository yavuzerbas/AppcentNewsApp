package com.example.appcentnewsapp.ui.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcentnewsapp.R
import com.example.appcentnewsapp.base.model.BaseResponse
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.network.NetworkHelper
import com.example.appcentnewsapp.ui.activity.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var news = NetworkHelper.newsService.getHomePageNews()
            .enqueue(object : Callback<BaseResponse<List<ArticleResponse>?>>{
                override fun onResponse(
                    call: Call<BaseResponse<List<ArticleResponse>?>>,
                    response: Response<BaseResponse<List<ArticleResponse>?>>
                ) {
                    when{
                        response.isSuccessful -> {
                            val news = response.body()?.articles
                            navigateToMainActivity(news)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponse<List<ArticleResponse>?>>,
                    t: Throwable
                ) {
                    //TODO("Not yet implemented")
                }

            })
    }
    private fun navigateToMainActivity(news: List<ArticleResponse>?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putParcelableArrayListExtra("news", ArrayList(news))
        startActivity(intent)
        finish()
    }
}