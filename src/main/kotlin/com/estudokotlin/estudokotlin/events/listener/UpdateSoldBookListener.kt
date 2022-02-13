package com.estudokotlin.estudokotlin.events.listener

import com.estudokotlin.estudokotlin.events.PurchaseEvent
import com.estudokotlin.estudokotlin.service.BookService
import com.estudokotlin.estudokotlin.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookListener(
        private val bookService: BookService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }

}