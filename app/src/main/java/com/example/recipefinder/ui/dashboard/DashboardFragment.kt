package com.example.recipefinder.ui.dashboard

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.fragment.app.Fragment
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipefinder.ui.home.HomeViewModel

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = { dashViewModel.updateSearchQuery(it) },
            onSearch = { /* Handle Search Action */ })

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

    }
}

