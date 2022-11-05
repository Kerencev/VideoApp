package com.kerencev.videoapp.di

import android.content.res.AssetManager
import com.kerencev.videoapp.model.repository.MediaRepository
import com.kerencev.videoapp.model.repository.MediaRepositoryImpl
import com.kerencev.videoapp.viewmodel.VideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AssetManager> { androidContext().assets }
    single<MediaRepository> { MediaRepositoryImpl(get()) }

    viewModel { VideoViewModel(get()) }
}