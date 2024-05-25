package com.example.xo;

public class Prompt {

    // Generate a prompt for an n x n table
    public static String generatePrompt(String[][] field, String turn) {
        // Convert the field array to a string representation
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                boardString.append(field[i][j].isEmpty() ? "_" : field[i][j]);
                if (j < field[0].length - 1) boardString.append(" ");
            }
            if (i < field.length - 1) boardString.append("\n");
        }

        // Create the prompt
        return String.format(
                "Here is the current state of the board:\n%s\nIt is %s's turn. What is the best next move?",
                boardString.toString(), turn
        );
    }
}
