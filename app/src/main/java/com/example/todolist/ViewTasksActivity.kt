package com.example.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ViewTasksActivity : AppCompatActivity() {

    private lateinit var taskDatabaseHelper: DatabaseHelper
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)

        listView = findViewById(R.id.lv_all_tasks)

        // Inisialisasi database
        taskDatabaseHelper = DatabaseHelper(this)

        // Ambil semua tugas dari database
        val allTasks = taskDatabaseHelper.getAllTasks()

        // Format tampilan daftar untuk ListView
        val taskList = allTasks.map { task ->
            val title = task["title"] ?: "Tugas Tanpa Judul"
            val priority = task["priority"] ?: "Tidak Ada"
            "$title\nPrioritas: $priority"
        }

        // Set Adapter untuk ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        listView.adapter = adapter
    }
}
