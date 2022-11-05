package com.kerencev.videoapp.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kerencev.videoapp.R
import com.kerencev.videoapp.view.video.VideoFragment

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