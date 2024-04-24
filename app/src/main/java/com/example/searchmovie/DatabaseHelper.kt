package com.example.searchmovie

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context?,
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    companion object {
        val TABLE_NAME = "MOVIES"
        private val DB_NAME = "MOVIES.DB"
        val _ID = "_id"
        val SUBJECT = "movieTitle"
        val DESC = "description"
        private val DB_VERSION = 1

        private val CREATE_TABLE = "create table $TABLE_NAME($_ID INTEGER PRIMARY KEY AUTOINCREMENT, $SUBJECT TEXT NOT NULL, $DESC TEXT)"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}