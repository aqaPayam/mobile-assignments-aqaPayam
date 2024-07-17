package com.example.login_sign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonResetPassword;

    // ArrayList to store registered users
    public static ArrayList<User> registeredUsers = SignUpActivity.registeredUsers;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_layout);

        // Initialize views
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);

        // Set click listener for reset password button
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    // Method to handle password reset process
    private void resetPassword() {
        String email = editTextEmail.getText().toString().trim();

        // Validate email format using regex
        if (!isValidEmail(email)) {
            editTextEmail.setError("Invalid email address");
            editTextEmail.requestFocus();
            return;
        }

        // Check if the email exists
        if (isEmailExists(email)) {
            // Perform password reset operation (you can implement this part)
            Toast.makeText(getApplicationContext(), "Password reset email sent", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity after sending the password reset email
        } else {
            Toast.makeText(getApplicationContext(), "Email does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to validate email format using regex
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    // Method to check if the email exists
    private boolean isEmailExists(String email) {
        for (User user : registeredUsers) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
