package com.kerencev.videoapp.ui.video

import android.content.res.AssetFileDescriptor
import android.content.res.Resources
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.kerencev.videoapp.databinding.FragmentVideoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoFragment : Fragment(), SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VideoViewModel by viewModel()
    private var surfaceHolder: SurfaceHolder? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        surfaceHolder = binding.surfaceView.holder
        surfaceHolder?.addCallback(this)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDisplay(surfaceHolder)
        val assetFileDescriptor: AssetFileDescriptor =
            requireContext().assets.openFd("videos/video5.mp4")
        try {
            mediaPlayer?.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )
            mediaPlayer?.prepare()
            mediaPlayer?.setOnPreparedListener(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    override fun onPrepared(mp: MediaPlayer) {
        val videoWidth = mediaPlayer?.videoWidth
        val videoHeight = mediaPlayer?.videoHeight
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val lp: ViewGroup.LayoutParams = binding.surfaceView.layoutParams
        lp.width = screenWidth
        lp.height =
            (videoHeight!!.toFloat() / videoWidth!!.toFloat() * screenWidth.toFloat()).toInt()
        binding.surfaceView.layoutParams = lp
        mediaPlayer?.start()
        Handler(Looper.getMainLooper()).postDelayed({
            val pos = mediaPlayer?.currentPosition
            mediaPlayer?.seekTo(1000)
        }, 5000)

    }

    override fun onPause() {
        super.onPause()
        releaseMediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }
}