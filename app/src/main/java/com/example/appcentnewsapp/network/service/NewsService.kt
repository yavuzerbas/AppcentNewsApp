package com.example.appcentnewsapp.network.service

import com.example.appcentnewsapp.base.model.BaseResponse
import com.example.appcentnewsapp.data.model.response.ArticleResponse
import com.example.appcentnewsapp.data.utils.session.Session
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    /*@GET("top-headlines?country=${Session.countryCode}&category=business&apiKey=${Session.apiKey}")
    fun getHomePageNews() : Call<BaseResponse<List<ArticleResponse>?>>

    @GET("everything?q={query}&page=1&apiKey=${Session.apiKey}")
    fun getQueryNews(
        @Query("query") query : String
    ) : Call<BaseResponse<List<ArticleResponse>?>>*/
    @GET("top-headlines")
    fun getHomePageNews(
        @Query("country") country: String = Session.countryCode,
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String = Session.apiKey
    ) : Call<BaseResponse<List<ArticleResponse>?>>

    @GET("everything")
    fun getQueryNews(
        @Query("q") query : String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = Session.apiKey
    ) : Call<BaseResponse<List<ArticleResponse>?>>
}