package com.example.githubusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.githubusers.api.GithubUserService
import com.example.githubusers.data.GithubUser
import com.example.githubusers.data.GithubUserPagingResource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class PagingResourceTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val fakeList = listOf(
        GithubUser(id = 1),
        GithubUser(id = 3),
        GithubUser(id = 5),
        GithubUser(id = 100)
    )

    @Test
    fun testPagingResource() = runBlockingTest {
        val service = mockk<GithubUserService>()
        val pagingSource = GithubUserPagingResource(service, 0)
        var since = 0
        coEvery { service.getUsers(since) } returns fakeList
        assertEquals(
            PagingSource.LoadResult.Page(
                data = fakeList,
                prevKey = null,
                nextKey = 100
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = since,
                    loadSize = 100,
                    placeholdersEnabled = false
                )
            )
        )

        //test prevKey
        since = 31
        coEvery { service.getUsers(since) } returns fakeList
        assertEquals(
            PagingSource.LoadResult.Page(
                data = fakeList,
                prevKey = 1,
                nextKey = 100
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = since,
                    loadSize = 100,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun testPagingResourceThrowException() = runBlockingTest {
        val service = mockk<GithubUserService>()
        val pagingSource = GithubUserPagingResource(service, 0)
        val ex = UnknownHostException("a")
        coEvery { service.getUsers(0) } throws ex
        assertEquals(
            PagingSource.LoadResult.Error<Int, GithubUser>(ex),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 100,
                    placeholdersEnabled = false
                )
            )
        )
    }
}