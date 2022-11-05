package com.kerencev.videoapp.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerencev.videoapp.VideoState
import com.kerencev.videoapp.model.dto.VideoList
import com.kerencev.videoapp.model.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoViewModel(private val mediaRepository: MediaRepository) : ViewModel() {

    private val _videoData = MutableLiveData<VideoState>()
    val videoData: LiveData<VideoState> get() = _videoData
    private var videoList: VideoList? = null
    private var currentOrder: Int = 0
    private var currentTime: Int = 0

    fun getVideo() {
        viewModelScope.launch(Dispatchers.IO) {
            if (videoList == null) {
                videoList = mediaRepository.fetchMediaList()
            }
            videoList?.let { listOfVideos ->
                val video = mediaRepository.fetchVideoFromAssets(
                    fileName = listOfVideos[currentOrder].videoIdentifier
                )
                _videoData.postValue(
                    VideoState(
                        currentVideo = video,
                        currentTime = currentTime
                    )
                )
                currentTime = 0
                currentOrder = if (currentOrder == listOfVideos.lastIndex) 0 else currentOrder + 1
            }
        }
    }

    fun saveVideoToDataBase() {

    }

    /**
     * Function to save current time of the video, when 'onStop' is called
     */
    fun saveCurrentTime(currentTime: Int) {
        videoData.value?.let { oldData ->
            _videoData.value = VideoState(
                currentVideo = oldData.currentVideo,
                currentTime = currentTime
            )
        }
        currentOrder -= 1
    }

    override fun onCleared() {
        videoList = null
        super.onCleared()
    }
}