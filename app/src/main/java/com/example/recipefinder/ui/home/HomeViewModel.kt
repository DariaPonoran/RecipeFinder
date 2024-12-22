package com.example.recipefinder.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class HomeViewModel : ViewModel() {

    // LiveData to hold the text displayed below the search bar
    private val _text = MutableLiveData<String>().apply {
        value = "Favorites"
    }
    val text: LiveData<String> = _text

    // LiveData to hold the search query
    private val _searchQuery = MutableLiveData<String>().apply {
        value = ""
    }
    val searchQuery: LiveData<String> = _searchQuery

    // Method to update the text value displayed below the search bar
    fun updateText(newText: String) {
        _text.value = newText
    }

    // Method to update the search query
    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }
}


@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit, onSearch: () -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange, // This will call updateText when value changes
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = { Text("What do you feel like eating?") },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = null)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray)
    )
}