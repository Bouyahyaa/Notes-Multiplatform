package com.bouyahya.notes.features.notes.ui.allnotes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bouyahya.notes.features.notes.domain.Note

@Composable
fun NotesListScreen(notesList: List<Note>) {
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
                onClick = { /* Handle add note click */ },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            )
        },
        content = {
            NotesList(notesList = notesList)
        }
    )
}

@Composable
fun NotesList(notesList: List<Note>) {
    if (notesList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "No notes found!",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn {
            items(notesList) { note ->
                NoteListItem(note = note)
            }
        }
    }
}

@Composable
fun NoteListItem(note: Note) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit",
            modifier = Modifier
                .size(40.dp)
                .background(
                    MaterialTheme.colors.secondaryVariant,
                    CircleShape
                )
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = note.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colors.error, CircleShape)
                .padding(8.dp)
        )
    }
}