package tictactoe.controller;

import tictactoe.model.TGame;

public class TController {

    TGame game;

    public String getStatus() {
        return game.getStatus();
//        if (game.isGameOver()) {
//            return "GameOver";
//        } else {
//            return this.game.getStatus();
//        }
    }

    public void startGame() { this.game.startGame();}

    public void resetGame() {
        this.game = new TGame();
    }

    public String makeMove(String buttonKey) {
        String move = game.getCurrentMove();
        this.game.makeMove(buttonKey);
        return game.getCurrentMove();
    }

    public void changeMove() {
        game.changeMove();
    }

    public String robotMove() {
        return game.computerMove();
    }

    public String getLetter() {
        return game.getCurrentMove();
    }

}
