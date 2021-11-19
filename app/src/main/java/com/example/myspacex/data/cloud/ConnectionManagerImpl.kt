package com.example.myspacex.data.cloud

import android.net.ConnectivityManager

class ConnectionManagerImpl(private val connectivityManager: ConnectivityManager?) :
    ConnectionManager {
    override fun isNetworkAbsent(): Boolean {
        val netInfo = connectivityManager?.activeNetworkInfo
        return netInfo == null || !netInfo.isConnected
    }
}