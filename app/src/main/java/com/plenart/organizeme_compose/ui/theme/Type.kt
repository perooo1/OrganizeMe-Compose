package com.plenart.organizeme_compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val SignInSignUpButtonText = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold
)

val IntroAppName = TextStyle(
    fontSize = 32.sp,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
)

val IntroHeroText = TextStyle(
    fontSize = 25.sp,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Light,
)

val IntroDescription = TextStyle(
    fontSize = 16.sp,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    color = androidx.compose.ui.graphics.Color.Gray
)

val SignUpSignInDescription = TextStyle(
    fontSize = 20.sp,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    color = androidx.compose.ui.graphics.Color.Gray
)

val DrawerHeaderUserName = TextStyle(
    fontSize = 60.sp,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal
)

val DrawerHeaderUserEmail = TextStyle(
    fontSize = 30.sp,
    fontFamily = FontFamily.Default,
    fontStyle = FontStyle.Italic
)
