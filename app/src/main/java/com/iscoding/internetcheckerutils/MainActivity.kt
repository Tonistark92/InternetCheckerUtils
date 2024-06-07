package com.iscoding.internetcheckerutils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.aboeldahab.utils.ConnectivityCallback
import com.aboeldahab.utils.NetworkUtils
import com.iscoding.internetcheckerutils.ui.theme.InternetCheckerUtilsTheme

class MainActivity : ComponentActivity() {
    //    private lateinit var connectivityReceiver: ConnectivityReceiver
    private lateinit var connectivityCallback: ConnectivityCallback
    private lateinit var connectivityManager: ConnectivityManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        connectivityReceiver = ConnectivityReceiver()
//        //                                              CONNECTIVITY_CHANGE
//        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(connectivityReceiver, filter)

        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityCallback = ConnectivityCallback(this)

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, connectivityCallback)
        setContent {
            InternetCheckerUtilsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
        }
    }
}

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(connectivityReceiver)
        connectivityManager.unregisterNetworkCallback(connectivityCallback)
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if (NetworkUtils.isInternetAvailable(context)) {
                // Internet is available, perform your actions here
                Toast.makeText(context, "HAs internet connection", Toast.LENGTH_LONG).show()

            } else {
                // No internet, show a message to the user
                Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "Check for internet")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InternetCheckerUtilsTheme {
    }
}