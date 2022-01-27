package com.estudokotlin.estudokotlin.repository

import com.estudokotlin.estudokotlin.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int>{

    fun findByNameContaining(name: String): List<CustomerModel>

}