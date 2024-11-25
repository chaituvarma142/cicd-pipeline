package com.example.game;

import java.util.Scanner;

public class Game2048 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameLogic game = new GameLogic();
        
        while (!game.isGameOver()) {
            printBoard(game.getBoard());
            System.out.print("Enter move (up, down, left, right): ");
            String move = scanner.nextLine();
            try {
                game.move(move);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move! Please try again.");
            }
        }

        System.out.println("Game Over!");
        printBoard(game.getBoard());
        scanner.close();
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print((cell == 0 ? "." : cell) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
