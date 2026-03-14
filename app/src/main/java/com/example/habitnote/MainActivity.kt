package com.example.habitnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitnote.ui.theme.HabitNoteTheme
import com.example.habitnote.view.AddScreen
import com.example.habitnote.view.EditScreen
import com.example.habitnote.view.HomeScreen
import com.example.habitnote.view.ViewScreen
import com.example.habitnote.viewmodel.MainViewModel
import kotlin.getValue

class  MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitNoteTheme {
                val controller = rememberNavController()
                NavHost(controller, startDestination = "home"){
                    composable("home"){
                        HomeScreen(controller, viewModel)
                    }
                    composable("add") {
                        AddScreen(controller, viewModel)
                    }
                    composable("view/{id}") {
                        val id = it.    arguments?.getString("id")?.toInt() ?: 0
                        ViewScreen(controller, viewModel, id)
                    }
                    composable("edit/{id}") {
                        val id = it.arguments?.getString("id")?.toInt() ?: 0
                        EditScreen(controller, viewModel, id)
                    }
                }
            }
        }
    }
}

