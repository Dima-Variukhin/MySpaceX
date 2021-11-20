package com.example.myspacex.di

import android.app.Application
import android.app.PendingIntent.getService
import com.example.myspacex.data.LaunchesRepository
import com.example.myspacex.data.LaunchesRepositoryImpl
import com.example.myspacex.data.cache.LaunchesCache
import com.example.myspacex.data.cache.LaunchesCacheImpl
import com.example.myspacex.data.cloud.LaunchDataMapper
import com.example.myspacex.data.cloud.LaunchesService
import com.example.myspacex.data.datasource.CloudLaunchesDataStore
import com.example.myspacex.data.datasource.DiskLaunchesDataStore
import com.example.myspacex.data.datasource.LaunchesDataStoreFactoryImpl
import com.example.myspacex.domain.*
import com.example.myspacex.domain.validator.YearValidator
import java.lang.UnsupportedOperationException

object MainScreen {

    private lateinit var config: DI.Config
    private lateinit var launchesCache: LaunchesCache

    private var repository: LaunchesRepository? = null
    private var launchDetailsInteractor: LaunchDetailsInteractor? = null
    private var launchesInteractor: LaunchesInteractor? = null
    private var searchResultsInteractor: SearchResultsInteractor? = null

    fun initialize(app: Application, configuration: DI.Config = DI.Config.RELEASE) {
        config = configuration
        launchesCache = LaunchesCacheImpl(app)
    }

    fun getLaunchesInteractorImpl(): LaunchesInteractor {
        if (config == DI.Config.RELEASE && launchesInteractor == null)
            launchesInteractor = makeLaunchesInteractor(getLaunchesRepository())
        return launchesInteractor!!
    }

    fun setLaunchesInteractor(interactor: LaunchesInteractor) =
        if (config == DI.Config.TEST) {
            launchesInteractor = interactor
        } else {
            throw UnsupportedOperationException("one cannot simply set interactor if not a DI.Config.TEST")
        }

    fun getSearchResultsInteractor(): SearchResultsInteractor {
        if (searchResultsInteractor == null)
            searchResultsInteractor = SearchResultsInteractorImpl(getLaunchesRepository())
        return searchResultsInteractor!!
    }

    fun getLaunchDetailsInteractor(): LaunchDetailsInteractor {
        if (launchDetailsInteractor == null)
            launchDetailsInteractor = getLaunchDetailsInteractor(getLaunchesRepository())
        return launchDetailsInteractor!!
    }


    private fun getLaunchDetailsInteractor(repository: LaunchesRepository) =
        LaunchDetailsInteractorImpl(repository)

    private fun makeLaunchesInteractor(repository: LaunchesRepository) =
        LaunchesInteractorImpl(repository, YearValidator())

    private fun getLaunchesRepository(): LaunchesRepository {
        if (repository == null)
            repository = LaunchesRepositoryImpl(getLaunchesDataStoreFactory(), LaunchDataMapper())
        return repository!!
    }

    private fun getDiskLaunchesDataStore() = DiskLaunchesDataStore(launchesCache)
    private fun getCloudLaunchesDataStore() = CloudLaunchesDataStore(
        NetworkDI.connectionManager,
        NetworkDI.getService(LaunchesService::class.java),
        launchesCache
    )

    private fun getLaunchesDataStoreFactory() =
        LaunchesDataStoreFactoryImpl(
            launchesCache,
            getDiskLaunchesDataStore(),
            getCloudLaunchesDataStore()
        )
}