package com.example.appcentnewsapp.base.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse<T>(
    val status: String?,
    val totalResults: Int?,
    val articles: T?,
) : Serializable