package com.kerencev.videoapp.model.repository

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import com.google.gson.Gson
import com.kerencev.videoapp.model.dto.VideoList
import com.kerencev.videoapp.utils.readAssetsFile

private const val MEDIA_LIST = "medialist.json"
private const val VIDEOS_FOLDER = "videos"

class MediaRepositoryImpl(private val assetManager: AssetManager) : MediaRepository {

    override suspend fun fetchMediaList(): VideoList {
        val data = assetManager.readAssetsFile(MEDIA_LIST)
        return Gson().fromJson(data, VideoList::class.java)
    }

    override suspend fun fetchVideoFromAssets(fileName: String): AssetFileDescriptor {
        return assetManager.openFd("$VIDEOS_FOLDER/$fileName")
    }
}