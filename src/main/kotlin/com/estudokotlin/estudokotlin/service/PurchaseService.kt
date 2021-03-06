package com.estudokotlin.estudokotlin.service

import com.estudokotlin.estudokotlin.events.PurchaseEvent
import com.estudokotlin.estudokotlin.model.PurchaseModel
import com.estudokotlin.estudokotlin.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private val purchaseRepository: PurchaseRepository,
        private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel   )
    }

}
