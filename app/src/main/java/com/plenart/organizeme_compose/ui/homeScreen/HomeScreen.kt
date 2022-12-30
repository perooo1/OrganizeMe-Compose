package com.plenart.organizeme_compose.ui.homeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.data.auth.AuthResponse

@Composable
fun HomeScreenRoute(homeScreenViewModel: HomeScreenViewModel) {
    Log.i(
        "HomeScreenRoute",
        "Home screen route before getUserInfo() call: userData: ${homeScreenViewModel.userData.value.toString()}"
    )
    homeScreenViewModel.getUserInfo()
    Log.i("HomeScreenRoute", "Home screen route after getUserInfo() call")
    Log.i(
        "HomeScreenRoute",
        "Home screen route after getUserInfo() call: userData: ${homeScreenViewModel.userData.value.toString()}"
    )

    val userDataState = homeScreenViewModel.userData.value
    Log.i(
        "HomeScreenRoute",
        "Home screen route after getUserInfo() call, userdataState: ${userDataState.toString()}"
    )

    when (userDataState) {
        is AuthResponse.Error -> Log.i("HomeScreenRoute", "userDataState is ResponseError")
        AuthResponse.Loading -> Log.i("HomeScreenRoute", "userDataState is ResponseLoading")
        is AuthResponse.Success -> {
            val user = userDataState.data
            Log.i(
                "HomeScreenRoute",
                "Home screen route inside response Success: user: ${user.toString()}"
            )
            HomeScreen(user.name)
        }
    }

    //HomeScreen()
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
