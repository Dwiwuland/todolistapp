package com.example.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ImportantTasksActivity : AppCompatActivity() {

    private lateinit var taskDatabaseHelper: DatabaseHelper
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_important_tasks)

        listView = findViewById(R.id.lv_important_tasks)

        // Inisialisasi database
        taskDatabaseHelper = DatabaseHelper(this)

        // Ambil tugas dengan prioritas "Penting" dari database
        val importantTasks = taskDatabaseHelper.getImportantTasks()

// Contoh pemetaan untuk menampilkan hanya judul dan prioritas dalam ListView
        val taskTitles =
            importantTasks.map { task -> "${task.title} (Prioritas: ${task.priority})" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskTitles)
        listView.adapter = adapter
    }
    }
