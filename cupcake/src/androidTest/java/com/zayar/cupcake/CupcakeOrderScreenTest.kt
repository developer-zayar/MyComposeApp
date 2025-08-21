package com.zayar.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.zayar.cupcake.data.DataSource
import com.zayar.cupcake.data.OrderUiState
import com.zayar.cupcake.ui.OrderSummaryScreen
import com.zayar.cupcake.ui.SelectOptionScreen
import com.zayar.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal, flavors)
        }

        // All the options are displayed on the screen.
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    fun selectOptionScreen_optionSelected_NextButtonClick() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        composeTestRule.onNodeWithText(flavors.first())
            .performClick()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()

    }

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }

        DataSource.quantityOptions.forEach { item ->
            composeTestRule.onNodeWithStringId(item.first).assertIsDisplayed()
        }

    }

    @Test
    fun summaryScreen_verifyContent() {
        val fakeOrderUiState = OrderUiState(
            quantity = 1,
            flavor = "Vanilla",
            date = getFormattedDate(),
            price = "$100",
        )

        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = fakeOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { subject: String, summary: String -> },
            )
        }

        val expectedText = composeTestRule.activity.resources.getQuantityString(
            R.plurals.cupcakes,
            fakeOrderUiState.quantity,
            fakeOrderUiState.quantity
        )

        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()

        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()

        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                fakeOrderUiState.price
            )
        ).assertIsDisplayed()

    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }
}