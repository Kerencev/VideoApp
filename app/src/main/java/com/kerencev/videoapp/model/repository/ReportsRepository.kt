package com.kerencev.videoapp.model.repository

import com.kerencev.videoapp.data.room.entity.ReportEntity
import com.kerencev.videoapp.model.dto.VideoItem

interface ReportsRepository {

    suspend fun saveReportToDb(video: VideoItem)

    suspend fun getAllReportsFromDb(): List<ReportEntity>
}