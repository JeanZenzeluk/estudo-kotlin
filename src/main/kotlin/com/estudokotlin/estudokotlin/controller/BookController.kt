package com.estudokotlin.estudokotlin.controller

import com.estudokotlin.estudokotlin.controller.request.PostBookRequest
import com.estudokotlin.estudokotlin.extension.toBookModel
import com.estudokotlin.estudokotlin.service.BookService
import com.estudokotlin.estudokotlin.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("book")
class BookController (
        val bookService: BookService,
        val customerService: CustomerService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest){
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

}