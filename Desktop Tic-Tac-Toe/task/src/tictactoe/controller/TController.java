package tictactoe.controller;

import tictactoe.TicTacToe;
import tictactoe.model.TGame;

public class TController {

    TGame game;

    public String getStatus() {
        return game.getStatus();
    }

    public void startGame() { this.game.startGame();}

    public void nextMove(TicTacToe view) {
        if (game.getCurrentPlayer().equals("Robot")) {
            String letter = game.getCurrentMove();
            view.setButtonMove(game.computerMove(), letter);
            view.setStatusLabel();
            game.changeMove();
            nextMove(view);
        }
    }

    public void resetGame() {
        this.game = new TGame();
    }

    public void makeMove(String buttonKey, TicTacToe view) {
        String letter = game.getCurrentMove();
        this.game.makeMove(buttonKey);
        view.setButtonMove(buttonKey, letter);
        view.setStatusLabel();
    }

    public void setPlayers(String playerOne, String playerTwo) {
        game.setCurrentPlayer(playerOne);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
    }
}
