package org.wahid.borutoappversion1.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val Purple400 = Color(0xFF380990)
val PurpleGrey40 = Color(0xFF393982)
val Gray = Color(0xFF4D4D57)
val Pink40 = Color(0xFF7D5260)
val StarColor = Color(0xFFFFC44D)


val ColorScheme.welcomeScreenBackgroundColor
    @Composable
    get() =
        if (isSystemInDarkTheme()) Color.Black else Color.White

val ColorScheme.titleColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black


val ColorScheme.topAppBarTitleColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Gray else Color.White
val ColorScheme.topAppBarContainerColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Purple400

val ColorScheme.shimmerBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black.copy(alpha = 0.5f) else Color.White
val ColorScheme.shimmerItemColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.4f) else Color.DarkGray.copy(alpha = .4f)
