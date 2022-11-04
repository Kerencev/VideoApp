package com.kerencev.videoapp.model.repository

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import com.google.gson.Gson
import com.kerencev.videoapp.model.VideoList
import com.kerencev.videoapp.utils.readAssetsFile

class MediaRepositoryImpl(private val assetManager: AssetManager) : MediaRepository {

    override suspend fun fetchMediaList(): VideoList {
        val data = assetManager.readAssetsFile("medialist.json")
        return Gson().fromJson(data, VideoList::class.java)
    }

    override suspend fun fetchVideoFromAssets(fileName: String): AssetFileDescriptor {
        return assetManager.openFd(fileName)
    }
}