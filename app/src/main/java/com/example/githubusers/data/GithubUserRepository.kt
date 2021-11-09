package com.example.githubusers.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubusers.api.GithubUserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GithubUserRepository @Inject constructor(
    private val service: GithubUserService
) {

    fun getUsers(since: Int): Flow<PagingData<GithubUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubUserPagingResource(service, since) }
        ).flow
    }

    suspend fun getSpecUser(name: String): BaseResult<GithubUser> {
        return try {
            val result = service.getSpecUser(name)
            BaseResult.Success(result)
        } catch (ex: Throwable) {
            BaseResult.Error(ex)
        }
    }
}