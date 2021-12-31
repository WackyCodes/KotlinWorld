package com.wackycodes.upipayment

import com.wackycodes.upipayment.listener.PaymentStatusListener


internal object Singleton {
	@set:JvmSynthetic
	@get:JvmSynthetic
	internal var listener: PaymentStatusListener? = null
}