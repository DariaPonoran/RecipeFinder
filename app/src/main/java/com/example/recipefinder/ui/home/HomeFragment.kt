package com.example.recipefinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.fragment.app.Fragment
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()
                HomeScreen(navController)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel: HomeViewModel = viewModel()
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
            text = "Favorites",
            style = TextStyle(
                fontWeight = FontWeight.W700,
                fontSize = 38.sp,
                lineHeight = 38.4.sp,
            ),
            modifier = Modifier
                .padding(0.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchResult.isNotEmpty()) {
            Text(text = "Search Result: $searchResult", style = MaterialTheme.typography.body1)
        } else if (searchQuery.isEmpty()) {
            repeat(4) { index ->
                RecipeItem(title = "Recipe ${index + 1}",
                onClick = { navController.navigate("recipeDetail/Recipe${index + 1}") })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun RecipeItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .clickable { onClick() }
            .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(top = 12.dp)
                    .weight(1f),
                text = title,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.W700
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 22.dp),
                text = "20 min.",
                style = MaterialTheme.typography.body2
            )
        }

        var isSelected by remember { mutableStateOf(false) }

        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite Icon",
            tint = if (isSelected) Color.LightGray else Color(0xFF6B4E9C),
            modifier = Modifier
                .size(30.dp)
                .padding(end = 6.dp)
                .clickable {
                    isSelected = !isSelected
                }
        )
    }
}
