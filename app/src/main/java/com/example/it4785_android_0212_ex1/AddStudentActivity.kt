package com.example.it4785_android_0212_ex1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    private lateinit var edtMSSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var edtSoDienThoai: EditText
    private lateinit var edtDiaChi: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        edtMSSV = findViewById(R.id.edtMSSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai)
        edtDiaChi = findViewById(R.id.edtDiaChi)
        btnSave = findViewById(R.id.btnSave)
    }

    private fun setupListeners() {
        btnSave.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val mssv = edtMSSV.text.toString().trim()
        val hoTen = edtHoTen.text.toString().trim()
        val soDienThoai = edtSoDienThoai.text.toString().trim()
        val diaChi = edtDiaChi.text.toString().trim()

        if (mssv.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val student = Student(mssv, hoTen, soDienThoai, diaChi)
        if (StudentManager.addStudent(student)) {
            Toast.makeText(this, "Đã thêm sinh viên thành công", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        } else {
            Toast.makeText(this, "MSSV đã tồn tại", Toast.LENGTH_SHORT).show()
        }
    }
}

