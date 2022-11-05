package com.kerencev.videoapp.model.repository

import com.kerencev.videoapp.data.room.VideoHistoryDb
import com.kerencev.videoapp.data.room.entity.ReportEntity
import com.kerencev.videoapp.model.dto.VideoItem

class ReportRepositoryImpl(private val db: VideoHistoryDb) : ReportsRepository {

    override suspend fun saveReportToDb(video: VideoItem) {
        db.getReportsDao().insert(
            ReportEntity(
                id = 0,
                idVideo = video.videoId,
                videoName = video.videoIdentifier,
                startTime = System.currentTimeMillis()
            )
        )
    }

    override suspend fun getAllReportsFromDb(): List<ReportEntity> {
        return db.getReportsDao().queryForAllReports()
    }
}