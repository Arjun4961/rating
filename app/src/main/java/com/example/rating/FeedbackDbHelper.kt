package com.example.rating
// FeedbackDbHelper.kt

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedbackContract {
    // Define the table contents
    class FeedbackEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "feedback"
            const val COLUMN_NAME_CATEGORY = "category"
            const val COLUMN_NAME_RATING = "rating"
            const val COLUMN_NAME_IMPROVEMENT = "improvement"
        }
    }
}

class FeedbackDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES = """
        CREATE TABLE ${FeedbackContract.FeedbackEntry.TABLE_NAME} (
            ${BaseColumns._ID} INTEGER PRIMARY KEY,
            ${FeedbackContract.FeedbackEntry.COLUMN_NAME_CATEGORY} TEXT,
            ${FeedbackContract.FeedbackEntry.COLUMN_NAME_RATING} REAL,
            ${FeedbackContract.FeedbackEntry.COLUMN_NAME_IMPROVEMENT} TEXT
        )
    """.trimIndent()

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedbackContract.FeedbackEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Feedback.db"
    }
}
