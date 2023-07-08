package com.example.appcentnewsapp.network.service

import com.example.appcentnewsapp.base.model.BaseResponse
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.data.utils.session.Session
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?country=${Session.countryCode}&category=business&apiKey=${Session.apiKey}")
    fun getHomePageNews() : Call<BaseResponse<List<ArticleResponse>?>>

}