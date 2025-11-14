package com.example.myrecipeapp

sealed class Screen (val route: String){
    object RecipeScreen:Screen(route = "recipe_screen")
    object CategoryDetailScreen:Screen(route = "recipe_detail_screen")
}