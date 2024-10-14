package com.example.calculator
// Updated
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentNumber = ""
    private var previousNumber = ""
    private var operator = ""
    private var isNewOp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.textViewDisplay)

        setNumberListeners()
        setOperatorListeners()

        // Sự kiện cho nút CE và BS
        findViewById<Button>(R.id.buttonCE).setOnClickListener {
            onClearEntryClick()
        }
        findViewById<Button>(R.id.buttonBS).setOnClickListener {
            onBackspaceClick()
        }

        // Sự kiện cho nút dấu chấm
        findViewById<Button>(R.id.buttonDot).setOnClickListener {
            onNumberClick(".")
        }
    }

    private fun setNumberListeners() {
        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                onNumberClick((it as Button).text.toString())
            }
        }
    }

    private fun onNumberClick(number: String) {
        if (isNewOp) {
            display.text = ""
            isNewOp = false
        }

        // Kiểm tra nếu là dấu chấm, chỉ cho phép một dấu chấm trong mỗi số
        if (number == "." && currentNumber.contains(".")) {
            return // Nếu đã có dấu chấm, không thêm nữa
        }

        currentNumber += number
        display.text = currentNumber
    }

    private fun setOperatorListeners() {
        val operators = mapOf(
            R.id.buttonAdd to "+",
            R.id.buttonSubtract to "-",
            R.id.buttonMultiply to "*",
            R.id.buttonDivide to "/"
        )

        operators.forEach { (id, op) ->
            findViewById<Button>(id).setOnClickListener {
                onOperatorClick(op)
            }
        }

        findViewById<Button>(R.id.buttonEquals).setOnClickListener {
            onEqualClick()
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            onClearClick()
        }
    }

    private fun onOperatorClick(op: String) {
        if (currentNumber.isNotEmpty()) {
            previousNumber = currentNumber
            currentNumber = ""
            operator = op
            isNewOp = true
        }
    }

    private fun onEqualClick() {
        if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty()) {
            // Chuyển đổi previousNumber và currentNumber từ chuỗi sang số
            val prevNum = previousNumber.toDoubleOrNull()
            val currNum = currentNumber.toDoubleOrNull()

            if (prevNum == null || currNum == null) {
                display.text = "Error"
                return
            }

            // Thực hiện phép tính và lưu kết quả
            val result = when (operator) {
                "+" -> prevNum + currNum
                "-" -> prevNum - currNum
                "*" -> prevNum * currNum
                "/" -> if (currNum != 0.0) prevNum / currNum else Double.NaN
                else -> 0.0
            }

            // Hiển thị kết quả và cập nhật currentNumber
            display.text = if (result.isNaN()) "Error" else result.toString()
            currentNumber = result.toString()
            previousNumber = ""
            isNewOp = true
        }
    }

    private fun onClearClick() {
        currentNumber = ""
        previousNumber = ""
        operator = ""
        display.text = "0"
        isNewOp = true
    }

    private fun onClearEntryClick() {
        // Xóa số hiện tại (CE)
        currentNumber = ""
        display.text = "0"
    }

    private fun onBackspaceClick() {
        // Xóa từng chữ số cuối cùng của số hiện tại (BS)
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            display.text = if (currentNumber.isEmpty()) "0" else currentNumber
        }
    }
}
