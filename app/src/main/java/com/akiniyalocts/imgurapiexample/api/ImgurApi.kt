package com.akiniyalocts.imgurapiexample.api

import com.akiniyalocts.imgurapiexample.model.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ImgurApi {
    @Multipart
    @POST("/3/upload")
    @Headers("Authorization: Client-ID 0efc729575d3ba3")
    suspend fun uploadFile(
        @Part image: MultipartBody.Part?,
        @Part("name") name: RequestBody? = null
    ): Response<UploadResponse>
}