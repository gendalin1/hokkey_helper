package com.example.hokkey_helper.di.local

import com.example.hokkey_helper.repository.interfaces.MatchesRepository
import com.example.hokkey_helper.repository.interfaces.UserRepository
import com.example.hokkey_helper.useCases.AddPermissionToUserUseCase
import com.example.hokkey_helper.useCases.GetCommandUseCase
import com.example.hokkey_helper.useCases.GetCountryUseCase
import com.example.hokkey_helper.useCases.GetMatchesUseCase
import com.example.hokkey_helper.useCases.GetUserPermissionUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideCountryUseCase(
        repository: MatchesRepository,
    ): GetCountryUseCase =
        GetCountryUseCase(repository)

    @Provides
    @Singleton
    fun provideCommandUseCase(
        repository: MatchesRepository,
    ): GetCommandUseCase =
        GetCommandUseCase(repository)

    @Provides
    @Singleton
    fun provideMatchesUseCase(
        repository: MatchesRepository,
    ): GetMatchesUseCase =
        GetMatchesUseCase(repository)

    @Provides
    @Singleton
    fun provideUserPermissionUseCase(
        repository: UserRepository,
    ): GetUserPermissionUseCase =
        GetUserPermissionUseCase(repository)

    @Provides
    @Singleton
    fun provideAddUserPermissionUseCase(
        repository: UserRepository,
    ): AddPermissionToUserUseCase =
        AddPermissionToUserUseCase(repository)

}