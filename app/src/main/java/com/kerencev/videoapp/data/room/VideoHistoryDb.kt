package com.kerencev.videoapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kerencev.videoapp.data.room.dao.ReportsDao
import com.kerencev.videoapp.data.room.entity.ReportEntity

@Database(
    entities = [
        ReportEntity::class
    ],
    version = 1
)
abstract class VideoHistoryDb : RoomDatabase() {

    abstract fun getReportsDao(): ReportsDao
}