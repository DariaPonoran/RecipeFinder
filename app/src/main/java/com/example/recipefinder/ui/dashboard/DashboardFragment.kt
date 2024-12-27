package com.example.recipefinder.ui.dashboard

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.fragment.app.Fragment
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setContent {
            DashboardScreen()
        }
    }
}

@Composable
fun DashboardScreen() {
    val dashViewModel: DashboardViewModel = viewModel()
    val text by dashViewModel.text.observeAsState("")
    val searchQuery by dashViewModel.searchQuery.observeAsState("")
    val searchResult by dashViewModel.searchResult.observeAsState("")
    val isSearching by dashViewModel.isSearching.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = { dashViewModel.updateSearchQuery(it) },
            onSearch = { dashViewModel.performSearch(searchQuery) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchQuery.isEmpty()) {
            Text(text = "No results", style = MaterialTheme.typography.body1)
        }
        else {
            if (searchResult.isNotEmpty()) {
                Text(text = searchResult, style = MaterialTheme.typography.body1)
            }
            else if (isSearching) {
                Text(text = "Searching...", style = MaterialTheme.typography.body1)
            }
    }
    }
}

