package com.wackycodes.test.kotlin

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.wackycodes.test.kotlin.util.ProgressView
import com.wackycodes.test.wackykt.InternetService

abstract class ActivityBase : AppCompatActivity(), InternetService.ConnectivityReceiverListener{

    private val connectionListener : InternetService = InternetService()

    private var progressView: ProgressView ? = null

    fun showToast( message : String? ){
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show()
    }

    fun showSnackMessage(view: View?, message: String?){
        if (view != null && message != null) {
            Snackbar.make( view, message, Snackbar.LENGTH_LONG ).show()
        }
    }

    fun showDialog( ){
        if (progressView == null){
            progressView = ProgressView( this )
        }
        progressView?.show()
    }

    fun dismissDialog( ){
        progressView?.dismiss()
    }

    fun logError( message: String?){
        Log.e( "LOG_E", ": $message")
    }

    fun logMessage( message: String?){
        Log.d( "LOG_D", ": $message")
    }

    override fun onResume() {
        super.onResume()
        registerReceiver( connectionListener, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        InternetService.connectivityReceiverListener = this
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver( connectionListener )
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        onConnectionChange( isConnected )
    }


    abstract fun onConnectionChange( isConnected: Boolean )


}
