package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setupTopAppBar() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen =RallyScreen.Accounts
                )
            }
        }
    }

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
//        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        composeTestRule
//            .onNodeWithText(RallyScreen.Accounts.name.uppercase(Locale.getDefault()))
//            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .onNode(
                matcher = hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault()))
                                    // 현재 context에서는 상위 노드의 contentDescription을 확인하지 않아도 테스트 통과가 된다.
//                                and hasParent(hasContentDescription(RallyScreen.Accounts.name))
                ,useUnmergedTree = true
            )
            .assertExists()
    }
}