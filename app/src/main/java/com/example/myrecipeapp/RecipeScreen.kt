package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(recipeUiState:MainViewModel.RecipeState, navigateToDetail:(Category) -> Unit){
//    val recipeViewModel: MainViewModel = viewModel()
//    val recipeUiState by recipeViewModel.categoriesSate

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        when {
            recipeUiState.loading->{
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            recipeUiState.error != null ->{
                Text(text = recipeUiState.error.toString(), Modifier.align(Alignment.Center))
            }
            else ->{
                CategoryScreen(categories = recipeUiState.list, navigateToDetail )
//                RecipeList(recipe = recipeUiState.recipe)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>,navigateToDetail:(Category)->Unit){
    LazyVerticalGrid(GridCells.Fixed(2),modifier = Modifier.fillMaxSize()

        ) {
        items(categories) { item: Category ->
            CategoryItem(category = item,navigateToDetail)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navigateToDetail: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.padding(4.dp)
        )
    }
}