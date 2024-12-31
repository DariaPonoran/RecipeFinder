package com.example.recipefinder.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecipeDetailScreen(navController: NavController, recipeTitle: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Gray)
        ) {
            Text(
                text = "No Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                style = MaterialTheme.typography.h6.copy(color = Color.White)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = recipeTitle,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "20 min.", style = MaterialTheme.typography.body2)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Ingredients:", fontWeight = FontWeight.Bold)
        Text("• 5 pounds potatoes")
        Text("• 2 large cloves garlic, minced")
        Text("• Fine sea salt")
        Text("• 6 tablespoons butter")
        Text("• 1 cup whole milk")
        Text("• 4 ounces cream cheese")

        Spacer(modifier = Modifier.height(16.dp))
        
        Text("Instructions:", fontWeight = FontWeight.Bold)
        Text("1. Cut the potatoes and prepare them...")
        Text("2. Boil the potatoes...")
        Text("3. Prepare butter mixture...")
        Text("4. Pan-dry the potatoes...")
        Text("5. Mash the potatoes...")
    }
}
