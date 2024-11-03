package com.robert.projetofinalkotlin


import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.robert.projetofinalkotlin.Note
import com.robert.projetofinalkotlin.databinding.ActivityAddNoteBinding



class addNotesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db :NotesDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDataBaseHelper(this)

        binding.saveButton.setOnClickListener(){
            val tittle = binding.tittleEditText.text.toString()
            val content = binding.contenteEditText.text.toString()
            val note = Note(0, title, content )
            db. insertNote(note)
            finish()
            Toast.makeText(this, "nota guardada",Toast.LENGTH_SHORT).show()


        }





    }
}