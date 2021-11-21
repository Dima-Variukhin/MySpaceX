package com.example.myspacex.di

import android.app.Application
import com.example.myspacex.data.LaunchesRepository
import com.example.myspacex.data.cache.LaunchesCache
import com.example.myspacex.data.cloud.LaunchDataMapper
import com.example.myspacex.data.cloud.LaunchesService
import com.example.myspacex.data.datasource.*
import com.example.myspacex.domain.*
import com.example.myspacex.domain.validator.Validator

object MainScreen {
    private lateinit var config: DI.Config
    private lateinit var launchesCache: LaunchesCache
    private var repository: LaunchesRepository? = null
    private var launchDetailsInteractor: LaunchDetailsInteractor? = null
    private var launchesInteractor: LaunchesInteractor? = null
    private var searchResultsInteractor: SearchResultsInteractor? = null

    fun initialize(app: Application, configuration: DI.Config = DI.Config.RELEASE) {
        config = configuration
        launchesCache = LaunchesCache.Base(app)
    }
    
    fun getLaunchesInteractorImpl(): LaunchesInteractor {
        if (config == DI.Config.RELEASE && launchesInteractor == null)
            launchesInteractor = makeLaunchesInteractor(getLaunchesRepository())
        return launchesInteractor!!
    }

    fun getSearchResultsInteractor(): SearchResultsInteractor {
        if (searchResultsInteractor == null)
            searchResultsInteractor = SearchResultsInteractor.Base(getLaunchesRepository())
        return searchResultsInteractor!!
    }

    fun getLaunchDetailsInteractor(): LaunchDetailsInteractor {
        if (launchDetailsInteractor == null)
            launchDetailsInteractor = getLaunchDetailsInteractor(getLaunchesRepository())
        return launchDetailsInteractor!!
    }


    private fun getLaunchDetailsInteractor(repository: LaunchesRepository) =
        LaunchDetailsInteractor.Base(repository)

    private fun makeLaunchesInteractor(repository: LaunchesRepository) =
        LaunchesInteractor.Base(repository, Validator.Base())

    private fun getLaunchesRepository(): LaunchesRepository {
        if (repository == null)
            repository = LaunchesRepository.Base(
                getLaunchesDataStoreFactory(),
                LaunchDataMapper()
            )
        return repository!!
    }

    private fun getDiskLaunchesDataStore() = LaunchesDataStore.DiskLaunchesDataStore(launchesCache)
    private fun getCloudLaunchesDataStore() = LaunchesDataStore.CloudLaunchesDataStore(
        NetworkDI.getService(LaunchesService::class.java),
        launchesCache
    )

    private fun getLaunchesDataStoreFactory() =
        LaunchesDataStoreFactory.Base(
            launchesCache,
            getDiskLaunchesDataStore(),
            getCloudLaunchesDataStore()
        )
}