package com.example.myspacex.data.datasource

import com.example.myspacex.data.cache.LaunchesCache
import com.example.myspacex.data.cloud.LaunchesCloud

class DiskLaunchesDataStore(
    private val launchesCache: LaunchesCache
) : LaunchesDataStore {
    override suspend fun getLaunchCloudList(year: String) = launchesCache.get(year)

    override suspend fun getLaunchDetails(year: String, id: Int) = getLaunchCloudList(year)[id]
}