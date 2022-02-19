package com.estudokotlin.estudokotlin.service

import com.estudokotlin.estudokotlin.exception.AuthenticationException
import com.estudokotlin.estudokotlin.repository.CustomerRepository
import com.estudokotlin.estudokotlin.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
        private val customerRepository: CustomerRepository
): UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt())
                .orElseThrow { AuthenticationException("Usuário não encontrado", "999") }
        return UserCustomDetails(customer)

    }


}