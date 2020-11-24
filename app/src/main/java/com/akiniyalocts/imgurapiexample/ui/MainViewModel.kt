package com.akiniyalocts.imgurapiexample.ui

import android.content.ContentResolver
import android.net.Uri
import androidx.core.net.toFile
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akiniyalocts.imgurapiexample.api.ImgurApi
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Multipart
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MainViewModel(private val imgurApi: ImgurApi, private val contentResolver: ContentResolver): ViewModel() {
    val imageUri = MutableLiveData<Uri>()

    fun selectedImageUri(uri: Uri) {
        imageUri.value = uri
    }

    fun uploadImage() = viewModelScope.launch{
        val uri = imageUri.value ?: return@launch

        val file = File.createTempFile("temp", null)
        copyStreamToFile(uri, file)

        val fileBody = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())
        val name = MultipartBody.Part.createFormData("name", file.name)

        val response = imgurApi.uploadFile(fileBody, name = file.name.toRequestBody())

        print(response)
    }

    fun copyStreamToFile(uri: Uri, outputFile: File) {
        contentResolver.openInputStream(uri)?.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }
}