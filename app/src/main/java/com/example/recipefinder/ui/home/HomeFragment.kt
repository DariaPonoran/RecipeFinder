package com.example.recipefinder.ui.home

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.fragment.app.Fragment
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = viewModel()
    val text by homeViewModel.text.observeAsState("")
    val searchQuery by homeViewModel.searchQuery.observeAsState("")
    val searchResult by homeViewModel.searchResult.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = { homeViewModel.updateSearchQuery(it) },
            onSearch = { homeViewModel.searchRecipe(searchQuery) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchResult.isNotEmpty()) {
            Text(text = "Search Result: $searchResult", style = MaterialTheme.typography.body1)
        } else if (searchQuery.isEmpty()) {
            repeat(4) { index ->
                RecipeItem(title = "Recipe ${index + 1}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun RecipeItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp)
            .border(1.dp, Color.Gray, shape = com. example. recipefinder. ui. theme.Shapes.large)
            .background(Color.White, shape = RoundedCornerShape(18.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(18.dp))
        ) {
            // Placeholder for an image
        }
        Column(
            modifier = Modifier.weight(1f)
                .padding(10.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.body1)
            Text(text = "20 min.", style = MaterialTheme.typography.body2)
        }

        Icon(
            Icons.Default.Favorite,
            contentDescription = "Favorite Icon",
            tint = Color.Red,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterVertically)
        )
    }
}
