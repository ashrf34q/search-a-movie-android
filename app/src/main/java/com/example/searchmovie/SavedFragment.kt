package com.example.searchmovie

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter

class SavedFragment : Fragment() {

    private lateinit var dbManager: DBManager

    private val from = arrayOf(DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC)
    // private val to = intArrayOf(R.id.id, R.id.title, R.id.desc)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dbManager = context?.let { DBManager(it) }!!
        dbManager.open()

        Log.w("SavedFragment", "Created Saved Fragment")

//        TODO("Create an instance of DBManager, call fetch() load the returned cursor into the CursorAdapter which in turn is loaded into the ListView")

        val cursor: Cursor = dbManager.fetch()
        // val cursorAdapter = SimpleCursorAdapter(context, com.google.android.material.R.layout.abc_activity_chooser_view_list_item, cursor, false)

        return inflater.inflate(R.layout.fragment_saved, container, false)
    }
}


/*
private lateinit var activityCallback: SavedDataListener
 */