package com.example.staticappshortcuts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.staticappshortcuts.ui.screen.EventsScreen
import com.example.staticappshortcuts.ui.screen.HomeScreen
import com.example.staticappshortcuts.ui.screen.SportsScreen
import com.example.staticappshortcuts.ui.theme.StaticAppShortcutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val startDestination = when (intent?.data?.toString()) {
            "app://sports" -> "sports"
            "app://events" -> "events"
            else -> "home"
        }
        setContent {
            StaticAppShortcutsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StaticAppShortcutsScreen(
                        modifier = Modifier.padding(innerPadding),
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}

@Composable
fun StaticAppShortcutsScreen(modifier: Modifier = Modifier, startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") { HomeScreen(navController) }
        composable("sports") { SportsScreen() }
        composable("events") { EventsScreen() }
    }
}