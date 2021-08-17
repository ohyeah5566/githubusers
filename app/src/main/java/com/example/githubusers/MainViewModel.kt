package com.example.githubusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.githubusers.data.GithubUser
import com.example.githubusers.data.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubUserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _users = MutableLiveData<PagingData<GithubUser>>()
    val users: LiveData<PagingData<GithubUser>> = _users

    private val _user = MutableLiveData<GithubUser>()
    val user: LiveData<GithubUser> = _user


    fun loadUsers() {
        viewModelScope.launch(dispatcher) {
            repository.getUsers(0).cachedIn(viewModelScope).collect { data ->
                _users.value = data
            }
        }
    }

    fun loadSpecUser(name: String) {
        viewModelScope.launch(dispatcher) {
            _user.value = repository.getSpecUser(name)
        }
    }

}