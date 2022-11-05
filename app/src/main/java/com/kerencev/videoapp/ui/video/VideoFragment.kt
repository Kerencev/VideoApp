package com.kerencev.videoapp.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kerencev.videoapp.databinding.FragmentVideoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoFragment : Fragment(), SurfaceHolder.Callback {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VideoViewModel by viewModel()
    private var player: Player? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.videoData.observe(viewLifecycleOwner) { videoData ->
            player?.play(
                video = videoData.currentVideo,
                timeForStart = videoData.currentTime
            )
        }
    }

    override fun onStart() {
        initFields()
        viewModel.getVideo()
        super.onStart()
    }

    override fun onStop() {
        player?.let { viewModel.saveCurrentTime(it.getCurrentPosition()) }
        player?.release()
        super.onStop()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        player?.setDisplay()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) = Unit

    override fun surfaceDestroyed(holder: SurfaceHolder) = Unit

    private fun initFields() {
        binding.surfaceView.holder.addCallback(this)
        player = SurfacePlayer(
            view = binding.surfaceView,
            listener = object : VideoStateListener {
                override fun onStartNewVideo() {
                    viewModel.saveVideoToDataBase()
                }

                override fun onFinish() {
                    viewModel.getVideo()
                }
            }
        )
    }
}