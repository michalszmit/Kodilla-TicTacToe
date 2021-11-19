package com.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class TicTacToe extends Application {

    ClassLoader classLoader = getClass().getClassLoader();
    Image imageback = new Image(Objects.requireNonNull(classLoader.getResourceAsStream("tavern.jpg")));

    private int won, lost, tied, roundCounter, turn;
    private boolean roundOver = false;

    private Label header;
    private Label status;
    private Label wins;
    private Label loss;
    private Label tie;
    private Label round;
    private Button[][] boardButtons;

    public static void main (String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        won = 0;
        lost = 0;
        tied = 0;
        roundCounter = 1;

        //Labels
        header = new Label("Tavern Tic Tac Toe");
        header.setFont(new Font(50));
        header.setTextFill(Color.web("#FFF"));
        status = new Label ("Round 1");
        status.setFont(new Font(50));
        status.setTextFill(Color.web("#FFF"));
        wins = new Label("Wins: " + won);
        wins.setFont(new Font(50));
        wins.setTextFill(Color.web("#FFF"));
        loss = new Label("Loss: " + lost);
        loss.setFont(new Font(50));
        loss.setTextFill(Color.web("#FFF"));
        tie = new Label("Ties: " + tied);
        tie.setFont(new Font(50));
        tie.setTextFill(Color.web("#FFF"));
        round = new Label("Round: " + roundCounter);
        round.setFont(new Font(50));
        round.setTextFill(Color.web("#FFF"));

        //Utility buttons
        Button nextRound = new Button("Next Round");
        nextRound.setOnAction(this::clearBoard);
        Button nextGame = new Button ("Next Game");
        nextGame.setOnAction(this::clearAll);
        Button endGame = new Button ("End Game");
        endGame.setOnAction(e -> System.exit(0));

        //Background
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        //GridPane Layout
        GridPane grid = new GridPane();
        grid.add(header, 11, 0);
        grid.add(wins, 0, 2);
        grid.add(loss, 0, 3);
        grid.add(tie, 0, 4);
        grid.add(round, 0, 5);
        grid.add(nextRound, 0, 7);
        grid.add(nextGame, 0, 8 );
        grid.add(endGame, 0, 9 );
        grid.add(status, 11, 3);
        grid.setBackground(background);

        //Game Board buttons
        boardButtons = new Button[3][3];

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3 ; row++) {
                boardButtons[column][row] = new Button ("");
                boardButtons[column][row].setOpacity(0.5);
                boardButtons[column][row].setPrefSize(150 , 150);
                boardButtons[column][row].setFont(new Font(60));
                boardButtons[column][row].setOnAction(this::placeXor0);
                grid.add(boardButtons[column][row], column + 15, row + 3);
            }
        }

        //GridPane sizing
        grid.setVgap(25);
        grid.setHgap(25);
        grid.setPrefSize(100, 100);
        grid.setMaxSize(100, 600);
        grid.setPadding(new Insets(30));

        Scene scene = new Scene(grid, 1600, 900, Color.BLACK);

        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    private void clearBoard(ActionEvent actionEvent) {

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                boardButtons[column][row].setText("");
            }
        }

        roundCounter++;
        turn = 0;
        status.setText("Round " + roundCounter + " X turn!");
        round.setText("Round: " + roundCounter);
        roundOver = false;
    }

    private void clearAll(ActionEvent actionEvent) {

        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                boardButtons[column][row].setText("");
            }
        }

        roundCounter = 1;
        won = 0;
        lost = 0;
        tied =0;
        turn = 0;
        status.setText("New Game " + "\nRound " + roundCounter + " X turn!");
        round.setText("Round: " + roundCounter);
        wins.setText("Wins: " + won);
        loss.setText("Loss: " + lost);
        tie.setText("Ties: " + tied);
        roundOver = false;
    }

    private void placeXor0(ActionEvent actionEvent) {
        if(!roundOver) {
            Button click = (Button) actionEvent.getSource();

            if (click.getText().length() > 0) {
                // VS AI
                if(turn %2 !=0) {           // Comment out if you want to play vs another human
                    computerTurn();         // Comment out if you want to play vs another human
                }                           // Comment out if you want to play vs another human
                return;
            }

            String value;
            if (turn % 2 == 0) {
                // X turn
                value = "X";
            } else {
                // 0 turn
                value = "0";
            }

            turn++;
            click.setText(value);

            if (turn >= 5) {
                if (checkWinner(value)) {
                    status.setText(String.format("%S has won!", value));

                    if (value.equals("X")) {
                        won++;
                        wins.setText("Wins: " + won);
                    }
                    else {
                        lost++;
                        loss.setText("Loss: " + lost);
                    }
                    roundOver = true;
                    return;
                }
            }

            if(turn == 9) {
                roundOver = true;
                status.setText("You tied!");
                tied++;
                tie.setText("Ties: " + tied );
                return;
            }

            status.setText(String.format("%S's turn.", turn % 2 == 0 ? "X" : "0"));

            // VS AI
            if (turn %2 != 0) {     //Comment out if you want to play vs another human
                computerTurn();     //Comment out if you want to play vs another human
            }                       //Comment out if you want to play vs another human
        }

    }

    private void computerTurn() {
        // AI logic, comment out the entire method if you want to play vs another human
        boardButtons[(int)(Math.random()*3)][(int)(Math.random()*3)].fire();
    }

    private boolean checkWinner(String player) {

        //Vertical
        if(player.equals(boardButtons[0][0].getText())
                && player.equals(boardButtons[0][1].getText())
                && player.equals(boardButtons[0][2].getText())) {
            return true;
        }
        if(player.equals(boardButtons[1][0].getText())
                && player.equals(boardButtons[1][1].getText())
                && player.equals(boardButtons[1][2].getText())) {
            return true;
        }
        if(player.equals(boardButtons[2][0].getText())
                && player.equals(boardButtons[2][1].getText())
                && player.equals(boardButtons[2][2].getText())) {
            return true;
        }

        //Horizontal
        if(player.equals(boardButtons[0][0].getText())
                && player.equals(boardButtons[1][0].getText())
                && player.equals(boardButtons[2][0].getText())) {
            return true;
        }
        if(player.equals(boardButtons[0][1].getText())
                && player.equals(boardButtons[1][1].getText())
                && player.equals(boardButtons[2][1].getText())) {
            return true;
        }
        if(player.equals(boardButtons[0][2].getText())
                && player.equals(boardButtons[1][2].getText())
                && player.equals(boardButtons[2][2].getText())) {
            return true;
        }

        //Diagonal
        if(player.equals(boardButtons[0][0].getText())
                && player.equals(boardButtons[1][1].getText())
                && player.equals(boardButtons[2][2].getText())) {
            return true;
        }
        if(player.equals(boardButtons[2][0].getText())
                && player.equals(boardButtons[1][1].getText())
                && player.equals(boardButtons[0][2].getText())) {
            return true;
        }

        return false;
    }
}
