package com.nbscollege_jenjosh.schdulix.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nbscollege_jenjosh.schdulix.Schdulix
import com.nbscollege_jenjosh.schdulix.model.UserProfile


/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [UserProfile::class], version = 1, exportSchema = true)
                    abstract class SchdulixDatabase : RoomDatabase() {
                        abstract fun UserProfileDao(): UserProfileDao

                        companion object {
                            @Volatile
                            private var Instance: SchdulixDatabase? = null
                            fun getDatabase(context: Context): SchdulixDatabase {
                                // if the Instance is not null, return it, otherwise create a new database instance.
                                return Instance ?: synchronized(this) {
                                    Room.databaseBuilder(
                                        context,
                                        SchdulixDatabase::class.java,
                                        "schdulix_database"
                                    )
                                        /**
                                         * Setting this option in your app's database builder means that Room
                                         * permanently deletes all data from the tables in your database when it
                                         * attempts to perform a migration with no defined migration path.
                                         */
                                        .fallbackToDestructiveMigration()
                                        .build()
                                        .also { Instance = it }
                                }
                            }
                        }
                    }
