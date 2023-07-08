package com.example.appcentnewsapp.data.model.response

data class ArticleResponse(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
) : java.io.Serializable

data class Source(
    val id: String?,
    val name: String?,
): java.io.Serializable
