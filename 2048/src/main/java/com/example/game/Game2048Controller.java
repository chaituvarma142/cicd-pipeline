package com.example.game;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class Game2048Controller {

    private GameLogic game;

    public Game2048Controller() {
        this.game = new GameLogic();
    }

    @GetMapping("/board")
    public int[][] getBoard() {
        return game.getBoard();
    }

    @PostMapping("/move")
    public int[][] makeMove(@RequestParam String direction) {
        game.move(direction);
        return game.getBoard();
    }

    @GetMapping("/gameover")
    public boolean isGameOver() {
        return game.isGameOver();
    }

    @PostMapping("/newgame")
    public int[][] newGame() {
        game.resetGame();
        return game.getBoard();
    }
}
