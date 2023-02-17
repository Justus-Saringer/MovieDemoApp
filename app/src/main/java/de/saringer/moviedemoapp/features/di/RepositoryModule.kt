package de.saringer.moviedemoapp.features.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.saringer.moviedemoapp.features.login.network.LoginApi
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginApi: LoginApi, application: Application): LoginRepository = LoginRepository(loginApi, application)
}