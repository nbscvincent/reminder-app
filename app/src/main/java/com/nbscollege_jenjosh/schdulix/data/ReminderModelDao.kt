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
    @Query("SELECT * FROM schedule ORDER BY title ASC")
    fun getAllSchedule(): Flow<List<ReminderModel>>

    @Query("SELECT * from schedule WHERE title = :title")
    fun getSchedule(title: String): Flow<ReminderModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: ReminderModel)

    @Update
    suspend fun update(schedule: ReminderModel)

    @Delete
    suspend fun delete(schedule: ReminderModel)

}