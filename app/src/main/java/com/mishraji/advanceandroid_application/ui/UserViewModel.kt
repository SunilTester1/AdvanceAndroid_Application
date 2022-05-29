package com.mishraji.advanceandroid_application.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mishraji.advanceandroid_application.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
    val user = repository.getUser().asLiveData()
}