package com.mishraji.advanceandroid_application.data

import androidx.room.withTransaction
import com.mishraji.advanceandroid_application.api.UserApi
import com.mishraji.advanceandroid_application.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,
    private val db: UserDatabase
) {
    private val userDao = db.userDao()

    fun getUser() = networkBoundResource(
        query = {
            userDao.getAllUser()!!
        },
        fetch = {
            delay(2000)
            api.getUser()
        },
        shouldFetch = {
            true
        },
        saveFetchResult = { user ->
            db.withTransaction {
                userDao.deleteAllUser()
                userDao.insertUser(user)
            }
        }
    )

}