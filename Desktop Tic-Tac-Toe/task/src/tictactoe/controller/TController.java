package tictactoe.controller;

import tictactoe.model.TGame;

public class TController {

    TGame game;

    public String getStatus() {
        if (game.isGameOver()) {
            return "GameOver";
        } else {
            return this.game.getStatus();
        }
    }

    public void resetGame() {
        this.game = new TGame();
    }

    public String makeMove(String buttonKey) {
        String move = game.getCurrentMove();
        this.game.makeMove(buttonKey);
        game.changeMove();
        return move;
    }

    public String robotMove() {
        return "ButtonA1";
    }

    public String getLetter() {
        return game.getCurrentMove();
    }

}
