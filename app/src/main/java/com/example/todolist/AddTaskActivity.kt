package com.example.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val db = DatabaseHelper(this)

        val editTextTitle = findViewById<EditText>(R.id.et_task_title)
        val editTextDescription = findViewById<EditText>(R.id.et_task_description)
        val spinnerPriority = findViewById<Spinner>(R.id.sp_task_priority)
        val buttonSave = findViewById<Button>(R.id.btn_save_task)

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

