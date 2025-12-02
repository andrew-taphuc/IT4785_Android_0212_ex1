package com.example.it4785_android_0212_ex1

object StudentManager {
    private val students = mutableListOf<Student>()

    fun getAllStudents(): List<Student> = students.toList()

    fun addStudent(student: Student): Boolean {
        // Kiểm tra MSSV đã tồn tại chưa
        if (students.any { it.mssv == student.mssv }) {
            return false
        }
        students.add(student)
        return true
    }

    fun updateStudent(position: Int, student: Student): Boolean {
        if (position < 0 || position >= students.size) {
            return false
        }
        // Kiểm tra MSSV có bị trùng với sinh viên khác không
        val currentStudent = students[position]
        if (student.mssv != currentStudent.mssv && students.any { it.mssv == student.mssv }) {
            return false
        }
        students[position] = student
        return true
    }

    fun getStudent(position: Int): Student? {
        if (position < 0 || position >= students.size) {
            return null
        }
        return students[position]
    }

    fun getStudentPosition(mssv: String): Int {
        return students.indexOfFirst { it.mssv == mssv }
    }
}

