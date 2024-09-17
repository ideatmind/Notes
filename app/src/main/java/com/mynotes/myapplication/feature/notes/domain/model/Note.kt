package com.mynotes.myapplication.feature.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description : String? = null,
    val isBookmarked : Boolean = false
)