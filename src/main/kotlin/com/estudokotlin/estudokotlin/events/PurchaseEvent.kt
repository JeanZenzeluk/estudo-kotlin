package com.estudokotlin.estudokotlin.events

import com.estudokotlin.estudokotlin.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
        source: Any,
        val purchaseModel: PurchaseModel
): ApplicationEvent(source)