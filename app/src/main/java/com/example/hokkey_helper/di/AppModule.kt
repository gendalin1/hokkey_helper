package com.example.hokkey_helper.di

import com.example.hokkey_helper.di.local.RepositoryModule
import com.example.hokkey_helper.di.local.UseCaseModule
import dagger.Module

@Module(
    includes = [
        RepositoryModule::class,
        UseCaseModule::class
    ]
)

class AppModule