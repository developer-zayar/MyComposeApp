package com.zayar.cupcake

import androidx.navigation.NavHostController
import org.junit.Assert.assertEquals

fun NavHostController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}