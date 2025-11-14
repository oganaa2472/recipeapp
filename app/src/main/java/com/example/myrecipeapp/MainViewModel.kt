package com.example.myrecipeapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    data class RecipeState(
        val loading: Boolean = false,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
    init {
        fetchCategories()
    }
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesSate: State<RecipeState> = _categoriesState


    private fun fetchCategories() {
        viewModelScope.launch {
            try{
                val response = recipeService.getCategories()

                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,error = null
                )
            }catch (e:Exception){
                _categoriesState.value = _categoriesState.value.copy(
                    error = "Error fetching categories ${e.message}",
                    loading = false
                )
            }
        }
    }

}