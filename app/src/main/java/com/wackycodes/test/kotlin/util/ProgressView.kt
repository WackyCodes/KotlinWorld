package com.wackycodes.test.kotlin.util

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import com.wackycodes.test.kotlin.R

class ProgressView : Dialog {
    constructor(context: Context) : super( context ){
        requestWindowFeature( Window.FEATURE_NO_TITLE )
        setContentView( R.layout.layout_dialog_progress )
        window?.setLayout( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT )
        setCancelable(true)
    }

    // Optional
    override fun show( ){
        super.show()
    }

    // Optional
    override fun dismiss( ){
        super.dismiss()
    }

}