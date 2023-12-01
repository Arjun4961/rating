// MainActivity.kt

package com.example.rating

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var categorySpinner: Spinner
    private lateinit var ratingBar: RatingBar
    private lateinit var improvementEditText: EditText
    private lateinit var dbHelper: FeedbackDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categorySpinner = findViewById(R.id.categorySpinner)
        ratingBar = findViewById(R.id.ratingBar)
        improvementEditText = findViewById(R.id.improvementEditText)

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener { submitFeedback() }

        val recordVideoButton: Button = findViewById(R.id.recordVideoButton)
        recordVideoButton.setOnClickListener { recordVideo() }

        dbHelper = FeedbackDbHelper(this)
    }

    private fun submitFeedback() {
        val category: String = categorySpinner.selectedItem.toString()
        val rating: Float = ratingBar.rating
        val improvement: String = improvementEditText.text.toString()

        // Get the data repository in write mode
        val db = dbHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(FeedbackContract.FeedbackEntry.COLUMN_NAME_CATEGORY, category)
            put(FeedbackContract.FeedbackEntry.COLUMN_NAME_RATING, rating)
            put(FeedbackContract.FeedbackEntry.COLUMN_NAME_IMPROVEMENT, improvement)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(FeedbackContract.FeedbackEntry.TABLE_NAME, null, values)

        Toast.makeText(this, "Feedback submitted with ID: $newRowId", Toast.LENGTH_LONG).show()
    }

    private fun recordVideo() {
        // TODO: Implement video recording functionality
        Toast.makeText(this, "Recording Video", Toast.LENGTH_SHORT).show()
    }
}



