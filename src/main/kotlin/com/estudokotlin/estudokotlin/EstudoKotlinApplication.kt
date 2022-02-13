package com.estudokotlin.estudokotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class EstudoKotlinApplication

fun main(args: Array<String>) {
	runApplication<EstudoKotlinApplication>(*args)
}
