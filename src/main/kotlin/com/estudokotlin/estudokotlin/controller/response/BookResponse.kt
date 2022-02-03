package com.estudokotlin.estudokotlin.controller.response

import com.estudokotlin.estudokotlin.enums.BookStatus
import com.estudokotlin.estudokotlin.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(

        var id: Int? = null,

        var name: String,

        var price: BigDecimal,

        var customer: CustomerModel? = null,

        var status: BookStatus? = null

)