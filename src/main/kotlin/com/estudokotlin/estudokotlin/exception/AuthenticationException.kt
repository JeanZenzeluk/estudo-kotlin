package com.estudokotlin.estudokotlin.exception

class AuthenticationException(override val message: String, val errorCode: String): Exception() {

}