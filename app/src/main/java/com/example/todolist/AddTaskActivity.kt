package com.example.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.DatabaseHelper
import com.example.todolist.R
import com.example.todolist.R.id.editTextTitle

class AddTaskActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val db = DatabaseHelper(this)

        val editTextTitle = findViewById<EditText>(editTextTitle)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val spinnerPriority = findViewById<Spinner>(R.id.spinnerPriority)
        val buttonSave = findViewById<Button>(R.id.buttonSaveTask)

        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()
            val priority = spinnerPriority.selectedItem.toString()
            val deadline = "" // Add date picker here

            if (title.isNotEmpty() && description.isNotEmpty()) {
                db.addTask(title, description, priority, deadline)
                Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
