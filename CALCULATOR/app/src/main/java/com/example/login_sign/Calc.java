package com.example.login_sign;

import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Calc extends AppCompatActivity {

    private static final String HISTORY_FILE = "calc_history.txt";
    private TextView resultTextView;
    private TextView historyTextView;
    private ScrollView historyScrollView;
    private StringBuilder currentInput = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        resultTextView = findViewById(R.id.result_tv);
        historyTextView = findViewById(R.id.history_tv);
        historyScrollView = findViewById(R.id.history_scroll_view);

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
                findViewById(R.id.button_ac),
                findViewById(R.id.button_additional) // Added additional button
        };

        for (MaterialButton button : buttons) {
            button.setOnClickListener(view -> handleButtonClick(button.getText().toString()));
        }

        loadHistory();
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
            case "RESET HISTORY":
                resetHistory();
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
            String resultStr = Double.toString(result);
            resultTextView.setText(resultStr);
            saveHistory(currentInput.toString() + " = " + resultStr);
            currentInput.setLength(0); // Clear input after evaluation
        } catch (Exception e) {
            resultTextView.setText("Error");
        }
    }

    private void saveHistory(String record) {
        try {
            FileOutputStream fos = openFileOutput(HISTORY_FILE, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            osw.write(record + "\n");
            osw.close();
            fos.close();
            loadHistory(); // Refresh the displayed history
        } catch (Exception e) {
            Log.e("Calc", "Error saving history", e);
        }
    }

    private void loadHistory() {
        try {
            FileInputStream fis = openFileInput(HISTORY_FILE);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder history = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                history.append(line).append("\n");
            }
            historyTextView.setText(history.toString());
            br.close();
            isr.close();
            fis.close();
            scrollToBottom(); // Scroll to the bottom after loading history
        } catch (Exception e) {
            Log.e("Calc", "Error loading history", e);
        }
    }

    private void resetHistory() {
        try {
            FileOutputStream fos = openFileOutput(HISTORY_FILE, MODE_PRIVATE); // Overwrite the file
            fos.write("".getBytes());
            fos.close();
            loadHistory(); // Refresh the displayed history
        } catch (Exception e) {
            Log.e("Calc", "Error resetting history", e);
        }
    }

    private void scrollToBottom() {
        historyScrollView.post(() -> historyScrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }
}
