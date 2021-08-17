package com.example.githubusers.data

import com.example.githubusers.api.GithubUserService
import javax.inject.Inject


class GithubUserRepository @Inject constructor(
    private val service: GithubUserService
) {
    suspend fun getUsers(): List<GithubUser> {
        return service.getUsers()
    }

    suspend fun getSpecUser(name: String): GithubUser {
        return service.getSpecUser(name)
    }
}