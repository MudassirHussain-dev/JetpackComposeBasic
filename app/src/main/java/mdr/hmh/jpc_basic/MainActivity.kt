package mdr.hmh.jpc_basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.isPopupLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import mdr.hmh.jpc_basic.presentation.screens.LoginScreen
import mdr.hmh.jpc_basic.presentation.screens.home.HomeScreen
import mdr.hmh.jpc_basic.presentation.screens.login.LoginViewModel
import mdr.hmh.jpc_basic.presentation.screens.registration.RegisterViewModel
import mdr.hmh.jpc_basic.presentation.screens.registration.RegistrationScreen
import mdr.hmh.jpc_basic.ui.theme.JPC_BasicTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JPC_BasicTheme {
                val naveController = rememberAnimatedNavController()

                BoxWithConstraints {
                    AnimatedNavHost(
                        navController = naveController,
                        startDestination = Destination.Login.route
                    ) {
                        addLogin(navController = naveController)

                        addRegister(navController = naveController)
                        addHome()

                    }
                }

            }
        }
    }

    fun NavGraphBuilder.addLogin(
        navController: NavHostController
    ) {
        composable(
            route = Destination.Login.route,
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(500)
                )
            },
            exitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            }
        ) {
            val viewModel: LoginViewModel = hiltViewModel()
            val email = viewModel.state.value.email
            val password = viewModel.state.value.password

            if (viewModel.state.value.onSuccessLogin) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(
                        Destination.Home.route + "/$email" + "/$password"
                    ){
                        popUpTo(Destination.Login.route){
                            inclusive = true
                        }
                    }

                }
            } else {
                LoginScreen(
                    state = viewModel.state.value,
                    onLogin = viewModel::login,
                    onNavigateToRegister = {
                        navController.navigate(Destination.Register.route)

                    },
                    onDismissDialog = viewModel::hideErrorDialog

                )
            }
        }
    }

    fun NavGraphBuilder.addRegister(
        navController: NavHostController
    ) {
        composable(
            route = Destination.Register.route,
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(500)
                )
            },
            exitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            }
        ) {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegistrationScreen(
                state = viewModel.state.value,
                onRegister = viewModel::register,
                onBack = {
                    navController.popBackStack()
                },
                onDismissDialog = viewModel::hideErrorDialog
            )
        }


    }

    fun NavGraphBuilder.addHome() {
        composable(
            route = Destination.Home.route + "/{email}" + "{password}",
            arguments = Destination.Home.arguments
        ) {
            val email = it.arguments?.getString("email") ?: ""
            val password = it.arguments?.getString("password") ?: ""
            HomeScreen(email = email, password = password)
        }

    }
}
