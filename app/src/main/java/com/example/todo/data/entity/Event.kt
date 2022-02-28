package com.example.todo.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "events_table")
data class Event(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
     var title: String = "",
     var body: String = "",
     var time: String = ""
)