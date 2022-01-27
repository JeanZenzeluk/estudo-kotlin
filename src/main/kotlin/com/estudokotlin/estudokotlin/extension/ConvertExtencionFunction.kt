package com.estudokotlin.estudokotlin.extension

import com.estudokotlin.estudokotlin.controller.request.PostBookRequest
import com.estudokotlin.estudokotlin.controller.request.PostCustomerRequest
import com.estudokotlin.estudokotlin.controller.request.PutCustomerRequest
import com.estudokotlin.estudokotlin.enums.BookStatus
import com.estudokotlin.estudokotlin.model.BookModel
import com.estudokotlin.estudokotlin.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return com.estudokotlin.estudokotlin.model.CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id:Int): CustomerModel {
    return com.estudokotlin.estudokotlin.model.CustomerModel(id = id, name = this.name, email = this.email)
}

fun String.primeiraLetra(): Char {
    return this.get(0)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
            name = this.name,
            price = this.price,
            status = BookStatus.ATIVO,
            customer = customer
    )
}