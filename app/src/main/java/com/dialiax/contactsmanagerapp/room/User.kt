package com.dialiax.contactsmanagerapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id : Int,

    @ColumnInfo(name = "user_name")
    var name : String,

    @ColumnInfo(name = "user_email")
    val email: String

)
