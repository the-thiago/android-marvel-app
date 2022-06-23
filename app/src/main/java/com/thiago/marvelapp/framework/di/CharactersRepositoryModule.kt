package com.thiago.marvelapp.framework.di

import com.thiago.core.data.repository.CharactersRemoteDataSource
import com.thiago.core.data.repository.CharactersRepository
import com.thiago.marvelapp.framework.CharactersRepositoryImpl
import com.thiago.marvelapp.framework.remote.RetrofitCharactersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharactersRepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitCharactersDataSource
    ): CharactersRemoteDataSource
}