package com.example.notesapp.ui.edit_note.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.AppDatabase
import com.example.notesapp.data.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditViewModel(val app: Application) : AndroidViewModel(app) {


    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            val noteDao = AppDatabase.DatabaseBuilder.getInstance(app.applicationContext).noteDao()
            noteDao.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            val noteDao = AppDatabase.DatabaseBuilder.getInstance(app.applicationContext).noteDao()
            noteDao.deleteNote(note)
        }
    }
}