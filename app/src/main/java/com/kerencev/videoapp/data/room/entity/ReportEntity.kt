package com.kerencev.videoapp.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "reports"
)
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "id_video")
    val idVideo: Int,
    @ColumnInfo(name = "video_name")
    val videoName: String,
    @ColumnInfo(name = "start_time")
    val startTime: Long
)
