package com.example.it4785_android_0212_ex1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {
    private lateinit var edtMSSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var edtSoDienThoai: EditText
    private lateinit var edtDiaChi: EditText
    private lateinit var btnUpdate: Button

    private var studentPosition = -1
    private var currentStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        val mssv = intent.getStringExtra("MSSV")
        if (mssv == null) {
            Toast.makeText(this, "Không tìm thấy thông tin sinh viên", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        studentPosition = StudentManager.getStudentPosition(mssv)
        if (studentPosition == -1) {
            Toast.makeText(this, "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        currentStudent = StudentManager.getStudent(studentPosition)
        if (currentStudent == null) {
            Toast.makeText(this, "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initViews()
        loadStudentData()
        setupListeners()
    }

    private fun initViews() {
        edtMSSV = findViewById(R.id.edtMSSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai)
        edtDiaChi = findViewById(R.id.edtDiaChi)
        btnUpdate = findViewById(R.id.btnUpdate)
    }

    private fun loadStudentData() {
        currentStudent?.let { student ->
            edtMSSV.setText(student.mssv)
            edtHoTen.setText(student.hoTen)
            edtSoDienThoai.setText(student.soDienThoai)
            edtDiaChi.setText(student.diaChi)
        }
    }

    private fun setupListeners() {
        btnUpdate.setOnClickListener {
            updateStudent()
        }
    }

    private fun updateStudent() {
        val mssv = edtMSSV.text.toString().trim()
        val hoTen = edtHoTen.text.toString().trim()
        val soDienThoai = edtSoDienThoai.text.toString().trim()
        val diaChi = edtDiaChi.text.toString().trim()

        if (mssv.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedStudent = Student(mssv, hoTen, soDienThoai, diaChi)
        if (StudentManager.updateStudent(studentPosition, updatedStudent)) {
            Toast.makeText(this, "Đã cập nhật thông tin sinh viên", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "MSSV đã tồn tại hoặc có lỗi xảy ra", Toast.LENGTH_SHORT).show()
        }
    }
}

