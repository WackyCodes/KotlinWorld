package com.wackycodes.test.kotlin.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.LocusId
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.wackycodes.test.kotlin.R
import com.wackycodes.test.kotlin.databinding.LayoutDialogProgressBinding

class ProgressView : Dialog {

    var progressBar: ProgressBar? = null

//    var layoutDialogProgressBinding:LayoutDialogProgressBinding?=null

    constructor(context: Context) : super( context ){
        requestWindowFeature( Window.FEATURE_NO_TITLE )
        val view: View = LayoutInflater.from( context ).inflate( R.layout.layout_dialog_progress, null )
        progressBar = view.findViewById(R.id.progressBar)
        setContentView( view )
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

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setDialogImage(id:Int? ){
        if (id!=null){
            progressBar?.progressDrawable = getContext().getDrawable( id )
        }
    }


}