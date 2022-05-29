package com.mishraji.advanceandroid_application.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Owner::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}