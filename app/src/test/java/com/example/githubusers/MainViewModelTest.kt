package com.example.githubusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubusers.data.BaseResult
import com.example.githubusers.data.GithubUser
import com.example.githubusers.data.GithubUserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val user = GithubUser(
        login = "login",
        name = "name"
    )

    @Test
    fun testLoadSpecUser() = runBlockingTest {
        val repository = mockk<GithubUserRepository>(relaxed = true)
        val testDispatcher = TestCoroutineDispatcher()
        val viewModel = MainViewModel(repository, testDispatcher)
        coEvery { repository.getSpecUser("A") } returns flow {
            emit(
                BaseResult.Success(user)
            )
        }

        viewModel.loadSpecUser("A")
        assert(viewModel.user.value?.name == "name")
    }

    @Test
    fun testLoadSpecUserFail() = runBlockingTest {
        val repository = mockk<GithubUserRepository>(relaxed = true)
        val testDispatcher = TestCoroutineDispatcher()
        val viewModel = MainViewModel(repository, testDispatcher)

        coEvery { repository.getSpecUser("A") } returns flow {
            val ex = Exception("FailTest")
            emit(
                BaseResult.Error(ex)
            )
        }

        viewModel.loadSpecUser("A")
        assert(viewModel.userFail.first() == "FailTest")
    }

}