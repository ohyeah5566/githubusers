package com.example.githubusers.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubusers.api.GithubUserService

class GithubUserPagingResource (
    private val service: GithubUserService,
    private val since:Int //first start
) : PagingSource<Int, GithubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        val idSince = params.key ?: since
        return try {
            val result = service.getUsers(idSince)
            val nextKey = result.last().id
            LoadResult.Page(result, null, nextKey)
        } catch (ex: Throwable) {
            LoadResult.Error(ex)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}