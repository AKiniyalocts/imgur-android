package com.akiniyalocts.imgurapiexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akiniyalocts.imgurapiexample.api.ImgurApi
import com.akiniyalocts.imgurapiexample.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val imgurApi: ImgurApi by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 
    }
}