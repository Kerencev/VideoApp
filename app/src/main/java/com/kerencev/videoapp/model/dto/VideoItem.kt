package com.kerencev.videoapp.model.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class for converting from a json file
 */
data class VideoItem(
    @SerializedName("VideoId")
    val videoId: Int,
    @SerializedName("VideoIdentifier")
    val videoIdentifier: String,
    @SerializedName("OrderNumber")
    val orderNumber: Int
)
