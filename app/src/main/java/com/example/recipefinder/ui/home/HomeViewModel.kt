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

    private val _text = MutableLiveData<String>().apply {
        value = "Favorites"
    }
    val text: LiveData<String> = _text

    private val _searchQuery = MutableLiveData<String>().apply {
        value = ""
    }
    val searchQuery: LiveData<String> = _searchQuery

    fun updateText(newText: String) {
        _text.value = newText
    }

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }
}


@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit, onSearch: () -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
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