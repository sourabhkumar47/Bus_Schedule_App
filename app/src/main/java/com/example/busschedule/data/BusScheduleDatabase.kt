package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusScheduleDatabase : RoomDatabase() {

    abstract fun busScheduleDao(): BusScheduleDao

    //allows access to the methods to create or
    // get the database and uses the class name as the qualifier
    companion object {
        @Volatile
        private var Instance: BusScheduleDatabase? = null
    }

    @OptIn(InternalCoroutinesApi::class)
    fun getDatabase(context: Context): BusScheduleDatabase {
        return Instance ?: synchronized(this) {
            Room.databaseBuilder(context, BusScheduleDatabase::class.java, "bus_database")
                .build()
                .also { Instance = it }
        }
    }
}