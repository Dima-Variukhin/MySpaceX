package com.example.myspacex.data.datasource

import com.example.myspacex.data.cache.LaunchesCache

interface LaunchesDataStoreFactory {
    enum class Priority {
        CLOUD,
        CACHE
    }

    fun create(year: String, priority: Priority): LaunchesDataStore


    class Base(
        private val launchesCache: LaunchesCache,
        private val diskLaunchesDataStore: LaunchesDataStore.DiskLaunchesDataStore,
        private val cloudLaunchesDataStore: LaunchesDataStore.CloudLaunchesDataStore,
    ) : LaunchesDataStoreFactory {

        override fun create(year: String, priority: LaunchesDataStoreFactory.Priority) =
            if (priority == LaunchesDataStoreFactory.Priority.CLOUD || !launchesCache.isCached(year))
                cloudLaunchesDataStore
            else
                diskLaunchesDataStore
    }
}