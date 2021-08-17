package com.example.githubusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.data.GithubUser
import com.example.githubusers.data.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubUserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _users = MutableLiveData<List<GithubUser>>()
    val users: LiveData<List<GithubUser>> = _users

    fun loadUsers() {
        viewModelScope.launch(dispatcher) {
            _users.value = repository.getUsers()
        }
    }

}