package com.estudokotlin.estudokotlin.extension

import com.estudokotlin.estudokotlin.controller.request.PostBookRequest
import com.estudokotlin.estudokotlin.controller.request.PostCustomerRequest
import com.estudokotlin.estudokotlin.controller.request.PutBookRequest
import com.estudokotlin.estudokotlin.controller.request.PutCustomerRequest
import com.estudokotlin.estudokotlin.controller.response.BookResponse
import com.estudokotlin.estudokotlin.controller.response.CustomerResponse
import com.estudokotlin.estudokotlin.controller.response.PageResponse
import com.estudokotlin.estudokotlin.enums.BookStatus
import com.estudokotlin.estudokotlin.enums.CustomerStatus
import com.estudokotlin.estudokotlin.model.BookModel
import com.estudokotlin.estudokotlin.model.CustomerModel
import org.springframework.data.domain.Page

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return com.estudokotlin.estudokotlin.model.CustomerModel(name = this.name, email = this.email,
            status = CustomerStatus.ATIVO, password = this.password)
}

fun PutCustomerRequest.toCustomerModel(previousValue:CustomerModel): CustomerModel {
    return com.estudokotlin.estudokotlin.model.CustomerModel(id = previousValue.id, name = this.name,
            email = this.email, status = previousValue.status, password = previousValue.password)
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

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
            id = previousValue.id,
            name = this.name ?: previousValue.name, // seta o previousValue apenas se o name for nullo
            price = this.price ?: previousValue.price,
            status = previousValue.status,
            customer = previousValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
            id = this.id,
            name = this.name,
            email = this.email,
            status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
            id = this.id,
            name = this.name,
            price = this.price,
            customer = this.customer,
            status = this.status
    )
}

fun <T>Page<T>.toPageResponse(): PageResponse<T>{
    return PageResponse(this.content,
            this.number,
            this.totalElements,
            this.totalPages)
}
