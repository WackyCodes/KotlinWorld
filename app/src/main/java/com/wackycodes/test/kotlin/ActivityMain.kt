package com.wackycodes.test.kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.wackycodes.test.kotlin.payment.ActivityUpiPayment
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : ActivityBase(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onButtonAction()
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun onButtonAction( ){
        buttonShowDialog.setOnClickListener { showDialog() }

        buttonPayment.setOnClickListener{ startActivity( Intent(
            this,
            ActivityUpiPayment::class.java
        ) ) }

    }

    private fun showCustomDialog( ){

//        val dialog:ProgressBar = ProgressBar( this )
//        dialog.progressDrawable = getDrawable( R.drawable.custome_progress )

    }

    override fun onConnectionChange(isConnected: Boolean) {
        if (isConnected){ // Connected!
            Log.e("INTERNET", "Connected!")
            textViewAlert.text = "Connected!"
            textViewAlert.visibility = View.GONE
        }else{ // Not Connected! or Disconnected!
            Log.e("INTERNET", "No Internet Connection!")
            textViewAlert.text = "No Internet Connection!"
            textViewAlert.visibility = View.VISIBLE
        }
    }


}