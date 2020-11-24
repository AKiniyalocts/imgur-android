package com.akiniyalocts.imgurapiexample.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadResponse(
    @Json(name = "data")
    val upload: Upload,
    @Json(name = "status")
    val status: Int,
    @Json(name = "success")
    val success: Boolean
)

@JsonClass(generateAdapter = true)
data class Upload(
    @Json(name = "account_id")
    val accountId: Any,
    @Json(name = "account_url")
    val accountUrl: Any,
    @Json(name = "ad_type")
    val adType: Any,
    @Json(name = "ad_url")
    val adUrl: Any,
    @Json(name = "animated")
    val animated: Boolean,
    @Json(name = "bandwidth")
    val bandwidth: Int,
    @Json(name = "datetime")
    val datetime: Int,
    @Json(name = "deletehash")
    val deletehash: String,
    @Json(name = "description")
    val description: Any,
    @Json(name = "favorite")
    val favorite: Boolean,
    @Json(name = "has_sound")
    val hasSound: Boolean,
    @Json(name = "height")
    val height: Int,
    @Json(name = "hls")
    val hls: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "in_gallery")
    val inGallery: Boolean,
    @Json(name = "in_most_viral")
    val inMostViral: Boolean,
    @Json(name = "is_ad")
    val isAd: Boolean,
    @Json(name = "link")
    val link: String,
    @Json(name = "mp4")
    val mp4: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "nsfw")
    val nsfw: Any,
    @Json(name = "section")
    val section: Any,
    @Json(name = "size")
    val size: Int,
    @Json(name = "tags")
    val tags: List<Any>,
    @Json(name = "title")
    val title: Any,
    @Json(name = "type")
    val type: String,
    @Json(name = "views")
    val views: Int,
    @Json(name = "vote")
    val vote: Any,
    @Json(name = "width")
    val width: Int
)