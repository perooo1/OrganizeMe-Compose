package com.plenart.organizeme_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.plenart.organizeme_compose.ui.theme.OrganizeMeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrganizeMeComposeTheme {
                MainScreen()
            }
        }
    }
}
