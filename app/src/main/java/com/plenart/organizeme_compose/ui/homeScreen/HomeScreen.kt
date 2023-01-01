package com.plenart.organizeme_compose.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R

@Composable
fun HomeScreenRoute(homeScreenViewModel: HomeScreenViewModel) {

    homeScreenViewModel.getUserInfo()
    val userDataState = homeScreenViewModel.userData.collectAsState()

    if (userDataState.value != null) {
        HomeScreen(userDataState.value!!.name)
    } else {
        HomeScreen(userName = "Trenutno nuill")
    }
}

@Composable
fun HomeScreen(
    userName: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = userName)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "img"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen("this is homescreen placeholder txt")
}
