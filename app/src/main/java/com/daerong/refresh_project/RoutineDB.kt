package com.daerong.refresh_project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Routine::class], version = 1)
abstract class RoutineDB: RoomDatabase() {
    abstract fun routineDao():RoutineDAO

    companion object {
        private var INSTANCE: RoutineDB? = null

        //RoutineDB 객체 반환
        fun getInstance(context: Context?): RoutineDB? {
            if(INSTANCE == null) {
                synchronized(RoutineDB::class) {
                    if (context != null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RoutineDB::class.java, "routine.db")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        //DB 삭제
        fun destroyInstance(){
            INSTANCE = null
        }
    }

}