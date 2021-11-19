package com.example.myspacex.data.datasource

import com.example.myspacex.data.cache.LaunchesCache

class LaunchesDataStoreFactoryImpl(
    private val launchesCache: LaunchesCache,
    private val diskLaunchesDataStore: DiskLaunchesDataStore,
    private val cloudLaunchesDataStore: CloudLaunchesDataStore,
) : LaunchesDataStoreFactory {

    override fun create(year: String, priority: LaunchesDataStoreFactory.Priority) =
        if (priority == LaunchesDataStoreFactory.Priority.CLOUD || !launchesCache.isCached(year))
            cloudLaunchesDataStore
        else
            diskLaunchesDataStore
}

interface LaunchesDataStoreFactory {
    enum class Priority {
        CLOUD,
        CACHE
    }

    fun create(year: String, priority: Priority): LaunchesDataStore
}