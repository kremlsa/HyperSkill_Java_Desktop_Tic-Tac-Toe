package tictactoe;

import tictactoe.controller.TController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public class TicTacToe extends JFrame {

    Map<String, JButton> buttonsMap = new LinkedHashMap<String, JButton>() {{
        put("ButtonA3", new JButton());
        put("ButtonB3", new JButton());
        put("ButtonC3", new JButton());
        put("ButtonA2", new JButton());
        put("ButtonB2", new JButton());
        put("ButtonC2", new JButton());
        put("ButtonA1", new JButton());
        put("ButtonB1", new JButton());
        put("ButtonC1", new JButton());
    }};
    JPanel fieldGrid = new JPanel();
    JPanel statusPanel = new JPanel();
    JPanel toolPanel = new JPanel();
    JLabel statusLabel = new JLabel();
    JButton resetButton = new JButton();
    JButton playerOneButton = new JButton();
    JButton playerTwoButton = new JButton();
    ButtonListener buttonListener = new ButtonListener();
    PlayerButtonListener playerButtonListener = new PlayerButtonListener();
    ResetListener resetListener = new ResetListener();
    TController tc = new TController();
    String currentMove;

    public TicTacToe() {
        super("Tic Tac Toe");
        setSize(450, 450);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        GridLayout layoutField = new GridLayout(3, 3, 0, 0);
        fieldGrid.setLayout(layoutField);
        initGameField();
        getContentPane().add(toolPanel);
        getContentPane().add(fieldGrid);
        getContentPane().add(statusPanel);
        setVisible(true);
    }

    public void resetGame() {
        for (Map.Entry<String, JButton> entry : buttonsMap.entrySet()) {
            entry.getValue().setText(" ");
        }
        tc.resetGame();
        playerOneButton.setText("Human");
        playerTwoButton.setText("Human");
        playerOneButton.setEnabled(true);
        playerTwoButton.setEnabled(true);
        resetButton.setText("Start");
        statusLabel.setText("Game is not started");
        currentMove = playerOneButton.getText();
    }

    public void startGame() {
        resetButton.setText("Reset");
        playerOneButton.setEnabled(false);
        playerTwoButton.setEnabled(false);
        currentMove = playerOneButton.getText();
        nextMove();
    }

    public void initGameField() {
        for (Map.Entry<String, JButton> entry : buttonsMap.entrySet()) {
            entry.getValue().setName(entry.getKey());
            entry.getValue().setText(" ");
            entry.getValue().setFont(new Font("Arial", Font.BOLD, 40));
            entry.getValue().setFocusPainted(false);
            entry.getValue().addActionListener(buttonListener);
            fieldGrid.add(entry.getValue());
        }
        toolPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        toolPanel.setLayout(new GridLayout(1, 3, 0, 0));
        statusPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        statusLabel.setText("Game is not started");
        statusLabel.setName("LabelStatus");
        resetButton.setText("Start");
        resetButton.setName("ButtonStartReset");
        resetButton.addActionListener(resetListener);
        statusPanel.setLayout(new BorderLayout());
        statusPanel.add(statusLabel, BorderLayout.WEST);
        playerOneButton.setName("ButtonPlayer1");
        playerTwoButton.setName("ButtonPlayer2");
        playerOneButton.setText("Human");
        playerTwoButton.setText("Human");
        playerOneButton.addActionListener(playerButtonListener);
        playerTwoButton.addActionListener(playerButtonListener);
        toolPanel.add(playerOneButton);
        toolPanel.add(resetButton);
        toolPanel.add(playerTwoButton);
        resetGame();
    }

    public void nextMove() {
        if (currentMove.equals("Robot")) {
            buttonsMap.get(tc.robotMove()).setText(tc.getLetter());
            changeMove();
        }
    }

    public void changeMove() {
        currentMove = currentMove.equals(playerOneButton.getText()) ?
                playerTwoButton.getText() : playerOneButton.getText();
        setStatusLabel();
    }



    public void setButtonText(String buttonKey) {
        if (!tc.getStatus().equals("GameOver") && currentMove.equals("Human")) {
            if (buttonsMap.get(buttonKey).getText().equals(" ")) {
                buttonsMap.get(buttonKey).setText(tc.makeMove(buttonKey));
            }
        }
        setStatusLabel();
        changeMove();
        nextMove();
    }

    public void setStatusLabel() {
        String status = tc.getStatus();
        if (!status.equals("GameOver")) {
            statusLabel.setText(status);
        }
    }

    public void changePlayer(String player) {
        if (player.equals("ButtonPlayer1")) {
            playerOneButton.setText(
                    playerOneButton.getText().equals("Human") ? "Robot" : "Human");
        }
        if (player.equals("ButtonPlayer2")) {
            playerTwoButton.setText(
                    playerTwoButton.getText().equals("Human") ? "Robot" : "Human");
        }
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fieldKey = ((JButton) e.getSource()).getName();
            setButtonText(fieldKey);
        }
    }

    class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String status = ((JButton) e.getSource()).getText();
            if (status.equals("Start")) {
                startGame();
            } else {
                resetGame();
            }
        }
    }

    class PlayerButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            changePlayer(((JButton) e.getSource()).getName());
        }
    }
}
