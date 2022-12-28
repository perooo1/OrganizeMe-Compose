package com.plenart.organizeme_compose.navigation

const val HOME_ROUTE = "Home_route"
const val SIGN_UP_ROUTE = "Sign_in_route"
const val SIGN_IN_ROUTE = "Sign_up_route"

sealed class NavigationItem(
    override val route: String
) : OrganizeMeNavigation(route) {

    object HomeDestination : NavigationItem(HOME_ROUTE)
    object SignUpDestination : NavigationItem(SIGN_UP_ROUTE)
    object SignInDestination : NavigationItem(SIGN_IN_ROUTE)

}
