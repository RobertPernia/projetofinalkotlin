package com.robert.projetofinalkotlin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.robert.projetofinalkotlin.addNotesActivity
import com.robert.projetofinalkotlin.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db :NotesDataBaseHelper
    private lateinit var notesAdapter: NotesAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db =NotesDataBaseHelper(this)
        val notelist:MutableList<Note> = db.getAllNotes().toMutableList()
        notesAdapter=NotesAdapter(notelist,this)


        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter =notesAdapter


        binding.addButton.setOnClickListener(){
            val intent = Intent(this,addNotesActivity::class.java
            startActivity(intent))
        }
}

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }