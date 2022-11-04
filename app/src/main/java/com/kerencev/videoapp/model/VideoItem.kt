package com.kerencev.videoapp.model

import com.google.gson.annotations.SerializedName

data class VideoItem(
    @SerializedName("VideoId")
    val videoId: Int,
    @SerializedName("VideoIdentifier")
    val videoIdentifier: String,
    @SerializedName("OrderNumber")
    val orderNumber: Int
)
