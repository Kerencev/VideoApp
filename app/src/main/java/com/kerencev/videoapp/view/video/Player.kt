package com.kerencev.videoapp.view.video

import android.content.res.AssetFileDescriptor
import android.content.res.Resources
import android.media.MediaPlayer
import android.view.SurfaceView
import android.view.ViewGroup

interface Player {
    fun play(video: AssetFileDescriptor, timeForStart: Int)
    fun release()
    fun getCurrentPosition(): Int
    fun setDisplay()
}

interface VideoStateListener {
    /**
     * Save video to database when new video is started
     */
    fun onStartNewVideo()
    fun onFinish()
}

class SurfacePlayer(
    private val view: SurfaceView,
    private val listener: VideoStateListener
) : Player {

    private val player by lazy { MediaPlayer() }

    override fun play(video: AssetFileDescriptor, timeForStart: Int) {
        try {
            player.setDataSource(
                video.fileDescriptor,
                video.startOffset,
                video.length
            )
            player.prepare()
            player.seekTo(timeForStart)
            setOnPreparedListener()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun release() {
        with(player) {
            stop()
            reset()
            release()
        }
    }

    override fun getCurrentPosition(): Int {
        return player.currentPosition
    }

    override fun setDisplay() {
        player.setDisplay(view.holder)
    }

    /**
     * Function to play video when the player is prepare
     * The function informs the outside when the video is over
     */
    private fun setOnPreparedListener() {
        with(player) {
            setOnPreparedListener {
                setDimensionsOfSurfaceView()
                start()
                if (player.currentPosition == 0) {
                    listener.onStartNewVideo()
                }
                setOnCompletionListener {
                    stop()
                    reset()
                    listener.onFinish()
                }
            }
        }
    }

    /**
     * Function for setting the correct video sizes depending on the screen
     */
    private fun setDimensionsOfSurfaceView() {
        val videoWidth = player.videoWidth
        val videoHeight = player.videoHeight
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val params: ViewGroup.LayoutParams = view.layoutParams
        params.width = screenWidth
        params.height =
            (videoHeight.toFloat() / videoWidth.toFloat() * screenWidth.toFloat()).toInt()
        view.layoutParams = params
    }
}