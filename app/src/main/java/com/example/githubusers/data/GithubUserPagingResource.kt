package com.example.githubusers.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubusers.api.GithubUserService

class GithubUserPagingResource(
    private val service: GithubUserService,
    private val since: Int //first start
) : PagingSource<Int, GithubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        val idSince = params.key ?: since
        return try {
            val result = service.getUsers(idSince)
            val nextKey = result.last().id
            val prevKey = if (idSince <= 30) null else idSince - 30
            LoadResult.Page(result, prevKey, nextKey)
        } catch (ex: Throwable) {
            LoadResult.Error(ex)
        }
    }

    //當 PagingSource invalidation時?,會從這裡拿新key
    override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? {
        return state.anchorPosition
    }

}