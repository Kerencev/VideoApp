package com.kerencev.videoapp.di

import android.content.res.AssetManager
import androidx.room.Room
import com.kerencev.videoapp.data.room.VideoHistoryDb
import com.kerencev.videoapp.model.repository.MediaRepository
import com.kerencev.videoapp.model.repository.MediaRepositoryImpl
import com.kerencev.videoapp.model.repository.ReportRepositoryImpl
import com.kerencev.videoapp.model.repository.ReportsRepository
import com.kerencev.videoapp.viewmodel.VideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AssetManager> { androidContext().assets }
    single<MediaRepository> { MediaRepositoryImpl(get()) }
    single<ReportsRepository> { ReportRepositoryImpl(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            VideoHistoryDb::class.java,
            "minitv.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    viewModel { VideoViewModel(get(), get()) }
}