package com.example.githubusers.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val id: Int = 0,
    val login: String = "", //name
    val avatar_url: String = "", //image
    val type: String = "",
    val email: String? = "",
    val followers: Int = 0,
    val following: Int = 0,
    val location: String? = "",
    val html_url: String = "",
    val name: String = ""
) : Parcelable