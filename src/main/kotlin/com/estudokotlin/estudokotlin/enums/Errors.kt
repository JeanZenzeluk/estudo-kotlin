package com.estudokotlin.estudokotlin.enums

enum class Errors(val code: String, val message: String) {

    ML001("ML-001", "InvalidRequest"),
    ML101("ML-101", "Book [%s] not exists"),
    ML102("ML-102", "Não é possível alterar um livro com status [%s]"),
    ML201("ML-201", "Customer [%s] not exists")

}