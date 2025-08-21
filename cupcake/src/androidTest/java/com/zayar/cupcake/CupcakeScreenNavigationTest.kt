package com.zayar.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        println("cupcakeNavHost_verifyStartDestination: ${navController.currentBackStackEntry?.destination?.route}")
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
//        composeTestRule.onNodeWithStringId(R.string.my_string)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)

        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    // Navigating to the Start screen by clicking the Up button from the Flavor screen
    @Test
    fun cupcakeNavHost_clickBackOnFlavorScreen_navigatesToStartOrderScreen() {
        navigateToFlavorScreen()
        performNavigateUp()

        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    // Navigating to the Start screen by clicking the Cancel button from the Flavor screen
    @Test
    fun cupcakeNavHost_clickCancelOnFlavorScreen_navigatesToStartOrderScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    // Navigating to the Pickup screen
    @Test
    fun cupcakeNavHost_clickNextOnFlavorScreen_navigatesToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    // Navigating to the Flavor screen by clicking the Up button from the Pickup screen
    @Test
    fun cupcakeNavHost_clickBackOnPickupScreen_navigatesToFlavorScreen() {
        navigateToPickupScreen()
        performNavigateUp()

        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    // Navigating to the Start screen by clicking the Cancel button from the Pickup screen
    @Test
    fun cupcakeNavHost_clickCancelOnPickupScreen_navigatesToStartOrderScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    // Navigating to the Summary screen
    @Test
    fun cupcakeNavHost_clickNextOnPickupScreen_navigatesToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()

        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)

    }

    // Navigating to the Start screen by clicking the Cancel button from the Summary screen
    @Test
    fun cupcakeNavHost_clickBackOnSummaryScreen_navigatesToStartOrderScreen() {
        navigateToSummaryScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.chocolate)
            .performClick()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }


}