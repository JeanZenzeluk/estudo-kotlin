package com.estudokotlin.estudokotlin.controller.response

import com.estudokotlin.estudokotlin.enums.CustomerStatus
import javax.persistence.*

data class CustomerResponse(

        var id: Int? = null,

        var name: String,

        var email: String,

        var status: CustomerStatus

)