package com.example.login_sign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignUp;

    // ArrayList to store registered users
    public static ArrayList<User> registeredUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        // Set focus on the first input field
        editTextUsername.requestFocus();

        // Set click listener for sign-up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    // Method to handle sign-up process
    private void signUp() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Validate email format using regex
        if (!isValidEmail(email)) {
            editTextEmail.setError("Invalid email address");
            editTextEmail.requestFocus();
            return;
        }

        // Validate password format using regex
        if (!isValidPassword(password)) {
            editTextPassword.setError("Password must contain at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        // Confirm password
        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Passwords do not match");
            editTextConfirmPassword.requestFocus();
            return;
        }

        // Check if username is unique
        if (isUsernameUnique(username)) {
            // Check if email is unique
            if (isEmailUnique(email)) {
                // Create a new User object and add it to the list of registered users
                User newUser = new User(username, email, password);
                registeredUsers.add(newUser);
                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                // You can save the registered users list to SharedPreferences or a database here
                // Example: saveRegisteredUsers();
                clearFields();
            } else {
                Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to validate email format using regex
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    // Method to validate password format using regex
    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    // Method to check if the username is unique
    private boolean isUsernameUnique(String username) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the email is unique
    private boolean isEmailUnique(String email) {
        for (User user : registeredUsers) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    // Method to clear input fields
    private void clearFields() {
        editTextUsername.getText().clear();
        editTextEmail.getText().clear();
        editTextPassword.getText().clear();
        editTextConfirmPassword.getText().clear();
        editTextUsername.requestFocus();
    }
}
