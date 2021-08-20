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
    JMenuBar menuPanel = new JMenuBar();
    JLabel statusLabel = new JLabel();
    JButton resetButton = new JButton();
    JButton playerOneButton = new JButton();
    JButton playerTwoButton = new JButton();
    ButtonListener buttonListener = new ButtonListener();
    PlayerButtonListener playerButtonListener = new PlayerButtonListener();
    ResetListener resetListener = new ResetListener();
    MenuListener menuListener = new MenuListener();
    TController tc = new TController();
    String currentMove;
    JMenu menuGame;
    JMenuItem menuHumanHuman, menuHumanRobot, menuRobotHuman, menuRobotRobot, menuExit;

    public TicTacToe() {
        super("Tic Tac Toe");
        setSize(450, 450);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        GridLayout layoutField = new GridLayout(3, 3, 0, 0);
        fieldGrid.setLayout(layoutField);
        initGameField();
        initMenu();
        menuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        getContentPane().add(menuPanel);
        getContentPane().add(toolPanel);
        getContentPane().add(fieldGrid);
        getContentPane().add(statusPanel);
        setVisible(true);
    }

    public void initMenu() {
        menuGame = new JMenu("Game");
        menuGame.setName("MenuGame");
        menuPanel.add(menuGame);
        menuHumanHuman= new JMenuItem("Human vs Human");
        menuHumanHuman.setName("MenuHumanHuman");
        menuHumanHuman.addActionListener(menuListener);
        menuHumanRobot= new JMenuItem("Human vs Robot");
        menuHumanRobot.setName("MenuHumanRobot");
        menuHumanRobot.addActionListener(menuListener);
        menuRobotHuman= new JMenuItem("Robot vs Human");
        menuRobotHuman.setName("MenuRobotHuman");
        menuRobotHuman.addActionListener(menuListener);
        menuRobotRobot= new JMenuItem("Robot vs Robot");
        menuRobotRobot.setName("MenuRobotRobot");
        menuRobotRobot.addActionListener(menuListener);
        menuExit = new JMenuItem("Exit");
        menuExit.setName("MenuExit");
        menuExit.addActionListener(menuListener);
        menuGame.add(menuHumanHuman);
        menuGame.add(menuHumanRobot);
        menuGame.add(menuRobotHuman);
        menuGame.add(menuRobotRobot);
        menuGame.addSeparator();
        menuGame.add(menuExit);
    }

    public void resetGame() {
        for (Map.Entry<String, JButton> entry : buttonsMap.entrySet()) {
            entry.getValue().setText(" ");
            entry.getValue().setEnabled(true);
        }
        tc.resetGame();
        playerOneButton.setText("Human");
        playerTwoButton.setText("Human");
        playerOneButton.setEnabled(true);
        playerTwoButton.setEnabled(true);
        statusLabel.setText("Game is not started");
        currentMove = playerOneButton.getText();
    }

    public void startGame() {
        resetButton.setText("Reset");
        System.out.println(resetButton.getText());
        for (Map.Entry<String, JButton> entry : buttonsMap.entrySet()) {
            entry.getValue().setText(" ");
            entry.getValue().setEnabled(true);
        }
        tc.startGame();
        playerOneButton.setEnabled(false);
        playerTwoButton.setEnabled(false);
        currentMove = playerOneButton.getText();
        setStatusLabel();
        nextMove();
    }

    public void initGameField() {
        for (Map.Entry<String, JButton> entry : buttonsMap.entrySet()) {
            entry.getValue().setName(entry.getKey());
            entry.getValue().setText(" ");
            entry.getValue().setFont(new Font("Arial", Font.BOLD, 40));
            entry.getValue().setFocusPainted(false);
            entry.getValue().addActionListener(buttonListener);
            entry.getValue().setEnabled(false);
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
        tc.resetGame();
    }

    public void nextMove() {
        if (currentMove.equals("Robot")) {
            buttonsMap.get(tc.robotMove()).setText(tc.getLetter());
            tc.changeMove();
            changeMove();
            nextMove();
        }
    }

    public void changeMove() {
        currentMove = currentMove.equals(playerOneButton.getText()) ?
                playerTwoButton.getText() : playerOneButton.getText();
        System.out.println("current move " + currentMove);
        setStatusLabel();
    }



    public void setButtonText(String buttonKey) {
        if (tc.getStatus().equals("Game in progress") && currentMove.equals("Human")) {
            if (buttonsMap.get(buttonKey).getText().equals(" ")) {
                buttonsMap.get(buttonKey).setText(tc.makeMove(buttonKey));
                tc.changeMove();
                setStatusLabel();
                changeMove();
                nextMove();
            }
        }
    }

    public void setStatusLabel() {
        String status = tc.getStatus();
        statusLabel.setText(status);
        if (statusLabel.getText().equals("X wins") || statusLabel.getText().equals("O wins")
                || statusLabel.getText().equals("Draw")) {
            for (Map.Entry<String, JButton> entry : buttonsMap.entrySet()) {
                entry.getValue().setEnabled(false);
            }
        }

//        if (!status.equals("GameOver")) {
//            statusLabel.setText(status);
//        }
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

    public void menuHandler(String option) {
        switch (option) {
            case "MenuHumanHuman":
                if (!statusLabel.getText().equals("Game in progress")) {
                    resetGame();
                    playerOneButton.setText("Human");
                    playerTwoButton.setText("Human");
                    startGame();
                }
                break;
            case "MenuHumanRobot":
                if (!statusLabel.getText().equals("Game in progress")) {
                    resetGame();
                    playerOneButton.setText("Human");
                    playerTwoButton.setText("Robot");
                    startGame();
                }
                break;
            case "MenuRobotHuman":
                if (!statusLabel.getText().equals("Game in progress")) {
                    resetGame();
                    playerOneButton.setText("Robot");
                    playerTwoButton.setText("Human");
                    startGame();
                }
                break;
            case "MenuRobotRobot":
                if (!statusLabel.getText().equals("Game in progress")) {
                    resetGame();
                    playerOneButton.setText("Robot");
                    playerTwoButton.setText("Robot");
                    startGame();
                }
                break;
            case "MenuExit":
                System.exit(0);
                break;
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
                resetButton.setText("Reset");
                startGame();
            } else {
                resetButton.setText("Start");
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

    class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menuHandler(((JMenuItem) e.getSource()).getName());
        }
    }
}
