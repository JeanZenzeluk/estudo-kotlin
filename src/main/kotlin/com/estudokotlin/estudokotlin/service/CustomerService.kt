package com.estudokotlin.estudokotlin.service

import com.estudokotlin.estudokotlin.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, ignoreCase = true) }
        }
        return customers
    }

    fun create(customer: CustomerModel) {

        var id = if(this.customers.isEmpty()) {
            1
        }else{
            this.customers.last().id!!.toInt() + 1
        }.toString()

        customer.id = id

        this.customers.add(CustomerModel(id, customer.name, customer.email))
        println(customer)
    }

    fun getCustomer(id: String) :CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun update(customer : CustomerModel) {
        customers.filter { it.id == customer.id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }

}