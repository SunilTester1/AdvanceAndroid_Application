package com.mishraji.advanceandroid_application.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "owner")
data class Owner(
    @PrimaryKey
    @ColumnInfo(name = "owner_id")
    @SerializedName("id") var id: Int? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null


)