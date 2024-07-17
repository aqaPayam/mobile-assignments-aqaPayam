package com.example.calc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.result_tv)

        val buttons = arrayOf(
            findViewById<MaterialButton>(R.id.button_0),
            findViewById(R.id.button_1),
            findViewById(R.id.button_2),
            findViewById(R.id.button_3),
            findViewById(R.id.button_4),
            findViewById(R.id.button_5),
            findViewById(R.id.button_6),
            findViewById(R.id.button_7),
            findViewById(R.id.button_8),
            findViewById(R.id.button_9),
            findViewById(R.id.button_dot),
            findViewById(R.id.button_plus),
            findViewById(R.id.button_minus),
            findViewById(R.id.button_multiply),
            findViewById(R.id.button_divide),
            findViewById(R.id.button_open_bracket),
            findViewById(R.id.button_close_bracket),
            findViewById(R.id.button_equals),
            findViewById(R.id.button_c),
            findViewById(R.id.button_ac)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                handleButtonClick(button.text.toString())
            }
        }
    }

    private fun handleButtonClick(input: String) {
        when (input) {
            "=" -> evaluateExpression()
            "C" -> clearLastInput()
            "AC" -> clearAll()
            else -> appendInput(input)
        }
    }

    private fun appendInput(input: String) {
        currentInput.append(input)
        resultTextView.text = currentInput.toString()
    }

    private fun clearLastInput() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            resultTextView.text = currentInput.toString()
        }
    }

    private fun clearAll() {
        currentInput.clear()
        resultTextView.text = "0"
    }

    private fun evaluateExpression() {
        try {
            val expression = ExpressionBuilder(currentInput.toString()).build()
            val result = expression.evaluate()
            resultTextView.text = result.toString()
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }
}
