package com.daerong.refresh_project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoutineDAO {
    @Query("SELECT * FROM routines")
    fun getAll():List<Routine>

    @Insert(onConflict = REPLACE)
    fun insert(routine: Routine)

    @Query("DELETE from routines")
    fun deleteAll()
}