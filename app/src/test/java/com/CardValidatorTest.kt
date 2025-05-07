package com

import com.android.basketballapp.data.utils.CardValidator
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CardValidatorTest {

    private lateinit var validator: CardValidator

    @Before
    fun setup() {
        validator = CardValidator()
    }

    @Test
    fun validCard_shouldReturnTrue() {
        // Arrange
        val cardNumber = "1101123412341234"
        val expiryDate = "1234"

        // Act
        val result = validator.isValidCard(cardNumber, expiryDate)

        // Assert
        assertTrue(result)
    }

    @Test
    fun invalidBank_shouldReturnFalse() {
        // Arrange
        val cardNumber = "9999123412341234"
        val expiryDate = "1234"

        // Act
        val result = validator.isValidCard(cardNumber, expiryDate)

        // Assert
        assertFalse(result)
    }

    @Test
    fun mismatchedExpiry_shouldReturnFalse() {
        // Arrange
        val cardNumber = "2032123412345678"
        val expiryDate = "9999"

        // Act
        val result = validator.isValidCard(cardNumber, expiryDate)

        // Assert
        assertFalse(result)
    }

    @Test
    fun shortCardNumber_shouldReturnFalse() {
        // Arrange
        val cardNumber = "11011234"
        val expiryDate = "1234"

        // Act
        val result = validator.isValidCard(cardNumber, expiryDate)

        // Assert
        assertFalse(result)
    }

    @Test
    fun shortExpiryDate_shouldReturnFalse() {
        // Arrange
        val cardNumber = "8733123412345678"
        val expiryDate = "12"

        // Act
        val result = validator.isValidCard(cardNumber, expiryDate)

        // Assert
        assertFalse(result)
    }

    @Test
    fun emptyInputs_shouldReturnFalse() {
        // Arrange
        val cardNumber = ""
        val expiryDate = ""

        // Act
        val result = validator.isValidCard(cardNumber, expiryDate)

        // Assert
        assertFalse(result)
    }

    @Test
    fun validCardWithDifferentAllowedBank_shouldReturnTrue() {
        // Arrange
        val cardNumber = "8733123412349999"
        val expiryDate = "9999"

        // Act
        val result = validator.isValidCard(cardNumber, expiryDate)

        // Assert
        assertTrue(result)
    }

    @Test
    fun validCardWithWhitespace_shouldReturnFalse() {
        // Arrange
        val cardNumber = "1101 1234 1234 1234"
        val expiryDate = "1234"

        // Act
        val result = validator.isValidCard(cardNumber.replace(" ", ""), expiryDate)

        // Assert
        assertTrue(result)
    }
}
