package com.akiniyalocts.imgurapiexample.ui

import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.akiniyalocts.imgurapiexample.model.Upload
import com.akiniyalocts.imgurapiexample.utils.AsyncState
import com.akiniyalocts.imgurapiexample.utils.Loading
import com.akiniyalocts.imgurapiexample.utils.Success
import com.google.android.material.progressindicator.ProgressIndicator

/**
 * Custom bindings for the [MainActivity] view
 */

@BindingAdapter("uploadUri")
fun setUploadUri(button: Button, uri: Uri?){
    button.isEnabled = uri != null
}

@BindingAdapter("uriImage")
fun setUriImage(imageView: ImageView, uri: Uri?){
    imageView.setImageURI(uri)
}

@BindingAdapter("completedUploadLink")
fun setCompletedUploadLink(textView: TextView, upload: AsyncState<Upload>?){
    textView.text = (upload as? Success<Upload>)?.value?.link
}

/**
 * Shows a progressIndicator or hides it based on some data's [AsyncState]
 */
@BindingAdapter("progressVisibility")
fun setProgressVisibility(progressIndicator: ProgressIndicator, state: AsyncState<Any?>?){
    if(state == Loading){
        progressIndicator.visibility = View.VISIBLE
    }else{
        progressIndicator.visibility = View.INVISIBLE
    }
}

/**
 * Shows a button or hides it based on some data's [AsyncState]
 */
@BindingAdapter("buttonVisibility")
fun setButtonVisibility(button: Button, state: AsyncState<Any?>?){
    if(state == Loading){
        button.visibility = View.INVISIBLE
    }else{
        button.visibility = View.VISIBLE
    }
}
