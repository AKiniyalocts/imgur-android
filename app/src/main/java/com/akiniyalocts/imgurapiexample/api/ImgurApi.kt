package com.akiniyalocts.imgurapiexample.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImgurApi {
    @Multipart
    @POST("/3/upload")
    suspend fun uploadFile(
        @Part("image") image: MultipartBody.Part?,
        @Part("video") video: MultipartBody.Part?,
        @Part("album") album: MultipartBody.Part? = null,
        @Part("type") type: MultipartBody.Part,
        @Part("name") name: MultipartBody.Part?,
        @Part("title") title: MultipartBody.Part?,
        @Part("description") description: MultipartBody.Part?,
        @Part("disable_audio") disableAudio: Boolean? = null
    ): Result<ResponseBody>
}