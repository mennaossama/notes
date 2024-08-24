package com.example.notesapp.ui.edit_note.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.notesapp.R
import com.example.notesapp.data.models.Note
import com.example.notesapp.databinding.ActivityEditBinding
import com.example.notesapp.ui.edit_note.viewmodel.EditViewModel
import com.example.notesapp.ui.main.adapter.NotesAdapter
import com.example.notesapp.ui.main.view_model.MainViewModel
import kotlin.properties.Delegates

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    var title: String = ""
    var body: String = ""
    var id: Int = 0
    val viewModel: EditViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)

        title = intent.getStringExtra("noteTitle")!!
        body = intent.getStringExtra("noteBody")!!
        id = intent.getIntExtra("noteId", 0)

        binding.etTitle.setText(title)
        binding.etNote.setText(body)

        binding.btEdit.setOnClickListener {
            if (!binding.etTitle.text.isNullOrEmpty() &&
                !binding.etNote.text.isNullOrEmpty()
            ) {
                viewModel.updateNote(
                    Note(
                        id, binding.etTitle.text.toString(),
                        binding.etNote.text.toString()
                    )
                )
            }

            Toast.makeText(this, "Edit Successfully", Toast.LENGTH_SHORT).show()
            finish()

        }

        binding.binBtn.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to Delete this Note?")

        builder.setPositiveButton("OK") { dialog, which ->
            viewModel.deleteNote(Note(id, title, body))

            Toast.makeText(this, "Note Deleted Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
