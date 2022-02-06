package com.estudokotlin.estudokotlin.validation

import com.estudokotlin.estudokotlin.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(value: String?, p1: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()){
            return false
        }
        return customerService.emailAvailable(value)
    }

}
