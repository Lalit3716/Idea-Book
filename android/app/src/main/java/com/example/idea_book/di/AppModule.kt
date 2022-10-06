package com.example.idea_book.di

import com.example.idea_book.core.validators.EmailValidator
import com.example.idea_book.core.validators.PasswordValidator
import com.example.idea_book.core.validators.UsernameValidator
import com.example.idea_book.data.repository.AuthRepositoryImpl
import com.example.idea_book.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }

    @Provides
    @Singleton
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidator()
    }

    @Provides
    @Singleton
    fun provideUsernameValidator(): UsernameValidator {
        return UsernameValidator()
    }
}
