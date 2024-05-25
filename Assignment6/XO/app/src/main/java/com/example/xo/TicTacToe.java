package com.example.xo;

public class TicTacToe {

    // Assuming field is a 2D array of Strings representing the board state
    public int[] move(String[][] field) {

        return findBestMove(field);

    }

    private int[] findBestMove(String[][] board) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    board[i][j] = "O";
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = "";

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(String[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

        if (score == 10)
            return score - depth;

        if (score == -10)
            return score + depth;

        if (!isMovesLeft(board))
            return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals("")) {
                        board[i][j] = "O";
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = "";
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals("")) {
                        board[i][j] = "X";
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = "";
                    }
                }
            }
            return best;
        }
    }

    private boolean isMovesLeft(String[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].equals(""))
                    return true;
        return false;
    }

    private int evaluate(String[][] board) {
        // Checking rows for victory
        for (int row = 0; row < 3; row++) {
            if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
                if (board[row][0].equals("O"))
                    return 10;
                else if (board[row][0].equals("X"))
                    return -10;
            }
        }

        // Checking columns for victory
        for (int col = 0; col < 3; col++) {
            if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
                if (board[0][col].equals("O"))
                    return 10;
                else if (board[0][col].equals("X"))
                    return -10;
            }
        }

        // Checking diagonals for victory
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            if (board[0][0].equals("O"))
                return 10;
            else if (board[0][0].equals("X"))
                return -10;
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            if (board[0][2].equals("O"))
                return 10;
            else if (board[0][2].equals("X"))
                return -10;
        }

        return 0;
    }
}


