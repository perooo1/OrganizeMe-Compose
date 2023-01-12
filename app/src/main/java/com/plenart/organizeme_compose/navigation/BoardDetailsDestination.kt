package com.plenart.organizeme_compose.navigation

const val BOARD_ID_KEY = "boardId"
const val BOARD_DETAILS_ROUTE = "Board_details_route"
const val BOARD_DETAILS_ROUTE_WITH_PARAMS = "$BOARD_DETAILS_ROUTE/{$BOARD_ID_KEY}"

object BoardDetailsDestination: OrganizeMeNavigation(BOARD_DETAILS_ROUTE_WITH_PARAMS){
    fun createNavigationRoute(boardId: String): String = "$BOARD_DETAILS_ROUTE/$boardId"
}
