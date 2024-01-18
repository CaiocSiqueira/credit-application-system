package com.mrsnow.credit.application.system.dto

import com.mrsnow.credit.application.system.entity.Credit
import java.math.BigDecimal
import java.util.*

data class CreditViewList(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOFInstallments: Int
    ) {

    constructor(credit: Credit): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOFInstallments = credit.numberOfInstallments
    )
}
