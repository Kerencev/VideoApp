package com.kerencev.videoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerencev.videoapp.VideoState
import com.kerencev.videoapp.model.dto.VideoList
import com.kerencev.videoapp.model.repository.MediaRepository
import com.kerencev.videoapp.model.repository.ReportsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoViewModel(
    private val mediaRepository: MediaRepository,
    private val reportsRepository: ReportsRepository
) : ViewModel() {

    private val _videoData = MutableLiveData<VideoState>()
    val videoData: LiveData<VideoState> get() = _videoData
    private var videoList: VideoList? = null
    private var nextOrder: Int = 0
    private var currentTime: Int = 0

    fun getVideo() {
        viewModelScope.launch(Dispatchers.IO) {
            if (videoList == null) {
                videoList = mediaRepository.fetchMediaList()
            }
            videoList?.let { listOfVideos ->
                val video = mediaRepository.fetchVideoFromAssets(
                    fileName = listOfVideos[nextOrder].videoIdentifier
                )
                _videoData.postValue(
                    VideoState(
                        currentVideo = video,
                        currentTime = currentTime
                    )
                )
                currentTime = 0
                nextOrder = if (nextOrder == listOfVideos.lastIndex) 0 else nextOrder + 1
            }
        }
    }

    /**
     * Function to save current video to data base
     */
    fun saveVideoToDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            videoList?.let { listOfVideos ->
                val currentOrder = if (nextOrder == 0) listOfVideos.lastIndex else nextOrder - 1
                reportsRepository.saveReportToDb(
                    video = listOfVideos[currentOrder]
                )
            }
        }
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
        nextOrder -= 1
    }

    override fun onCleared() {
        videoList = null
        super.onCleared()
    }
}