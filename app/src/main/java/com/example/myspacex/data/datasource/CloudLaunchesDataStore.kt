package com.example.myspacex.data.datasource

import com.example.myspacex.data.cache.LaunchesCache
import com.example.myspacex.data.cloud.ConnectionManager
import com.example.myspacex.data.cloud.LaunchesCloud
import com.example.myspacex.data.cloud.LaunchesService

class CloudLaunchesDataStore(
    private val connectionManager: ConnectionManager,
    private val launchesService: LaunchesService,
    private val launchesCache: LaunchesCache
) : LaunchesDataStore {
    override suspend fun getLaunchCloudList(year: String): List<LaunchesCloud> =
        if (connectionManager.isNetworkAbsent()) {
            throw NetworkConnectionException()
        } else {
            val launches: List<LaunchesCloud>
            try {
                val launchesAsync = launchesService.getLaunches(year)
                val result = launchesAsync.await()
                launches = result.body()!!
                launchesCache.put(year, launches)
            } catch (exception: Exception) {
                throw ServerUnavailableException()
            }
            launches
        }

    override suspend fun getLaunchDetails(year: String, id: Int) =
        throw UnsupportedOperationException("Operation is not available")
}

class NetworkConnectionException(cause: Throwable? = null) : Exception(cause)
class ServerUnavailableException : java.lang.Exception()