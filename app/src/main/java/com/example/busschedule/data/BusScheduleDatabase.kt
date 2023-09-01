package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(BusSchedule::class), version = 1)
abstract class BusScheduleDatabase : RoomDatabase() {

    abstract fun busScheduleDao(): BusScheduleDao

    //allows access to the methods to create or
    // get the database and uses the class name as the qualifier
    companion object {
        @Volatile
        private var INSTANCE: BusScheduleDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): BusScheduleDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    BusScheduleDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}
