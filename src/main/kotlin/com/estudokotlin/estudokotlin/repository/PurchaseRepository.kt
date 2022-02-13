package com.estudokotlin.estudokotlin.repository

import com.estudokotlin.estudokotlin.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int> {

}
