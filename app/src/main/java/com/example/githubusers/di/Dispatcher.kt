package com.example.githubusers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
class Dispatcher {

    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}