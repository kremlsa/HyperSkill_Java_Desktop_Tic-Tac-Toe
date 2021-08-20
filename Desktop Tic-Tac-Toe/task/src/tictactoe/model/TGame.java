package tictactoe.model;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class TGame {
    String currentMove = "X";
    boolean isGameOver = false;
    String gameStatus = "Game is not started";
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
        if (field.get("ButtonA3").equals(letter) && field.get("ButtonB3").equals(letter)
                                && field.get("ButtonC3").equals(letter)) return true;
        if (field.get("ButtonA2").equals(letter) && field.get("ButtonB2").equals(letter)
                && field.get("ButtonC2").equals(letter)) return true;
        if (field.get("ButtonA1").equals(letter) && field.get("ButtonB1").equals(letter)
                && field.get("ButtonC1").equals(letter)) return true;
        if (field.get("ButtonA3").equals(letter) && field.get("ButtonA2").equals(letter)
                && field.get("ButtonA1").equals(letter)) return true;
        if (field.get("ButtonB3").equals(letter) && field.get("ButtonB2").equals(letter)
                && field.get("ButtonB1").equals(letter)) return true;
        if (field.get("ButtonC3").equals(letter) && field.get("ButtonC2").equals(letter)
                && field.get("ButtonC1").equals(letter)) return true;
        if (field.get("ButtonA3").equals(letter) && field.get("ButtonB2").equals(letter)
                && field.get("ButtonC1").equals(letter)) return true;
        if (field.get("ButtonA1").equals(letter) && field.get("ButtonB2").equals(letter)
                && field.get("ButtonC3").equals(letter)) return true;
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
        List<String> moves = new ArrayList<>();
        for (Map.Entry<String, String> entry : field.entrySet())
        {
            if(entry.getValue().equals(" ")) {
                moves.add(entry.getKey());
            }
        }
        return moves;
    }

    public void startGame() { this.gameStatus = "Game in progress";}

    public void changeMove() {
        this.currentMove = this.currentMove.equals("X") ? "O" : "X";
    }

    public String getStatus() {
        if (isPlayerWin(this.gameField, "X")) {
            isGameOver = true;
            this.gameStatus = "X wins";
            return "X wins";
        }
        if (isPlayerWin(this.gameField, "O")) {
            isGameOver = true;
            this.gameStatus = "O wins";
            return "O wins";
        }
        if (isDraw(this.gameField)) {
            isGameOver = true;
            this.gameStatus = "Draw";
            return "Draw";
        }
        if (gameStatus.equals("Game is not started")) {
            return "Game is not started";
        }
        return "Game in progress";
    }

    public Map<String, String> deepCopy(Map<String, String> original) {
        Map<String, String> copy = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> entry : original.entrySet())
        {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    public String computerMove() {
        String move = calcMove();
        gameField.put(move, this.currentMove);
        return move;
    }

    public String calcMove() {
        Random random = new Random();
        String letterInv;
        //Check if Computer win next move
        Map<String, String> cloneField;
        List<String> moves = availableMoves(gameField);
        //Check if Computer win next move
        for (String move : moves) {
            cloneField = deepCopy(gameField);
            cloneField.put(move, currentMove);
            if (isPlayerWin(cloneField, currentMove)) {
                return move;
            }
        }
        letterInv = currentMove.equals("X") ? "O" : "X";
        for (String move : moves) {
            cloneField = deepCopy(gameField);
            cloneField.put(move, letterInv);
            if (isPlayerWin(cloneField, letterInv)) {
                return move;
            }
        }
        return moves.get(random.nextInt(moves.size()));
    }


}
