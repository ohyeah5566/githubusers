package com.example.githubusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.githubusers.data.BaseResult
import com.example.githubusers.data.GithubUser
import com.example.githubusers.data.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubUserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    val users = repository.getUsers(0).cachedIn(viewModelScope)

    private val _user = MutableLiveData<GithubUser>()
    val user: LiveData<GithubUser> = _user

    // single event
    private val _userFail = Channel<String>(Channel.BUFFERED)
    val userFail = _userFail.receiveAsFlow()

    fun loadSpecUser(name: String) {
        viewModelScope.launch(dispatcher) {
            repository.getSpecUser(name).collect { result ->
                when (result) {
                    is BaseResult.Success -> {
                        _user.value = result.data
                    }
                    is BaseResult.Error -> {
                        _userFail.send(result.ex.customMessage())
                    }
                }
            }
        }
    }

}