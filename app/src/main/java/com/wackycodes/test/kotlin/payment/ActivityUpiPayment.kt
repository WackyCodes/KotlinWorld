package com.wackycodes.test.kotlin.payment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.wackycodes.test.kotlin.ActivityBase
import com.wackycodes.test.kotlin.R
import com.wackycodes.upipayment.listener.PaymentStatusListener
import com.wackycodes.upipayment.model.PaymentApp
import com.wackycodes.upipayment.model.TransactionDetails
import com.wackycodes.upipayment.model.TransactionStatus
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import kotlinx.android.synthetic.main.activity_upi_payment.*

class ActivityUpiPayment : ActivityBase(), PaymentStatusListener {
    override fun onConnectionChange(isConnected: Boolean) {
//         TODO("Not yet implemented")
    }

    private lateinit var easyUpiPayment: EasyUpiPayment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upi_payment)

        initViews()
    }

    private fun initViews() {
        val transactionId = "TID" + System.currentTimeMillis()
        field_transaction_id.setText(transactionId)
        field_transaction_ref_id.setText(transactionId)

        // Setup click listener for Pay button
        button_pay.setOnClickListener { pay() }
    }

    private fun pay() {
        val payeeVpa = field_vpa.text.toString()
        val payeeName = field_name.text.toString()
        val transactionId = field_transaction_id.text.toString()
        val transactionRefId = field_transaction_ref_id.text.toString()
        val payeeMerchantCode = field_payee_merchant_code.text.toString()
        val description = field_description.text.toString()
        val amount = field_amount.text.toString()
        val paymentAppChoice = radioAppChoice

        val paymentApp = when (paymentAppChoice.checkedRadioButtonId) {
            R.id.app_default -> PaymentApp.ALL
            R.id.app_amazonpay -> PaymentApp.AMAZON_PAY
            R.id.app_bhim_upi -> PaymentApp.BHIM_UPI
            R.id.app_google_pay -> PaymentApp.GOOGLE_PAY
            R.id.app_phonepe -> PaymentApp.PHONE_PE
            R.id.app_paytm -> PaymentApp.PAYTM
            else -> throw IllegalStateException("Unexpected value: " + paymentAppChoice.id)
        }

        try {
            // START PAYMENT INITIALIZATION
            easyUpiPayment = EasyUpiPayment(this) {
                this.paymentApp = paymentApp
                this.payeeVpa = payeeVpa
                this.payeeName = payeeName
                this.transactionId = transactionId
                this.transactionRefId = transactionRefId
                this.payeeMerchantCode = payeeMerchantCode
                this.description = description
                this.amount = amount
            }
            // END INITIALIZATION

            // Register Listener for Events
            easyUpiPayment.setPaymentStatusListener(this)

            // Start payment / transaction
            easyUpiPayment.startPayment()
        } catch (e: Exception) {
            e.printStackTrace()
            toast("Error: ${e.message}")
        }
    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        // Transaction Completed
        Log.d("TransactionDetails", transactionDetails.toString())
        textView_status.text = transactionDetails.toString()

        when (transactionDetails.transactionStatus) {
            TransactionStatus.SUCCESS -> onTransactionSuccess()
            TransactionStatus.FAILURE -> onTransactionFailed()
            TransactionStatus.SUBMITTED -> onTransactionSubmitted()
        }
    }

    override fun onTransactionCancelled() {
        // Payment Cancelled by User
        toast("Cancelled by user")
        imageView.setImageResource(R.drawable.ic_failed)
    }

    private fun onTransactionSuccess() {
        // Payment Success
        toast("Success")
        imageView.setImageResource(R.drawable.ic_success)
    }

    private fun onTransactionSubmitted() {
        // Payment Pending
        toast("Pending | Submitted")
        imageView.setImageResource(R.drawable.ic_success)
    }

    private fun onTransactionFailed() {
        // Payment Failed
        toast("Failed")
        imageView.setImageResource(R.drawable.ic_failed)
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

//    Uncomment this if you have inherited [android.app.Activity] and not [androidx.appcompat.app.AppCompatActivity]
//
//	override fun onDestroy() {
//		super.onDestroy()
//		easyUpiPayment.removePaymentStatusListener()
//	}
}