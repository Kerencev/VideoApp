package com.kerencev.videoapp.ui.video

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerencev.videoapp.model.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoViewModel(private val mediaRepository: MediaRepository) : ViewModel() {

    fun getMediaList() {
        viewModelScope.launch(Dispatchers.IO) {
            val mediaList = mediaRepository.fetchMediaList()
            Log.d("TAG", mediaList.toString())
        }
    }

    fun getVideoFromAssets(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val video = mediaRepository.fetchVideoFromAssets(fileName)
            Log.d("TAG", video.toString())
        }
    }
}