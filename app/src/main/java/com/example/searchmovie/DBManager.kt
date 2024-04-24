package com.example.searchmovie

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DBManager(private val context: Context) {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    fun open(): DBManager {
        dbHelper = DatabaseHelper(context)
        database = dbHelper.writableDatabase
        return this
    }

    fun close() {
        dbHelper.close()
    }

    fun insert(movieTitle: String, desc: String) {
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.SUBJECT, movieTitle)
        contentValues.put(DatabaseHelper.DESC, desc)
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
    }

    fun fetch(): Cursor {
        val columns = arrayOf(DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC)
        val cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null)

        cursor?.moveToFirst()
        return cursor
    }

    fun update(_id : Long, movieTitle: String, desc: String): Int {
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.SUBJECT, movieTitle)
        contentValues.put(DatabaseHelper.DESC, desc)

        return (
                database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID +"="+_id, null)
                )
    }

    fun delete(_id: Long) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID+"="+_id, null)
    }

}