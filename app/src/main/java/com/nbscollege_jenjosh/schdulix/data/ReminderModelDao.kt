package com.nbscollege_jenjosh.schdulix.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderModelDao {
    @Query("SELECT * FROM schedule WHERE createdBy = :username ORDER BY title ASC")
    fun getAllSchedule(username: String): Flow<List<ReminderModel>>

    @Query("SELECT * FROM schedule WHERE title = :title")
    fun getSchedule(title: String): Flow<ReminderModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: ReminderModel)

    @Update
    suspend fun update(schedule: ReminderModel)

    @Delete
    suspend fun delete(schedule: ReminderModel)

    // detail time insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertdtl(scheduledtl: List<AddTimeModel>)

    @Query("DELETE FROM time WHERE title = :title ")
    suspend fun deletedtl(title: String)

    @Query("SELECT * FROM time WHERE title = :title ORDER BY time ASC")
    fun getAllScheduleDetail(title: String): Flow<List<AddTimeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertdtlLine(scheduledtl: AddTimeModel)

    @Query("DELETE FROM time WHERE id = :id ")
    suspend fun deleteScheduleDtl(id: Int?)

}