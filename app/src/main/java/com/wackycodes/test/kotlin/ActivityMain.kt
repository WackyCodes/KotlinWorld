package com.wackycodes.test.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.wackycodes.test.kotlin.databinding.ActivityMainBinding

class ActivityMain : ActivityBase(){

    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        mainBinding = DataBindingUtil.setContentView( this, R.layout.activity_main )

        onButtonAction()
    }


    private fun onButtonAction( ){
        mainBinding.buttonShowDialog.setOnClickListener { showDialog() }

    }

    override fun onConnectionChange(isConnected: Boolean) {
        if (isConnected){ // Connected!
            Log.e("INTERNET", "Connected!")
            mainBinding.textViewAlert.text = "Connected!"
            mainBinding.textViewAlert.visibility = View.GONE
        }else{ // Not Connected! or Disconnected!
            Log.e("INTERNET", "No Internet Connection!")
            mainBinding.textViewAlert.text = "No Internet Connection!"
            mainBinding.textViewAlert.visibility = View.VISIBLE
        }
    }


}