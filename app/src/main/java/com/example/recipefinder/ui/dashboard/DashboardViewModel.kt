package com.example.recipefinder.ui.dashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Suggested Recipes"
    }
    val text: LiveData<String> = _text

    private val _searchQuery = MutableLiveData<String>().apply {
        value = ""
    }
    val searchQuery: LiveData<String> = _searchQuery

    private val _searchResult = MutableLiveData<String>().apply {
        value = ""
    }
    val searchResult: LiveData<String> = _searchResult

    private val _isSearching = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isSearching: LiveData<Boolean> = _isSearching

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
        _isSearching.value = true
    }

    fun performSearch(query: String) {
        if (query.isNotEmpty()) {
            _isSearching.value = false
            _searchResult.value = "Search result for '$query'"
        } else {
            _isSearching.value = false
            _searchResult.value = "No results"
        }
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