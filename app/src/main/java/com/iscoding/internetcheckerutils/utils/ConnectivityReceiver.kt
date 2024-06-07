package com.aboeldahab.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.widget.Toast


class ConnectivityReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (isInternetAvailable(context)) {
            Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Internet disconnected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            networkInfo.isConnected
        }
    }
}
/////////////////
//class ConnectionStateMonitor : NetworkCallback() {
//    val networkRequest: NetworkRequest = NetworkRequest.Builder()
//        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//        .build()
//
//    fun enable(context: Context) {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        connectivityManager.registerNetworkCallback(networkRequest, this)
//    }
//
//    // Likewise, you can have a disable method that simply calls ConnectivityManager.unregisterNetworkCallback(NetworkCallback) too.
//    override fun onAvailable(network: Network) {
//        // Do what you need to do here,
//        // or instead, override `onUnavailable()` or `onLost()`,
//        // (to detect absense of network).
//    }
//}


////////////////////////




//class ConnectionLiveData(val context: Context) : LiveData<Boolean>() {
//
//    private var connectivityManager: ConnectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//
//    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback
//
//    private val networkRequestBuilder: NetworkRequest.Builder = NetworkRequest.Builder()
//        .addTransportType(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)
//        .addTransportType(android.net.NetworkCapabilities.TRANSPORT_WIFI)
//
//    override fun onActive() {
//        super.onActive()
//        updateConnection()
//        when {
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(getConnectivityMarshmallowManagerCallback())
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> marshmallowNetworkAvailableRequest()
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> lollipopNetworkAvailableRequest()
//            else -> {
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                    context.registerReceiver(networkReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")) // android.net.ConnectivityManager.CONNECTIVITY_ACTION
//                }
//            }
//        }
//    }
//
//    override fun onInactive() {
//        super.onInactive()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
//        } else {
//            context.unregisterReceiver(networkReceiver)
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private fun lollipopNetworkAvailableRequest() {
//        connectivityManager.registerNetworkCallback(networkRequestBuilder.build(), getConnectivityLollipopManagerCallback())
//    }
//
//    @TargetApi(Build.VERSION_CODES.M)
//    private fun marshmallowNetworkAvailableRequest() {
//        connectivityManager.registerNetworkCallback(networkRequestBuilder.build(), getConnectivityMarshmallowManagerCallback())
//    }
//
//    private fun getConnectivityLollipopManagerCallback(): ConnectivityManager.NetworkCallback {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
//                override fun onAvailable(network: Network?) {
//                    postValue(true)
//                }
//
//                override fun onLost(network: Network?) {
//                    postValue(false)
//                }
//            }
//            return connectivityManagerCallback
//        } else {
//            throw IllegalAccessError("Accessing wrong API version")
//        }
//    }
//
//    private fun getConnectivityMarshmallowManagerCallback(): ConnectivityManager.NetworkCallback {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
//                override fun onCapabilitiesChanged(network: Network?, networkCapabilities: NetworkCapabilities?) {
//                    networkCapabilities?.let { capabilities ->
//                        if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
//                            postValue(true)
//                        }
//                    }
//                }
//                override fun onLost(network: Network?) {
//                    postValue(false)
//                }
//            }
//            return connectivityManagerCallback
//        } else {
//            throw IllegalAccessError("Accessing wrong API version")
//        }
//    }
//
//    private val networkReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context, intent: Intent) {
//            updateConnection()
//        }
//    }
//
//    private fun updateConnection() {
//        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//        postValue(activeNetwork?.isConnected == true)
//    }
//}