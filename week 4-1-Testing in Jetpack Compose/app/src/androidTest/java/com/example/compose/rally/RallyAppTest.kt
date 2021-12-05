package com.example.compose.rally

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test

class RallyAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test()
    fun rallyApp_overviewScreen_tabSelected() {
        var tabScreen:RallyScreen? = null
        composeTestRule.setContent {
            RallyApp(
                currentScreen = RallyScreen.Overview,
                onTabSelected = {
                    tabScreen = it
                }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .performClick()

        tabScreen?.let {
            assert(tabScreen == RallyScreen.Overview)
        }?: fail()
    }

    @Test()
    fun rallyApp_accountScreen_tabSelected() {
        var tabScreen:RallyScreen? = null
        composeTestRule.setContent {
            RallyApp(
                currentScreen = RallyScreen.Overview,
                onTabSelected = {
                    tabScreen = it
                }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .performClick()

        tabScreen?.let {
            assert(tabScreen == RallyScreen.Accounts)
        }?: fail()

    }

    @Test()
    fun rallyApp_biilsScreen_tabSelected() {
        var tabScreen:RallyScreen? = null
        composeTestRule.setContent {
            RallyApp(
                currentScreen = RallyScreen.Overview,
                onTabSelected = {
                    tabScreen = it
                }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .performClick()

        tabScreen?.let {
            assert(tabScreen == RallyScreen.Bills)
        }?: fail()

    }
}