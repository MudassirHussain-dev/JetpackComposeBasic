package mdr.hmh.jpc_basic

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Destination(val route: String, val arguments: List<NamedNavArgument>) {
    object Login : Destination("login", emptyList())
    object Register : Destination("register", emptyList())
    object Home : Destination(
        "home", listOf(
            navArgument("email") { type = NavType.StringType },
            navArgument("password") { type = NavType.StringType }
        )
    )
}