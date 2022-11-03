package com.kerencev.videoapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kerencev.videoapp.R
import com.kerencev.videoapp.ui.video.VideoFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, VideoFragment())
                .commitNowAllowingStateLoss()
        }
    }
}