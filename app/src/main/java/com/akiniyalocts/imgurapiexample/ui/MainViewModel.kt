package com.akiniyalocts.imgurapiexample.ui

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akiniyalocts.imgurapiexample.api.ImgurApi

class MainViewModel(private val imgurApi: ImgurApi): ViewModel() {
    val imageUri = MutableLiveData<Uri>()

    fun selectedImageUri(uri: Uri) {
        imageUri.value = uri
    }

}