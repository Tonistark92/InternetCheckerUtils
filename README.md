# Internet Connectivity Checker

This project demonstrates three different methods to check internet connectivity in an Android application.

## Methods Used

1. **ConnectivityManager on Button Click:**
   - This method involves checking for internet connectivity when a button is clicked by the user. It utilizes the `ConnectivityManager` class to get the current network state.

2. **Broadcast Receiver (Deprecated):**
   - Initially, we attempted to use a broadcast receiver to monitor changes in network connectivity. However, the traditional method using `ConnectivityManager.CONNECTIVITY_ACTION` is deprecated, hence this approach was abandoned.

3. **ConnectivityManager.NetworkCallback():**
   - To replace the deprecated broadcast receiver method, we adopted the `ConnectivityManager.NetworkCallback()` approach. This method provides a more modern and efficient way to monitor network connectivity changes. It allows us to receive network status updates even when the app is running in the background.

## How Each Method Works

### 1. ConnectivityManager on Button Click
   - When the user clicks a designated button in the app, the `onClick()` method associated with the button is triggered.
   - Within this method, an instance of `ConnectivityManager` is created.
   - The connectivity manager is then used to check the network state. If internet connectivity is available, a success message is displayed to the user.

### 2. Broadcast Receiver (Deprecated)
   - Initially, we tried to use a broadcast receiver to monitor changes in network connectivity.
   - However, the traditional method using `ConnectivityManager.CONNECTIVITY_ACTION` is deprecated and no longer recommended for use.

### 3. ConnectivityManager.NetworkCallback()
   - This method involves the use of `ConnectivityManager.NetworkCallback()` to monitor network connectivity changes.
   - When the app is running, it registers a network callback with the connectivity manager.
   - The callback is triggered whenever there is a change in the network state, even when the app is running in the background.
   - This approach ensures that the app can react promptly to changes in network connectivity, providing a seamless user experience.

## Additional Notes
   - Ensure appropriate permissions are declared in the AndroidManifest.xml file to access network state information.
   - The third method using `ConnectivityManager.NetworkCallback()` is recommended for modern Android applications as it provides more reliable and efficient network connectivity monitoring.

Feel free to explore the provided code to understand the implementation details of each method further.
