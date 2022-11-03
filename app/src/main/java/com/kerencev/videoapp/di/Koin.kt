package com.kerencev.videoapp.di

import com.kerencev.videoapp.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { VideoViewModel() }
}