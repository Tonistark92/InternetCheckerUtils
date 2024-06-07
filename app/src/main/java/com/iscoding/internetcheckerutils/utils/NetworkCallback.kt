package com.aboeldahab.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.Toast

class ConnectivityCallback(private val context: Context) : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Toast.makeText(context, "Internet disconnected", Toast.LENGTH_SHORT).show()
    }
}