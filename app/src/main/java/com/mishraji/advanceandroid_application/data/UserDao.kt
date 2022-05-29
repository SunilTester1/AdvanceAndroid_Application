package com.mishraji.advanceandroid_application.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(user: List<User>)

    @Query("DELETE FROM user")
    suspend fun deleteAllUser()
}