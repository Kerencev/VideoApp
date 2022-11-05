package com.kerencev.videoapp.model.repository

import android.content.res.AssetFileDescriptor
import com.kerencev.videoapp.model.dto.VideoList

interface MediaRepository {

    /**
     * Function to get medialist
     * @return Data class Videolist from Json
     */
    suspend fun fetchMediaList(): VideoList

    /**
     * Function to get video from assets in the type that the media player needs
     * @param fileName The name of the file in the video folder.
     * @return AssetFileDescriptor, the type required by the media player.
     */
    suspend fun fetchVideoFromAssets(fileName: String): AssetFileDescriptor
}