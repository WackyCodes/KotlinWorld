package com.wackycodes.upipayment.listener

import com.wackycodes.upipayment.model.TransactionDetails


interface PaymentStatusListener {
	fun onTransactionCompleted(transactionDetails: TransactionDetails)
	fun onTransactionCancelled()
}