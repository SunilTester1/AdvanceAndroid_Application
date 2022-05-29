package com.mishraji.advanceandroid_application.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @Embedded
    @SerializedName("owner") var owner: Owner? = Owner()
)
