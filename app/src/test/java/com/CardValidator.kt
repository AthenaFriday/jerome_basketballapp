package com

class CardValidator {

    private val allowedBanks = setOf("1101", "2032", "8733")

    fun isValidCard(cardNumber: String, expiryDate: String): Boolean {
        if (cardNumber.length != 16 || expiryDate.length != 4) return false

        val bankCode = cardNumber.substring(0, 4)
        val cardExpiry = cardNumber.substring(cardNumber.length - 4)

        if (bankCode !in allowedBanks) return false
        if (cardExpiry != expiryDate) return false

        return true
    }
}
