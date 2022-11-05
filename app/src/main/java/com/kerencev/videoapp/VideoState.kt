package com.kerencev.videoapp

import android.content.res.AssetFileDescriptor

/**
 * Class for updating video state in the livedata
 */
data class VideoState(
    val currentVideo: AssetFileDescriptor,
    val currentTime: Int
)