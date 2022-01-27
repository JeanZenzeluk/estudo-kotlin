package com.estudokotlin.estudokotlin.repository

import com.estudokotlin.estudokotlin.model.BookModel
import com.estudokotlin.estudokotlin.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int>{

}