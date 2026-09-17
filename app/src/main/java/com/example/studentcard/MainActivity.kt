package com.example.studentcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentcard.databinding.ActivityMainBinding
import com.example.studentcard.model.Student
import com.example.studentcard.utils.toAcademicRanking
import com.example.studentcard.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415053122313", name = "Thai Truong Giang", className = "DD2026", email = "anv@ute.udn.vn", gpa = 3.8
    )

    // Chỉ sử dụng MỘT hàm onCreate duy nhất (Gộp Bước 4 và Bước 5 vào đây)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gán dữ liệu ban đầu lên các Views (Từ Bước 4)
        bindStudentData(currentStudent)

        // ── Xử lý sự kiện khi người dùng bấm nút Cập Nhật (Từ Bước 5) ────────
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                // Báo lỗi nếu nhập sai định dạng hoặc ngoài khoảng 0.0 - 4.0
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            // Cập nhật sinh viên bằng hàm copy()
            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent) // Vẽ lại dữ liệu mới lên Views
            toast("Cập nhật điểm thành công!")
        }
    }

    // Hàm phụ trợ (Từ Bước 4)
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}