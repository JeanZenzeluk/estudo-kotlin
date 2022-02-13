package com.estudokotlin.estudokotlin.controller.mapper

import com.estudokotlin.estudokotlin.model.PurchaseModel
import com.estudokotlin.estudokotlin.request.PostPurchaseRequest
import com.estudokotlin.estudokotlin.service.BookService
import com.estudokotlin.estudokotlin.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
        private val bookService: BookService,
        private val customerService: CustomerService
) {

    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return PurchaseModel(
                customer = customer,
                books = books.toMutableList(),
                price = books.sumOf { it.price}
        )

    }

}