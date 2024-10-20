package com.example.proteinscanner.di

import android.content.Context
import com.example.proteinscanner.data.ScannerRepositoryImpl
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideGmsScanner(@ApplicationContext context: Context): GmsBarcodeScanner {
        return GmsBarcodeScanning.getClient(context)

    }

    @Provides
    @Singleton
    fun provideScannerRepo(scanner: GmsBarcodeScanner): ScannerRepositoryImpl {
        return ScannerRepositoryImpl(scanner)
    }
}