package com.akiniyalocts.imgurapiexample.ui

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akiniyalocts.imgurapiexample.model.Upload
import com.akiniyalocts.imgurapiexample.repo.UploadRepository
import kotlinx.coroutines.launch

class MainViewModel(private val uploadRepository: UploadRepository): ViewModel() {
    val imageUri = MutableLiveData<Uri>()
    val uploadData = MutableLiveData<Result<Upload>>()

    fun selectedImageUri(uri: Uri) {
        imageUri.value = uri
    }

    fun uploadImage() = viewModelScope.launch{
        val uri = imageUri.value ?: return@launch
        uploadRepository.uploadFile(uri).fold(
            {
                uploadData.postValue(Result.success(it))
            },
            {
                uploadData.postValue(Result.failure(it))
            }
        )
    }

}