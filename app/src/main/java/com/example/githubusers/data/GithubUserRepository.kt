package com.example.githubusers.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubusers.api.GithubUserService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GithubUserRepository @Inject constructor(
    private val service: GithubUserService
) {

    fun getUsers(since:Int): Flow<PagingData<GithubUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubUserPagingResource(service,since) }
        ).flow
    }

    suspend fun getSpecUser(name: String): GithubUser {
        return service.getSpecUser(name)
    }
}