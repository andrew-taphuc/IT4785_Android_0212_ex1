package com.example.it4785_android_0212_ex1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSinhVien: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupRecyclerView()
    }

    private fun initViews() {
        rvSinhVien = findViewById(R.id.rvSinhVien)
    }

    private fun setupRecyclerView() {
        refreshStudentList()
        rvSinhVien.layoutManager = LinearLayoutManager(this)
    }

    private fun refreshStudentList() {
        val students = StudentManager.getAllStudents().toMutableList()
        adapter = StudentAdapter(students) { position ->
            val student = students[position]
            val intent = Intent(this, StudentDetailActivity::class.java).apply {
                putExtra("MSSV", student.mssv)
            }
            startActivity(intent)
        }
        rvSinhVien.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh danh sách khi quay lại activity
        refreshStudentList()
    }
}
