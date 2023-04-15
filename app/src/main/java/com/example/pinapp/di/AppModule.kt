package com.example.pinapp.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.pinapp.MainActivityViewModel
import com.example.pinapp.PinGenerator
import com.example.pinapp.PinValidator
import com.example.pinapp.db.AppDao
import com.example.pinapp.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getAppDataBase(context: Application): AppDataBase {
        return AppDataBase.getAppDatabase(context)
    }

    @Singleton
    @Provides
    fun getDao(appDataBase: AppDataBase): AppDao {
        return appDataBase.getDao()
    }

    @Singleton
    @Provides
    fun getPinValidator(): PinValidator {
        return PinValidator()
    }

    @Singleton
    @Provides
    fun getPinGenerator() = PinGenerator()
}