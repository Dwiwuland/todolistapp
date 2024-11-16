package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.ViewTasksActivity
import com.example.todolist.AddTaskActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi tombol di halaman Home
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)
        val btnViewTasks = findViewById<Button>(R.id.btnViewTasks)
        val btnImportantTasks = findViewById<Button>(R.id.btnImportantTasks)

        // Navigasi ke halaman AddTaskActivity
        btnAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke halaman ViewTasksActivity
        btnViewTasks.setOnClickListener {
            val intent = Intent(this, ViewTasksActivity::class.java)
            startActivity(intent)
        }
        btnImportantTasks.setOnClickListener {
            // Navigasi ke halaman ImportantTasksActivity (nanti dibuat)
            val intent = Intent(this, ImportantTasksActivity::class.java)
            startActivity(intent)
        }

    }

}
