package com.example.proteinscanner.di

import com.example.proteinscanner.data.network.api.ProductRemoteDataSource
import com.example.proteinscanner.data.network.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ProductModule {

    @Provides
    fun providesProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)


    @Provides
    fun providesProductRemoteDataSource(productService: ProductService): ProductRemoteDataSource =
        ProductRemoteDataSource(productService)
}