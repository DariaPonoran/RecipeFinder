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
import androidx.compose.ui.Alignment

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Replace Fragment's view with Compose UI
        requireActivity().setContent {
            DashboardScreen()
        }
    }
}

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorites",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        repeat(4) { index ->
            RecipeItem(title = "Recipe ${index + 1}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecipeItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // To place heart on the right
    ) {
        // Image placeholder on the left
        Box(
            modifier = Modifier
                .size(60.dp) // Adjust the size of the placeholder

        ) {
            // You can replace this with an actual Image using Coil or other image loading library
        }

        // Column for title and time
        Column(
            modifier = Modifier.weight(1f) // Takes up remaining space between the image and icon
        ) {
            Text(text = title, style = MaterialTheme.typography.body1)
            Text(text = "20 min.", style = MaterialTheme.typography.body2)
        }

        // Heart icon on the right
        Icon(
            Icons.Default.Favorite,
            contentDescription = "Favorite Icon",
            tint = Color.Red,
            modifier = Modifier.size(40.dp)
        )
    }
}
