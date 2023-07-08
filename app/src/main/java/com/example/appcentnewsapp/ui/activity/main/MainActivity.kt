package com.example.appcentnewsapp.ui.activity.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcentnewsapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("data", Array::class.java) // TODO check with TIRAMUSU version if works
        } else {
            intent.getSerializableExtra("data") as? Array<*>
        }
    }
}