package com.mrsnow.credit.application.system.service.impl

import com.mrsnow.credit.application.system.entity.Credit
import com.mrsnow.credit.application.system.repository.CreditRepository
import com.mrsnow.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply{
            customer = customerService.findById(credit.customer?.id!!)
        }

        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("Unknown credit code $creditCode"))

        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
    }
}