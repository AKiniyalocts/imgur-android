package com.akiniyalocts.imgurapiexample.ui

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akiniyalocts.imgurapiexample.model.Upload
import com.akiniyalocts.imgurapiexample.repo.UploadRepository
import com.akiniyalocts.imgurapiexample.utils.AsyncState
import com.akiniyalocts.imgurapiexample.utils.Fail
import com.akiniyalocts.imgurapiexample.utils.Loading
import com.akiniyalocts.imgurapiexample.utils.Success
import kotlinx.coroutines.launch

class MainViewModel(private val uploadRepository: UploadRepository): ViewModel() {
    val imageUri = MutableLiveData<Uri>()
    val uploadData = MutableLiveData<AsyncState<Upload>>()

    fun selectedImageUri(uri: Uri) {
        imageUri.value = uri
    }

    fun uploadImage() = viewModelScope.launch{
        val uri = imageUri.value ?: return@launch

        uploadData.postValue(Loading)
        uploadRepository.uploadFile(uri).fold(
            {
                uploadData.postValue(Success(it))
            },
            {
                uploadData.postValue(Fail(it))
            }
        )
    }

}