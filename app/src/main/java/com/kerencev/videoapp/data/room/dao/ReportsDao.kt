package com.kerencev.videoapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kerencev.videoapp.data.room.entity.ReportEntity

@Dao
interface ReportsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reportEntity: ReportEntity)

    @Query("SELECT * FROM reports")
    fun queryForAllReports(): List<ReportEntity>
}