package com.example.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.recipefinder.ui.dashboard.DashboardScreen
import com.example.recipefinder.ui.home.HomeScreen
import com.example.recipefinder.ui.home.RecipeDetailScreen
import com.example.recipefinder.ui.theme.RecipeFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeFinderTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("dashboard") { DashboardScreen() }
            composable("recipeDetail/{recipeTitle}") { backStackEntry ->
                val recipeTitle = backStackEntry.arguments?.getString("recipeTitle") ?: ""
                RecipeDetailScreen(navController, recipeTitle)
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.Gray,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = {
                Icon(Icons.Default.Favorite, contentDescription = "Faves")
            },
            label = { Text("Faves") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("dashboard") },
            icon = {
                Icon(Icons.Default.Search, contentDescription = "Find")
            },
            label = { Text("Find") }
        )
    }
}


