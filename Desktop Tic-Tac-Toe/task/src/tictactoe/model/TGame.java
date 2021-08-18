package tictactoe.model;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class TGame {
    String currentMove = "X";
    boolean isGameOver = false;
    Map<String, String> gameField = new LinkedHashMap<String, String>() {{
        put("ButtonA1", " ");
        put("ButtonA2", " ");
        put("ButtonA3", " ");
        put("ButtonB1", " ");
        put("ButtonB2", " ");
        put("ButtonB3", " ");
        put("ButtonC1", " ");
        put("ButtonC2", " ");
        put("ButtonC3", " ");
    }};

    public String getCurrentMove() {
        return currentMove;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isPlayerWin(Map<String, String> field, String letter) {
        if (gameField.get("ButtonA3").equals(letter) && gameField.get("ButtonB3").equals(letter)
                                && gameField.get("ButtonC3").equals(letter)) return true;
        if (gameField.get("ButtonA2").equals(letter) && gameField.get("ButtonB2").equals(letter)
                && gameField.get("ButtonC2").equals(letter)) return true;
        if (gameField.get("ButtonA1").equals(letter) && gameField.get("ButtonB1").equals(letter)
                && gameField.get("ButtonC1").equals(letter)) return true;
        if (gameField.get("ButtonA3").equals(letter) && gameField.get("ButtonA2").equals(letter)
                && gameField.get("ButtonA1").equals(letter)) return true;
        if (gameField.get("ButtonB3").equals(letter) && gameField.get("ButtonB2").equals(letter)
                && gameField.get("ButtonB1").equals(letter)) return true;
        if (gameField.get("ButtonC3").equals(letter) && gameField.get("ButtonC2").equals(letter)
                && gameField.get("ButtonC1").equals(letter)) return true;
        if (gameField.get("ButtonA3").equals(letter) && gameField.get("ButtonB2").equals(letter)
                && gameField.get("ButtonC1").equals(letter)) return true;
        if (gameField.get("ButtonA1").equals(letter) && gameField.get("ButtonB2").equals(letter)
                && gameField.get("ButtonC3").equals(letter)) return true;
        return false;
    }

    public int countLetter(Map<String, String> field, String letter) {
        return  Collections.frequency(field.values(), letter);
    }

    public boolean isDraw(Map<String, String> field) {
        return  Collections.frequency(field.values(), " ") == 0;
    }

    public void makeMove(String buttonKey) {
        gameField.put(buttonKey, this.currentMove);
    }

    public List<String> availableMoves(Map<String, String> field) {
        return field.values()
                .stream()
                .filter(s -> s.equals(" "))
                .collect(Collectors.toList());
    }

    public void changeMove() {
        this.currentMove = this.currentMove.equals("X") ? "O" : "X";
    }

    public String getStatus() {
        if (isPlayerWin(this.gameField, "X")) {
            isGameOver = true;
            return "X wins";
        }
        if (isPlayerWin(this.gameField, "O")) {
            isGameOver = true;
            return "O wins";
        }
        if (isDraw(this.gameField)) {
            isGameOver = true;
            return "Draw";
        }
        return "Game in progress";
    }



}
