package com.estudokotlin.estudokotlin.extension

import com.estudokotlin.estudokotlin.controller.request.PostCustomerRequest
import com.estudokotlin.estudokotlin.controller.request.PutCustomerRequest
import com.estudokotlin.estudokotlin.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return com.estudokotlin.estudokotlin.model.CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id:String): CustomerModel {
    return com.estudokotlin.estudokotlin.model.CustomerModel(id = id, name = this.name, email = this.email)
}

fun String.primeiraLetra(): Char {
    return this.get(0)
}