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

public class TicTacToe extends Application {

    private final Image imageback = new Image("file:src/main/resources/tavern.jpg");
    private int won, lost, tied, roundCounter, turn;
    private boolean roundOver = false;

    //Game Board
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8;

    private Label status;
    private Label wins;
    private Label loss;
    private Label tie;
    private Label round;

    public static void main (String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        //Labels
        //Labels
        Label header = new Label("Tavern Tic Tac Toe");
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
        Button nextGame = new Button ("Next Game");
        Button endGame = new Button ("End Game");

        //Game Board buttons

        button0 = new Button ("");
        button0.setOpacity(0.5);
        button0.setPrefSize(150 , 150);
        button0.setFont(new Font(60));
        button0.setOnAction(this::placeXor0);

        button1 = new Button ("");
        button1.setOpacity(0.5);
        button1.setPrefSize(150 , 150);
        button1.setFont(new Font(60));
        button1.setOnAction(this::placeXor0);

        button2 = new Button ("");
        button2.setOpacity(0.5);
        button2.setPrefSize(150 , 150);
        button2.setFont(new Font(60));
        button2.setOnAction(this::placeXor0);

        button3 = new Button ("");
        button3.setOpacity(0.5);
        button3.setPrefSize(150 , 150);
        button3.setFont(new Font(60));
        button3.setOnAction(this::placeXor0);

        button4 = new Button ("");
        button4.setOpacity(0.5);
        button4.setPrefSize(150 , 150);
        button4.setFont(new Font(60));
        button4.setOnAction(this::placeXor0);

        button5 = new Button ("");
        button5.setOpacity(0.5);
        button5.setPrefSize(150 , 150);
        button5.setFont(new Font(60));
        button5.setOnAction(this::placeXor0);

        button6 = new Button ("");
        button6.setOpacity(0.5);
        button6.setPrefSize(150 , 150);
        button6.setFont(new Font(60));
        button6.setOnAction(this::placeXor0);

        button7 = new Button ("");
        button7.setOpacity(0.5);
        button7.setPrefSize(150 , 150);
        button7.setFont(new Font(60));
        button7.setOnAction(this::placeXor0);

        button8 = new Button ("");
        button8.setOpacity(0.5);
        button8.setPrefSize(150 , 150);
        button8.setFont(new Font(60));
        button8.setOnAction(this::placeXor0);

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
        grid.add(button0, 15, 3);
        grid.add(button1, 16, 3);
        grid.add(button2, 17, 3);
        grid.add(button3, 15, 4);
        grid.add(button4, 16, 4);
        grid.add(button5, 17, 4);
        grid.add(button6, 15, 5);
        grid.add(button7, 16, 5);
        grid.add(button8, 17, 5);

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


    private void placeXor0(ActionEvent actionEvent) {
        if(!roundOver) {
            Button click = (Button) actionEvent.getSource();

            if (click.getText().length() > 0) {
                // AI Move - not working because of random buttonproblems
//                if(turn %2 !=0) {
//                    computerTurn();
//                }
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
                    roundOver = true;
                    return;
                }
            }

            if(turn == 9) {
                roundOver = true;
                status.setText("You tied!");
                return;
            }

            status.setText(String.format("%S's turn.", turn % 2 == 0 ? "X" : "0"));

            // VS AI - not working because of random button problems
//            if (turn %2 != 0) {
//                computerTurn();
//            }
        }

    }

//    private void computerTurn() {
//        // TODO - smart AI algorithm, if there is time.
//        // Random AI
//        button[(int)(Math.random()*9)].fire();
//    }

    private boolean checkWinner(String player) {
        //Horizontal
        if(player.equals(button0.getText()) &&
                player.equals(button1.getText()) &&
                player.equals(button2.getText())) {
            return true;
        }
        if(player.equals(button3.getText()) &&
                player.equals(button4.getText()) &&
                player.equals(button5.getText())) {
            return true;
        }
        if(player.equals(button6.getText()) &&
                player.equals(button7.getText()) &&
                player.equals(button8.getText())) {
            return true;
        }
        //Vertical
        if(player.equals(button0.getText()) &&
                player.equals(button3.getText()) &&
                player.equals(button6.getText())) {
            return true;
        }
        if(player.equals(button1.getText()) &&
                player.equals(button4.getText()) &&
                player.equals(button7.getText())) {
            return true;
        }
        if(player.equals(button2.getText()) &&
                player.equals(button5.getText()) &&
                player.equals(button8.getText())) {
            return true;
        }
        //Diagonal
        if(player.equals(button0.getText()) &&
                player.equals(button4.getText()) &&
                player.equals(button8.getText())) {
            return true;
        }
        if(player.equals(button2.getText()) &&
                player.equals(button4.getText()) &&
                player.equals(button6.getText())) {
            return true;
        }

        return false;
    }

    private void setScore() {
        won = 0;
        lost = 0;
        tied = 0;
        roundCounter = 1;
    }
}
