package com.example.myspacex.data

import com.example.myspacex.data.cloud.LaunchDataMapper
import com.example.myspacex.data.datasource.LaunchesDataStoreFactory

interface LaunchesRepository {
    suspend fun getLaunches(year: String, reload: Boolean = false): List<LaunchData>
    suspend fun getLaunchData(year: String, id: Int): LaunchData

    class Base(
        private val launchesDataStoreFactory: LaunchesDataStoreFactory,
        private val launchDataMapper: LaunchDataMapper
    ) : LaunchesRepository {
        override suspend fun getLaunches(year: String, reload: Boolean): List<LaunchData> {
            val priority =
                if (reload) LaunchesDataStoreFactory.Priority.CLOUD else LaunchesDataStoreFactory.Priority.CACHE
            val dataStore = launchesDataStoreFactory.create(year, priority)
            return launchDataMapper.map(dataStore.getLaunchCloudList(year))
        }

        override suspend fun getLaunchData(year: String, id: Int) = getLaunches(year)[id]
    }
}