package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesSate

    NavHost(
        navController = navController,
        startDestination = Screen.RecipeScreen.route
    ) {

        composable(Screen.RecipeScreen.route) {
            RecipeScreen(viewstate) { category ->
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("cat", category)

                navController.navigate(Screen.CategoryDetailScreen.route)
            }
        }

        composable(Screen.CategoryDetailScreen.route) {
            val category =
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Category>("cat")
                    ?: Category("", "", "", "")

            CategoryDetailScreen(category)
        }
    }
}
