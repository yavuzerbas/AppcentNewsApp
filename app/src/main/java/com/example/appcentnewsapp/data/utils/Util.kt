package com.example.appcentnewsapp.data.utils

import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun parseDate(dateString: String?): String {
        return if (dateString == null) {
            ""
        } else {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val date = format.parse(dateString)
            val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            outputFormat.format(date)
        }
    }

}