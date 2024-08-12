package com.uolimzhanov.navigationresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uolimzhanov.navigationresult.ui.theme.NavigationResultTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationResultTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screens.MainRoute) {
                        composable<Screens.MainRoute> { entry ->
                            val viewModel = hiltViewModel<MainViewModel>(entry)

                            MainScreenRoot(
                                viewModel = viewModel,
                                onNextClick = {
                                    navController.navigate(Screens.ResultReturningRouteA)
                                },
                                onGoToSecondScreen = {
                                    navController.navigate(Screens.SecondScreen)
                                }
                            )
                        }
                        composable<Screens.SecondScreen> { entry ->
                            val viewModel = hiltViewModel<SecondViewModel>(entry)

                            SecondScreenRoot(
                                viewModel = viewModel,
                                onClick = {
                                    navController.navigate(Screens.ResultReturningRouteB)
                                }
                            )
                        }
                        composable<Screens.ResultReturningRouteA> { entry ->
                            val parentEntry = remember(entry) {
                                navController.getBackStackEntry<Screens.MainRoute>()
                            }
                            val parentViewModel = hiltViewModel<MainViewModel>(parentEntry)

                            ScreenWithResultRoot(
                                onGoBack = { username ->
                                    parentViewModel.onUpdateText(username)
                                    navController.navigateUp()
                                }
                            )
                        }
                        composable<Screens.ResultReturningRouteB> { entry ->
                            val parentEntry = remember(entry) {
                                navController.getBackStackEntry<Screens.SecondScreen>()
                            }
                            val parentViewModel = hiltViewModel<SecondViewModel>(parentEntry)

                            ScreenWithResultRoot(
                                onGoBack = { username ->
                                    parentViewModel.onUpdateText(username)
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed interface Screens {
    @Serializable
    data object MainRoute : Screens

    @Serializable
    data object SecondScreen : Screens

    @Serializable
    data object ResultReturningRouteA : Screens

    @Serializable
    data object ResultReturningRouteB : Screens
}