package com.robert.projetofinalkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.robert.projetofinalkotlin.Note
import com.robert.projetofinalkotlin.databinding.NoteItemBinding

class NotesAdapter(private var notes :MutableList<Note>, context: Context):
        RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){
            private val db :NotesDataBaseHelper= NotesDataBaseHelper(context)
    class NoteViewHolder(private val binding : NoteItemBinding):
            RecyclerView.viewHolder(binding.root){
                val titleTextView: TextView =binding.tittleTextView
                val contentTextView: TextView =binding.contentTextView
                val optionButton: ImageView =binding.optionsButton
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int =notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text =note.title
        holder.contentTextView.text = note.content

        holder.optionButton.setOnClickListener{
            val popupMenu.inflate(R.menu.note_item_menu)


            popumenu.setOnMenuItemClickListener {menuItem ->
                when(menuItem.itemid){
                    R.id.editOption ->
                        val intent =
                            Intent(holder.itemView.context, updateNoteActivity::class).apply{
                                putExtra("note_id", note.id)
                            }
                    holder.itemView.context.starActivity(intent)
                            true

                }
                R.id.deleteOption ->
                db.deleteNote(note.id)
                removeItem(holder.adapterPosition)
                Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            else -> false
        }
    }
    fun refreshData(newNotes: List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()

    }
}