package com.uolimzhanov.navigationresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.uolimzhanov.navigationresult.ui.SecondViewModel
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
                                    navController.navigate(Screens.ResultReturningRoute("Main"))
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
                                    navController.navigate(Screens.ResultReturningRoute("Second"))
                                }
                            )
                        }
                        composable<Screens.ResultReturningRoute> { entry ->
                            val args = entry.toRoute<Screens.ResultReturningRoute>()
                            println(args.previousScreenName)
                            val parentEntry = remember(entry) {
                                navController.getBackStackEntry<Screens.MainRoute>()
                            }
                            val parentViewModel = hiltViewModel<MainViewModel>(parentEntry)

                            /*val parentViewModel = when (args.previousScreenName) {
                                "Main" -> {
                                }

                                "Second" -> {
                                    val parentEntry = remember(entry) {
                                        navController.getBackStackEntry<Screens.SecondScreen>()
                                    }
                                    hiltViewModel<SecondViewModel>(parentEntry)
                                }

                                else -> {
                                    val parentEntry = remember(entry) {
                                        navController.getBackStackEntry<Screens.MainRoute>()
                                    }
                                    hiltViewModel<MainViewModel>(parentEntry)
                                }
                            }
*/
                            ScreenWithResultRoot(
                                onGoBack = { username ->
                                    parentViewModel.updateGreetingMessage(username)
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
    data class ResultReturningRoute(val previousScreenName: String) : Screens
}