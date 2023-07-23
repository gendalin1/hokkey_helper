package com.example.hokkey_helper.di.local

import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import com.example.hokkey_helper.repository.MatchesRepositoryImpl
import com.example.hokkey_helper.repository.UserRepositoryImpl
import com.example.hokkey_helper.repository.interfaces.UserReporitory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMatchesRepositoryImpl(): MatchesRepository =
        MatchesRepositoryImpl()

    @Provides
    @Singleton
    fun provideUserRepositoryImpl(): UserReporitory =
        UserRepositoryImpl()

}