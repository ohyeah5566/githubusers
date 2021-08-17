package com.example.githubusers.data

data class GithubUser(
    val login: String = "", //name
    val avatar_url: String = "", //image
    val type: String = "",
    val email: String? = "",
    val followers: Int = 0,
    val following: Int = 0
)