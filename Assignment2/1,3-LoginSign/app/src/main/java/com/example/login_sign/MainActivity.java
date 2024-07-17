package com.example.login_sign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgotPassword;
    private TextView textViewRegister;

    // ArrayList to store registered users
    public static ArrayList<User> registeredUsers = SignUpActivity.registeredUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewRegister = findViewById(R.id.textViewRegister);

        // Set click listener for login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Set click listener for "Forgot Password?" text view
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForgetPassword();
            }
        });

        // Set click listener for "Register" text view
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });
    }

    // Method to handle login process
    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if username and password are empty
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if user credentials are valid
        if (isValidCredentials(username, password)) {
            // Successful login
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
            // You can proceed to the next activity or perform any desired action here
            //TODO

        } else {
            // Invalid credentials
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to check if user credentials are valid
    private boolean isValidCredentials(String username, String password) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // User found with matching username and password
            }
        }
        return false; // No user found with matching username and password
    }

    // Method to navigate to ForgetPasswordActivity
    private void navigateToForgetPassword() {
        Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    // Method to navigate to SignUpActivity
    private void navigateToSignUp() {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
