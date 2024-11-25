package com.example.game;

import java.util.Random;

public class GameLogic {
    private int[][] board;
    private Random random;

    public GameLogic() {
        board = new int[4][4];
        random = new Random();
        spawnRandomTile();
        spawnRandomTile();
    }

    public int[][] getBoard() {
        return board;
    }

    public void moveLeft() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            int[] row = board[i];
            int[] newRow = new int[4];
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (row[j] != 0) {
                    newRow[index++] = row[j];
                }
            }
            for (int j = 0; j < index - 1; j++) {
                if (newRow[j] == newRow[j + 1]) {
                    newRow[j] *= 2;
                    newRow[j + 1] = 0;
                }
            }
            int[] compressedRow = new int[4];
            index = 0;
            for (int j = 0; j < 4; j++) {
                if (newRow[j] != 0) {
                    compressedRow[index++] = newRow[j];
                }
            }
            moved |= !java.util.Arrays.equals(row, compressedRow);
            board[i] = compressedRow;
        }
        if (moved) spawnRandomTile();
    }

    public void rotateClockwise() {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[j][3 - i] = board[i][j];
            }
        }
        board = newBoard;
    }

    public void move(String direction) {
        switch (direction.toLowerCase()) {
            case "up":
                rotateClockwise();
                rotateClockwise();
                rotateClockwise();
                moveLeft();
                rotateClockwise();
                break;
            case "down":
                rotateClockwise();
                moveLeft();
                rotateClockwise();
                rotateClockwise();
                rotateClockwise();
                break;
            case "right":
                rotateClockwise();
                rotateClockwise();
                moveLeft();
                rotateClockwise();
                rotateClockwise();
                break;
            case "left":
                moveLeft();
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    private void spawnRandomTile() {
        int row, col;
        do {
            row = random.nextInt(4);
            col = random.nextInt(4);
        } while (board[row][col] != 0);
        board[row][col] = random.nextInt(10) == 0 ? 4 : 2;
    }

    public boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) return false;
                if (i < 3 && board[i][j] == board[i + 1][j]) return false;
                if (j < 3 && board[i][j] == board[i][j + 1]) return false;
            }
        }
        return true;
    }
}
