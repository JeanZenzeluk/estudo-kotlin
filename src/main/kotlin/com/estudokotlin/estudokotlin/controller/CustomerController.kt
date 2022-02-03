package com.estudokotlin.estudokotlin.controller

import com.estudokotlin.estudokotlin.controller.request.PostCustomerRequest
import com.estudokotlin.estudokotlin.controller.request.PutCustomerRequest
import com.estudokotlin.estudokotlin.controller.response.CustomerResponse
import com.estudokotlin.estudokotlin.extension.primeiraLetra
import com.estudokotlin.estudokotlin.extension.toCustomerModel
import com.estudokotlin.estudokotlin.extension.toResponse
import com.estudokotlin.estudokotlin.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController(
        val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        "teste".primeiraLetra()
        return customerService.getAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int) :CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer : PutCustomerRequest) {
        var customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }

}