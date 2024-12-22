package com.example.recipefinder.ui.home

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.fragment.app.Fragment
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Replace Fragment's view with Compose UI
        requireActivity().setContent {
            HomeScreen()
        }
    }
}
@Composable
fun HomeScreen() {
    // Get the HomeViewModel
    val homeViewModel: HomeViewModel = viewModel()

    // Observe the LiveData from the ViewModel
    val text by homeViewModel.text.observeAsState("")
    val searchQuery by homeViewModel.searchQuery.observeAsState("") // For the query in the SearchBar

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title Text for Home Screen (This is different from the search bar text)
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        SearchBar(query = searchQuery, onQueryChange = { homeViewModel.updateSearchQuery(it) }, onSearch = { /* Handle Search Action */ })

        Spacer(modifier = Modifier.height(16.dp))

        // Display other text after the Search Bar
        Text(
            text = text, // Display different text here
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
    }
}
