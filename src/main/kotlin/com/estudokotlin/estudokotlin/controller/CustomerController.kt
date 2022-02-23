package com.estudokotlin.estudokotlin.controller

import com.estudokotlin.estudokotlin.controller.request.PostCustomerRequest
import com.estudokotlin.estudokotlin.controller.request.PutCustomerRequest
import com.estudokotlin.estudokotlin.controller.response.CustomerResponse
import com.estudokotlin.estudokotlin.extension.primeiraLetra
import com.estudokotlin.estudokotlin.extension.toCustomerModel
import com.estudokotlin.estudokotlin.extension.toResponse
import com.estudokotlin.estudokotlin.security.UserCanOnlyAccessTheirOwnResource
import com.estudokotlin.estudokotlin.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customers")
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
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    fun getCustomer(@PathVariable id: Int) :CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserCanOnlyAccessTheirOwnResource
    fun update(@PathVariable id: Int, @RequestBody @Valid customer : PutCustomerRequest) {
        var customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserCanOnlyAccessTheirOwnResource
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }

}