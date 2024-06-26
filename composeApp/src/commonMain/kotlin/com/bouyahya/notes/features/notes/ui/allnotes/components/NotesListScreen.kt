package com.bouyahya.notes.features.notes.ui.allnotes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bouyahya.notes.features.notes.domain.model.Note
import com.bouyahya.notes.features.notes.ui.allnotes.NotesEvent
import com.bouyahya.notes.navigation.home.note.NoteScreenRoute

@Composable
fun NotesListScreen(
    notesList: List<Note>,
    onEvent: (NotesEvent) -> Unit,
    navController: NavController,
) {
    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                title = {
                    Text(text = "My Notes")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                onClick = {
                    navController.navigate(NoteScreenRoute.AddEditNote.route)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
            )
        },
    ) {
        NotesList(
            notesList = notesList,
            onEvent = onEvent,
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotesList(
    notesList: List<Note>,
    onEvent: (NotesEvent) -> Unit,
    navController: NavController
) {
    if (notesList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "No notes found!",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            items(notesList) { note ->
                ListItem(
                    icon = {
                        IconButton(
                            onClick = {
                                navController.navigate(NoteScreenRoute.AddEditNote.route + "?id=${note.id}")
                            },
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colors.secondaryVariant,
                                    CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                            )
                        }
                    },
                    trailing = {
                        IconButton(
                            onClick = {
                                onEvent(NotesEvent.DeleteNote(note.id))
                            },
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colors.error,
                                    CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                            )
                        }
                    },
                    text = {
                        Text(
                            text = note.title,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.body1
                        )
                    },
                    secondaryText = {
                        Text(
                            text = note.description,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.body1
                        )
                    }
                )
            }
        }
    }
}