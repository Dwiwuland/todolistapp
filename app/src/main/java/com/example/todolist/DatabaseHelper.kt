package com.example.todolist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ToDoList.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_TASK = "tasks"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PRIORITY = "priority"
        const val COLUMN_DEADLINE = "deadline"
        const val COLUMN_COMPLETED = "completed"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_TASK (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_DESCRIPTION TEXT," +
                "$COLUMN_PRIORITY TEXT," +
                "$COLUMN_DEADLINE TEXT," +
                "$COLUMN_COMPLETED INTEGER)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASK")
        onCreate(db)
    }

    // CRUD Operations (Create, Read, Update, Delete)

    fun addTask(title: String, description: String, priority: String, deadline: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_DESCRIPTION, description)
        values.put(COLUMN_PRIORITY, priority)
        values.put(COLUMN_DEADLINE, deadline)
        values.put(COLUMN_COMPLETED, 0)

        val success = db.insert(TABLE_TASK, null, values)
        db.close()
        return success > 0
    }

    @SuppressLint("Range")
    fun getAllTasks(): List<Map<String, String>> {
        val tasks = mutableListOf<Map<String, String>>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TASK", null)

        if (cursor.moveToFirst()) {
            do {
                tasks.add(
                    mapOf(
                        COLUMN_ID to cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        COLUMN_TITLE to cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        COLUMN_DESCRIPTION to cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                        COLUMN_PRIORITY to cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITY))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tasks
    }
    @SuppressLint("Range")
    fun getImportantTasks(): ArrayList<Task> {
        val importantTasks = ArrayList<Task>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_TASK WHERE $COLUMN_PRIORITY = 'High'",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val task = Task(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITY)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DEADLINE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETED)) == 1
                )
                importantTasks.add(task)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return importantTasks
    }


    fun updateTaskStatus(taskId: Int, isCompleted: Boolean) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_COMPLETED, if (isCompleted) 1 else 0)
        db.update(TABLE_TASK, values, "$COLUMN_ID=?", arrayOf(taskId.toString()))
        db.close()
    }


}
