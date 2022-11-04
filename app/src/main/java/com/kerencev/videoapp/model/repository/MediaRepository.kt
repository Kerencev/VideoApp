package com.kerencev.videoapp.model.repository

import android.content.res.AssetFileDescriptor
import com.kerencev.videoapp.model.VideoList

interface MediaRepository {

    suspend fun fetchMediaList(): VideoList
    suspend fun fetchVideoFromAssets(fileName: String): AssetFileDescriptor
}