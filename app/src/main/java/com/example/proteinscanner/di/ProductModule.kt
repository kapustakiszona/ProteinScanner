package com.example.proteinscanner.di

import com.example.proteinscanner.data.network.repository.NetworkRepositoryImpl
import com.example.proteinscanner.data.network.source.ProductRemoteDataSource
import com.example.proteinscanner.data.network.api.ProductService
import com.example.proteinscanner.domain.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ProductModule {

    @Provides
    fun providesProductService(retrofit: Retrofit): ProductService {
        val service = retrofit.create(ProductService::class.java)
        return service
    }

    @Provides
    fun providesProductRemoteDataSource(productService: ProductService): ProductRemoteDataSource =
        ProductRemoteDataSource(productService)

    @Provides
    fun providesNetworkRepository(productRemoteDataSource: ProductRemoteDataSource): NetworkRepository =
        NetworkRepositoryImpl(productRemoteDataSource)
}