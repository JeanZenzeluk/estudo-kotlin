package com.estudokotlin.estudokotlin.service

import com.estudokotlin.estudokotlin.model.BookModel
import com.estudokotlin.estudokotlin.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) {
        bookRepository.save(book)
    }


}
