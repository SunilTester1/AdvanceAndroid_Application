package com.mishraji.advanceandroid_application.api

import com.mishraji.advanceandroid_application.data.User
import retrofit2.http.GET

interface UserApi {
    companion object {
        const val BASE_URL = "https://api.github.com/users/Octokit/"
    }

    @GET("repos")
    suspend fun getUser(): List<User>
}