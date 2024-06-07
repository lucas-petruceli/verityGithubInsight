package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name ") val name: String,
    @ColumnInfo(name = "avatar_url")val avatarUrl: String
)
