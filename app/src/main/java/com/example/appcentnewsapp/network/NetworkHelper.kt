package com.example.appcentnewsapp.network

import com.example.appcentnewsapp.BuildConfig
import com.example.appcentnewsapp.network.service.NewsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkHelper {
    private lateinit var retrofit: Retrofit

    lateinit var newsService: NewsService

    init{
        retrofitBuilder()
        bindServices()
    }
    private fun retrofitBuilder() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun bindServices() {
        newsService = retrofit.create(NewsService::class.java)
    }
    private fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        //httpClient.addInterceptor(createHttpLoggingInterceptor())

        return httpClient.build()
    }
/*    private fun createHttpLoggingInterceptor() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return httpLoggingInterceptor
    }*/

}