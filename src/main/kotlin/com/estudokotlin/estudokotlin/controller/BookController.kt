package com.estudokotlin.estudokotlin.controller

import com.estudokotlin.estudokotlin.controller.request.PostBookRequest
import com.estudokotlin.estudokotlin.controller.request.PutBookRequest
import com.estudokotlin.estudokotlin.controller.response.BookResponse
import com.estudokotlin.estudokotlin.extension.toBookModel
import com.estudokotlin.estudokotlin.extension.toResponse
import com.estudokotlin.estudokotlin.service.BookService
import com.estudokotlin.estudokotlin.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("book")
class BookController (
        val bookService: BookService,
        val customerService: CustomerService
){


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/active")
    fun findActivities(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findActives(pageable).map{ it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody body: PutBookRequest){
        val bookSaved = bookService.findById(id)
        bookService.update(bookSaved)
    }

}