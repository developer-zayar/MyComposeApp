package com.zayar.mycomposeapp

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20Percent_NoRoundUp() {
        val amount = 100.0
        val tipPercent = 20.0

        val expectedTip = NumberFormat.getCurrencyInstance().format(20)

        val actualTip = calculateTip(amount, tipPercent, false)

        assertEquals(actualTip, expectedTip) //{ "Expected $expectedTip but got $actualTip" }
    }

}