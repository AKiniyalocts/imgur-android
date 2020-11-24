package com.akiniyalocts.imgurapiexample.ui

import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("uploadUri")
fun setUploadUri(button: Button, uri: Uri?){
    button.isEnabled = uri != null
}

@BindingAdapter("uriImage")
fun setUriImage(imageView: ImageView, uri: Uri?){
    imageView.setImageURI(uri)
}
