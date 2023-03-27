package de.saringer.moviedemoapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.saringer.moviedemoapp.features.login.network.LoginApi
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import de.saringer.moviedemoapp.features.search.datasources.network.LandingPageApi
import de.saringer.moviedemoapp.features.search.datasources.network.LandingPageRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginApi: LoginApi, application: Application): LoginRepository = LoginRepository(loginApi, application)

    @Provides
    @Singleton
    fun provideLandingPageRepository(landingPageApi: LandingPageApi): LandingPageRepository = LandingPageRepository(landingPageApi)
}