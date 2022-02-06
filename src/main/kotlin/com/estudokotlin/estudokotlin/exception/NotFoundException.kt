package com.estudokotlin.estudokotlin.exception

class NotFoundException(override val message: String, val errorCode: String): Exception() {

}