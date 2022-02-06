package com.estudokotlin.estudokotlin.exception

class BadRequestException(override val message: String, val errorCode: String): Exception() {

}