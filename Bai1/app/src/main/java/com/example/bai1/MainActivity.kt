package com.example.bai1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bai1.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtMSSV = findViewById<EditText>(R.id.edtMSSV)
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val spinnerWard = findViewById<Spinner>(R.id.spinnerWard)
        val spinnerDistrict = findViewById<Spinner>(R.id.spinnerDistrict)
        val spinnerCity = findViewById<Spinner>(R.id.spinnerCity)
        val checkBoxSports = findViewById<CheckBox>(R.id.checkBoxSports)
        val checkBoxMovies = findViewById<CheckBox>(R.id.checkBoxMovies)
        val checkBoxMusic = findViewById<CheckBox>(R.id.checkBoxMusic)
        val checkBoxTerms = findViewById<CheckBox>(R.id.checkBoxTerms)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val mssv = edtMSSV.text.toString()
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val phone = edtPhone.text.toString()
            val genderSelected = radioGroupGender.checkedRadioButtonId != -1
            val termsAccepted = checkBoxTerms.isChecked

            if (mssv.isBlank() || name.isBlank() || email.isBlank() || phone.isBlank() || !genderSelected || !termsAccepted) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin và chấp nhận điều khoản", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Lấy thông tin từ Spinner, CheckBox và CalendarView nếu cần thiết
            val selectedGender = findViewById<RadioButton>(radioGroupGender.checkedRadioButtonId).text
            val selectedWard = spinnerWard.selectedItem.toString()
            val selectedDistrict = spinnerDistrict.selectedItem.toString()
            val selectedCity = spinnerCity.selectedItem.toString()
            val hobbies = mutableListOf<String>()

            if (checkBoxSports.isChecked) hobbies.add("Thể thao")
            if (checkBoxMovies.isChecked) hobbies.add("Điện ảnh")
            if (checkBoxMusic.isChecked) hobbies.add("Âm nhạc")

            // Hiển thị thông tin đã nhập (hoặc xử lý tiếp)
            Toast.makeText(this, "Thông tin đã được nhập thành công!", Toast.LENGTH_SHORT).show()
        }
    }
}
