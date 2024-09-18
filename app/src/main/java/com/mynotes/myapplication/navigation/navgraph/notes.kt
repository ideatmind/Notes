// NavGraph.kt
package com.mynotes.myapplication.navigation.navgraph

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mynotes.myapplication.feature.favourites.presentation.FavouritesScreen
import com.mynotes.myapplication.feature.notes.presentation.AddEditNoteScreen
import com.mynotes.myapplication.feature.notes.presentation.NoteScreen
import com.mynotes.myapplication.navigation.Screen
import com.mynotes.myapplication.navigation.Tab

fun NavGraphBuilder.notes(navController: NavController) {
    navigation(
        startDestination = Screen.NoteScreen.route,
        route = Tab.Notes.route
    ) {
        composable(
            route = Screen.NoteScreen.route
        ) {
            NoteScreen(
                onAddNoteClick = { noteId ->
                    navController.navigate("${Screen.AddEditNoteScreen.route}/$noteId")
                },
                onEditNoteClick = { noteId, title, description ->
                    navController.navigate("${Screen.AddEditNoteScreen.route}/$noteId/$title/$description")
                }
            )
        }

        composable(
            route = "${Screen.AddEditNoteScreen.route}/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.IntType }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )
            }
        ) { entry ->
            val noteId = entry.arguments?.getInt("noteId") ?: -1
            AddEditNoteScreen(
                noteId = noteId,
                title = "",
                description = "",
                navController = navController
            )
        }

        composable(
            route = "${Screen.AddEditNoteScreen.route}/{noteId}/{title}/{description}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                )
            }
        ) { entry ->
            val noteId = entry.arguments?.getInt("noteId") ?: -1
            val title = entry.arguments?.getString("title") ?: ""
            val description = entry.arguments?.getString("description") ?: ""
            AddEditNoteScreen(
                noteId = noteId,
                title = title,
                description = description,
                navController = navController
            )
        }
    }
}


