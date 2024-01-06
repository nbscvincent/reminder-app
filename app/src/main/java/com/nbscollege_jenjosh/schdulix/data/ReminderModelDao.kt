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
    @Query("SELECT * from schedule ORDER BY id ASC")
    fun getAllSchedule(): Flow<List<ReminderModel>>

    @Query("SELECT * from schedule WHERE id = :id")
    fun getSchedule(id: Int): Flow<ReminderModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: ReminderModel)

    @Update
    suspend fun update(schedule: ReminderModel)

    @Delete
    suspend fun delete(schedule: ReminderModel)



    @Query("SELECT * from time ORDER BY id ASC")
    fun getAllScheduleDtl(): Flow<List<AddTimeModel>>

    @Query("SELECT * from time WHERE head_id = :id")
    fun getScheduleDtl(id: Int): Flow<AddTimeModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDtl(time: AddTimeModel)

    @Update
    suspend fun updateDtl(time: AddTimeModel)

    @Delete
    suspend fun deleteDtl(time: AddTimeModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDtlTmp(time: AddTimeTmpModel)
}