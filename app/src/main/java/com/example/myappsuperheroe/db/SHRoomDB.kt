package com.example.myappsuperheroe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myappsuperheroe.pojo.SuperHero


@Database(entities = [SuperHero::class], version = 1,exportSchema = false)
abstract class SHRoomDB : RoomDatabase(){


    abstract fun shDao() : SHDao

    companion object {
        @Volatile
        private var INSTANCE: SHRoomDB? = null

        fun getDatabase(context: Context): SHRoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SHRoomDB::class.java,
                    "SH_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}