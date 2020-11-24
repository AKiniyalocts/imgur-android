package com.akiniyalocts.imgurapiexample.repo

import android.content.ContentResolver
import android.net.Uri
import com.akiniyalocts.imgurapiexample.api.ImgurApi
import com.akiniyalocts.imgurapiexample.model.Upload
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

interface UploadRepository {
    suspend fun uploadFile(uri: Uri, title: String? = null): Result<Upload>
}
@Suppress("BlockingMethodInNonBlockingContext")
class UploadRepositoryImp(private val imgurApi: ImgurApi, private val contentResolver: ContentResolver): UploadRepository{
    override suspend fun uploadFile(uri: Uri, title: String?): Result<Upload> {
        return try{

            // copy inputstream from Uri to a temporary file for upload
            val file = copyStreamToFile(uri)

            val filePart = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())

            val response = imgurApi.uploadFile(
                filePart,
                name = file.name.toRequestBody()
            )

            if(response.isSuccessful){
                Result.success(response.body()!!.upload)
            }else{
                Result.failure(Exception("Unknown network Exception."))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    /**
     * We need to copy the Uri's input stream to the file system to temporarily
     * access it as a [File] to prepare it for upload.
     */
    private fun copyStreamToFile(uri: Uri): File {
        val outputFile = File.createTempFile("temp", null)

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
        return outputFile
    }
}