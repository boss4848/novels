package com.passakorn.novels.di

import com.passakorn.novels.data.datasource.remote.NovelRemoteDataSource
import com.passakorn.novels.data.repository.NovelRepository
import com.passakorn.novels.data.repository.NovelRepositoryImpl
import com.passakorn.novels.domain.mapper.CampaignListResponseMapper
import com.passakorn.novels.domain.mapper.NovelListResponseMapper
import com.passakorn.novels.domain.usecase.GetCampaignListUseCase
import com.passakorn.novels.domain.usecase.GetNovelListUseCase
import com.passakorn.novels.presentation.NovelViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NovelModules {
    private val retrofitModule = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://www.dek-d.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    private val networkModule = module {
        single { get<Retrofit>().create(NovelRemoteDataSource::class.java) }
    }

    private val repository = module {
        factory<NovelRepository> { NovelRepositoryImpl(get()) }
    }

    private val useCase = module {
        factoryOf(::GetNovelListUseCase)
        factoryOf(::GetCampaignListUseCase)
    }

    private val mapper = module {
        factoryOf(::NovelListResponseMapper)
        factoryOf(::CampaignListResponseMapper)
    }

    private val viewModel = module {
        factoryOf(::NovelViewModel)
    }

    val allModules = listOf(
        retrofitModule,
        networkModule,
        repository,
        useCase,
        viewModel,
        mapper,
    )
}