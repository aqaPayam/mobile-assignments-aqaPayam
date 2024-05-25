package com.example.xo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class MenuActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String UUID_KEY = "uuid";

    private RadioGroup radioGroupOpponent, radioGroupSymbol;
    private RadioButton radioButtonHuman, radioButtonCPU, radioButtonX, radioButtonO;
    private Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuactivity);

        // Retrieve or generate UUID
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String uuid = preferences.getString(UUID_KEY, null);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(UUID_KEY, uuid);
            editor.apply();
        }
        Log.d("MenuActivity", "User UUID: " + uuid);

        radioGroupOpponent = findViewById(R.id.radioGroupOpponent);
        radioGroupSymbol = findViewById(R.id.radioGroupSymbol);
        radioButtonHuman = findViewById(R.id.radioButtonHuman);
        radioButtonCPU = findViewById(R.id.radioButtonCPU);
        radioButtonX = findViewById(R.id.radioButtonX);
        radioButtonO = findViewById(R.id.radioButtonO);
        buttonStartGame = findViewById(R.id.buttonStartGame);

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonHuman.isChecked() && radioButtonX.isChecked()) {
                    startGameHumanX();
                } else if (radioButtonHuman.isChecked() && radioButtonO.isChecked()) {
                    startGameHumanO();
                } else if (radioButtonCPU.isChecked() && radioButtonX.isChecked()) {
                    startGameCPUX();
                } else if (radioButtonCPU.isChecked() && radioButtonO.isChecked()) {
                    startGameCPUO();
                }
            }
        });
    }

    private void startGameHumanX() {
        Intent intent = new Intent(MenuActivity.this, GAME.class);
        startActivity(intent);
    }

    private void startGameHumanO() {
        Intent intent = new Intent(MenuActivity.this, GAME2.class);
        startActivity(intent);
    }

    private void startGameCPUX() {
        Intent intent = new Intent(MenuActivity.this, GAMECPU.class);
        startActivity(intent);
    }

    private void startGameCPUO() {
        Intent intent = new Intent(MenuActivity.this, GAMECPU2.class);
        startActivity(intent);
    }
}
