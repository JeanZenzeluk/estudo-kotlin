package com.estudokotlin.estudokotlin.controller

import com.estudokotlin.estudokotlin.controller.mapper.PurchaseMapper
import com.estudokotlin.estudokotlin.request.PostPurchaseRequest
import com.estudokotlin.estudokotlin.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController("/purchase")
@RequestMapping("purchase")
class PurchaseController(
        private val purchaseService: PurchaseService,
        private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest){
        purchaseService.create(purchaseMapper.toModel(request))
    }


}