package com.example.myspacex.data.datasource

import com.example.myspacex.data.cache.LaunchesCache
import com.example.myspacex.data.cloud.LaunchesCloud
import com.example.myspacex.data.cloud.LaunchesService

interface LaunchesDataStore {
    suspend fun getLaunchCloudList(year: String): List<LaunchesCloud>
    suspend fun getLaunchDetails(year: String, id: Int): LaunchesCloud

    class DiskLaunchesDataStore(
        private val launchesCache: LaunchesCache
    ) : LaunchesDataStore {
        override suspend fun getLaunchCloudList(year: String) = launchesCache.get(year)

        override suspend fun getLaunchDetails(year: String, id: Int) = getLaunchCloudList(year)[id]
    }

    class CloudLaunchesDataStore(
        private val launchesService: LaunchesService,
        private val launchesCache: LaunchesCache
    ) : LaunchesDataStore {
        override suspend fun getLaunchCloudList(year: String): List<LaunchesCloud> {
            val launches: List<LaunchesCloud>
            try {
                val launchesAsync = launchesService.getLaunchesAsync(year)
                val result = launchesAsync.await()
                launches = result.body()!!
                launchesCache.put(year, launches)
            } catch (exception: Exception) {
                throw ServerUnavailableException()
            }
            return launches
        }

        override suspend fun getLaunchDetails(year: String, id: Int) =
            throw UnsupportedOperationException("Operation is not available")
    }



}