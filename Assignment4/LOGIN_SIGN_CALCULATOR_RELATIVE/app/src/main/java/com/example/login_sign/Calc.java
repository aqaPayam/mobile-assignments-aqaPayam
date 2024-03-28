package com.example.login_sign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calc extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder currentInput = new StringBuilder();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        resultTextView = findViewById(R.id.result_tv);

        MaterialButton[] buttons = {
                findViewById(R.id.button_0),
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
        };

        for (MaterialButton button : buttons) {
            button.setOnClickListener(view -> handleButtonClick(button.getText().toString()));
        }
    }

    private void handleButtonClick(String input) {
        switch (input) {
            case "=":
                evaluateExpression();
                break;
            case "C":
                clearLastInput();
                break;
            case "AC":
                clearAll();
                break;
            default:
                appendInput(input);
                break;
        }
    }

    private void appendInput(String input) {
        currentInput.append(input);
        resultTextView.setText(currentInput.toString());
    }

    private void clearLastInput() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            resultTextView.setText(currentInput.toString());
        }
    }

    private void clearAll() {
        currentInput.setLength(0);
        resultTextView.setText("0");
    }

    private void evaluateExpression() {
        try {
            Expression expression = new ExpressionBuilder(currentInput.toString()).build();
            double result = expression.evaluate();
            resultTextView.setText(Double.toString(result));
        } catch (Exception e) {
            resultTextView.setText("Error");
        }
    }
}
