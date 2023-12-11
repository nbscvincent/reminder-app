package com.nbscollege_jenjosh.schdulix.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
        @Query("SELECT * from user ORDER BY username ASC")
        fun getAllUsers(): Flow<List<UserProfile>>

        @Query("SELECT * from user WHERE username = :id")
        fun getUsers(id: Int): Flow<UserProfile>

        @Query("SELECT * from user WHERE username = :username AND password = :password")
        fun getUsersPass(username: String, password: String): Flow<UserProfile>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(user: UserProfile)

        @Update
        suspend fun update(user: UserProfile)

        @Delete
        suspend fun delete(user: UserProfile)
    }