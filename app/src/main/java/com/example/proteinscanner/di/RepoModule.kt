package com.example.proteinscanner.di

import android.content.Context
import com.example.proteinscanner.data.scanner.ScannerRepositoryImpl
import com.example.proteinscanner.domain.repository.ScannerRepository
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

    @Provides
//    @Singleton
    fun provideGmsScanner(@ApplicationContext context: Context): GmsBarcodeScanner {
        return GmsBarcodeScanning.getClient(context)

    }

    @Provides
//    @Singleton
    fun provideScannerRepo(scanner: GmsBarcodeScanner): ScannerRepository {
        return ScannerRepositoryImpl(scanner)
    }

}