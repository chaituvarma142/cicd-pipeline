package com.example.game;

import java.util.Random;

public class GameLogic {
    private int[][] board;
    private Random random;

    public GameLogic() {
        this.random = new Random();
        resetGame();
    }

    public int[][] getBoard() {
        return board;
    }

    public void move(String direction) {
        // TODO: Implement move logic for left, right, up, down
        spawnRandomTile(); // Spawn a new tile after a valid move
    }

    public boolean isGameOver() {
        return !canMove();
    }

    public void resetGame() {
        board = new int[4][4];
        spawnRandomTile();
        spawnRandomTile();
    }

    private void spawnRandomTile() {
        int emptyCount = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) emptyCount++;
            }
        }
        if (emptyCount == 0) return;

        int position = random.nextInt(emptyCount);
        int value = random.nextInt(10) < 9 ? 2 : 4;

        emptyCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    if (emptyCount == position) {
                        board[i][j] = value;
                        return;
                    }
                    emptyCount++;
                }
            }
        }
    }

    private boolean canMove() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) return true;
                if (j < 3 && board[i][j] == board[i][j + 1]) return true;
                if (i < 3 && board[i][j] == board[i + 1][j]) return true;
            }
        }
        return false;
    }
}
