package com.example.searchmovie

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    private lateinit var dbManager: DBManager
    private lateinit var savedBinding: FragmentSavedBinding

    private val from = arrayOf(DatabaseHelper._ID, DatabaseHelper.SUBJECT)
    // private val to = intArrayOf(R.id.id, R.id.title, R.id.desc)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        savedBinding = FragmentSavedBinding.inflate(inflater, container, false)

        dbManager = context?.let { DBManager(it) }!!
        dbManager.open()

        Log.w("SavedFragment", "Created Saved Fragment")

        val cursor: Cursor = dbManager.fetch()

        if(cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.SUBJECT))
                val id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper._ID))

                val textView = TextView(context)
                textView.text = "$id: ${title}"

                savedBinding.linearLayoutItems.addView(textView)

            }
                while (cursor.moveToNext())

                cursor.close()
        }

        return savedBinding.root
    }
}
