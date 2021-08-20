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

    public void makeMove(String buttonKey) {
        this.game.makeMove(buttonKey);
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

    public void setPlayers(String playerOne, String playerTwo) {
        game.setCurrentPlayer(playerOne);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
    }

    public String getPlayer() {
        return this.game.getCurrentPlayer();
    }

}
