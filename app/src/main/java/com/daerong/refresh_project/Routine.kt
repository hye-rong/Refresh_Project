package com.daerong.refresh_project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
class Routine(@PrimaryKey var id: Long?,
              @ColumnInfo(name = "rname") var rname:String,
              @ColumnInfo(name = "isroutine") var isRoutine:Int,
              @ColumnInfo(name = "ischecked") var isChecked:Int) {
    constructor(): this(null, "", 0, 0)
}