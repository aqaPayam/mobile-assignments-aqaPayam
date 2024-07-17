package com.example.xo;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class GAME2 extends AppCompatActivity implements View.OnClickListener {

    private final Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private boolean gameEnded;
    private boolean lastWinnerPlayer1;
    private boolean lastWinnerPlayer2;
    private final Stack<Button> moveStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        // Find the Save and Load buttons and set their onClick listeners
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGame();
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGame();
            }
        });

        // Find and set onClickListener for each button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + ((i * 3) + (j + 1));
                @SuppressLint("DiscouragedApi") int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.resetButton);
        Button buttonUndo = findViewById(R.id.undoButton);
        Button buttonNewGame = findViewById(R.id.newGameButton);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        buttonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoMove();
            }
        });
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (gameEnded) return;

        Button clickedButton = (Button) v;
        if (!clickedButton.getText().toString().isEmpty()) {
            // This position is already filled
            return;
        }

        if (player1Turn) {
            clickedButton.setText("O");
        } else {
            clickedButton.setText("X");
        }

        roundCount++;
        moveStack.push(clickedButton); // Push the clicked button onto the stack

        if (checkForWin()) {
            if (lastWinnerPlayer1) {
                player1Wins();
            } else if (lastWinnerPlayer2) {
                player2Wins();
            }
            player1Turn = !player1Turn;
        } else if (roundCount == 9) {
            draw();
            player1Turn = !player1Turn;
        } else {
            player1Turn = !player1Turn;
        }
    }

    private void undoMove() {
        if (!moveStack.isEmpty()) {
            Button lastMoveButton = moveStack.pop();
            String lastMove = lastMoveButton.getText().toString();
            lastMoveButton.setText("");
            player1Turn = !player1Turn;
            roundCount--;

            // Check if the game ended with this move
            if (gameEnded) {
                if (lastWinnerPlayer1 && lastMove.equals("O")) {
                    // Decrement Player 1's score if Player 1 won the last game
                    player1Points--;
                } else if (lastWinnerPlayer2 && lastMove.equals("X")) {
                    // Decrement Player 2's score if Player 2 won the last game
                    player2Points--;
                }
                gameEnded = false; // Allow game to continue after undo
            }
        } else {
            Toast.makeText(this, "No moves to undo!", Toast.LENGTH_SHORT).show();
        }
        updatePointsText();
    }


    private boolean checkForWin() {
        String[][] field = new String[3][3];

        // Copy button texts to the field array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].isEmpty()) {
                lastWinnerPlayer1 = field[i][0].equals("O");
                lastWinnerPlayer2 = field[i][0].equals("X");
                return true; // Row win
            }
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].isEmpty()) {
                lastWinnerPlayer1 = field[0][i].equals("O");
                lastWinnerPlayer2 = field[0][i].equals("X");
                return true; // Column win
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].isEmpty()) {
            lastWinnerPlayer1 = field[0][0].equals("O");
            lastWinnerPlayer2 = field[0][0].equals("X");
            return true; // Diagonal win
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].isEmpty()) {
            lastWinnerPlayer1 = field[0][2].equals("O");
            lastWinnerPlayer2 = field[0][2].equals("X");
            return true; // Diagonal win
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        gameEnded = true;
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        gameEnded = true;
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        gameEnded = true;
    }

    @SuppressLint("SetTextI18n")
    private void updatePointsText() {
        TextView textViewPlayer1 = findViewById(R.id.textViewPlayer1);
        TextView textViewPlayer2 = findViewById(R.id.textViewPlayer2);
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        lastWinnerPlayer1 = false;
        lastWinnerPlayer2 = false;
        roundCount = 0;
        player1Turn = true;
        gameEnded = false;
        moveStack.clear(); // Clear the move stack
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    private void newGame() {
        if (gameEnded) {
            resetBoard();
        } else {
            Toast.makeText(this, "Finish the current game before starting a new one!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveGame() {
        SharedPreferences sharedPreferences = getSharedPreferences("TicTacToeGame2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save each button state
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                editor.putString("button_" + i + "_" + j, buttons[i][j].getText().toString());
            }
        }

        // Save the game state
        editor.putBoolean("player1Turn", player1Turn);
        editor.putInt("roundCount", roundCount);
        editor.putInt("player1Points", player1Points);
        editor.putInt("player2Points", player2Points);
        editor.putBoolean("gameEnded", gameEnded);
        editor.putBoolean("lastWinnerPlayer1", lastWinnerPlayer1);
        editor.putBoolean("lastWinnerPlayer2", lastWinnerPlayer2);

        // Save the move stack size
        editor.putInt("moveStackSize", moveStack.size());

        // Save each move in the stack
        for (int i = 0; i < moveStack.size(); i++) {
            Button button = moveStack.get(i);
            int id = button.getId();
            editor.putInt("moveStack_" + i, id);
        }

        editor.apply();
        Toast.makeText(this, "Game Saved!", Toast.LENGTH_SHORT).show();
    }

    private void loadGame() {
        SharedPreferences sharedPreferences = getSharedPreferences("TicTacToeGame2", MODE_PRIVATE);

        // Check if the saved game exists
        boolean hasSavedGame = sharedPreferences.contains("player1Turn");

        if (hasSavedGame) {
            // Load each button state
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    String buttonText = sharedPreferences.getString("button_" + i + "_" + j, "");
                    buttons[i][j].setText(buttonText);
                }
            }

            // Load the game state
            player1Turn = sharedPreferences.getBoolean("player1Turn", true);
            roundCount = sharedPreferences.getInt("roundCount", 0);
            player1Points = sharedPreferences.getInt("player1Points", 0);
            player2Points = sharedPreferences.getInt("player2Points", 0);
            gameEnded = sharedPreferences.getBoolean("gameEnded", false);
            lastWinnerPlayer1 = sharedPreferences.getBoolean("lastWinnerPlayer1", false);
            lastWinnerPlayer2 = sharedPreferences.getBoolean("lastWinnerPlayer2", false);

            // Load the move stack
            int moveStackSize = sharedPreferences.getInt("moveStackSize", 0);
            moveStack.clear();
            for (int i = 0; i < moveStackSize; i++) {
                int buttonId = sharedPreferences.getInt("moveStack_" + i, -1);
                if (buttonId != -1) {
                    Button button = findViewById(buttonId);
                    moveStack.push(button);
                }
            }

            updatePointsText();
            Toast.makeText(this, "Game Loaded!", Toast.LENGTH_SHORT).show();
        } else {
            // No saved game found, initialize to default values
            resetBoard();
            Toast.makeText(this, "No saved game found. Starting new game!", Toast.LENGTH_SHORT).show();
        }
    }

}
