package com.example.githubusers.di

import com.example.githubusers.api.GithubUserService
import com.example.githubusers.api.getGithubUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideGithubUserService(): GithubUserService {
        return getGithubUserService()
    }


}