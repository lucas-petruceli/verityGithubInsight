package com.example.veritygithubinsight


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//
//    @Provides
//    @Singleton
//    fun provideUserDataSource(): UserDataSource {
//        val networkComponent = DaggerNetworkComponent.create()
//        return networkComponent.getUserDataSource()
//    }
//}