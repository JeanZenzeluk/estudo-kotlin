package com.estudokotlin.estudokotlin.repository

import com.estudokotlin.estudokotlin.enums.BookStatus
import com.estudokotlin.estudokotlin.model.BookModel
import com.estudokotlin.estudokotlin.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository : JpaRepository<BookModel, Int> {
    fun finByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>

//    fun findAll(pageable: Pageable): Page<BookModel>
}