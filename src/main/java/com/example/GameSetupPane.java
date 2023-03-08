package com.example;

import java.io.FileNotFoundException;
import java.util.HashSet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSetupPane extends StackPane {

    private Button btnHome;
    private Button btnStartGame;

    public GameSetupPane(Stage stage) {
        super();

        BorderPane innerPane = new BorderPane();

        btnHome = new Button("Back");
        btnHome.setMinSize(getWidth() / 2, getHeight() / 4);
        
        btnHome.setOnAction(e -> {
            try {
                stage.setScene(new Scene(new HomeScreenPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        

        btnStartGame = new Button("Start");
        btnStartGame.setMinSize(getWidth() / 2, getHeight() / 4);

        ToggleGroup grpPlayerAmount = new ToggleGroup();
        IntRadioButton rdo2players = new IntRadioButton("2 Players", 2);
        IntRadioButton rdo3players = new IntRadioButton("3 Players", 3);
        IntRadioButton rdo4players = new IntRadioButton("4 Players", 4);

        rdo2players.setToggleGroup(grpPlayerAmount);
        rdo3players.setToggleGroup(grpPlayerAmount);
        rdo4players.setToggleGroup(grpPlayerAmount);

        rdo4players.setSelected(true);


        ToggleGroup grpRoundAmount = new ToggleGroup();
        IntRadioButton rdo1Round = new IntRadioButton("1 Round", 1);
        IntRadioButton rdo3Rounds = new IntRadioButton("3 Rounds", 3);
        IntRadioButton rdo5Rounds = new IntRadioButton("5 Rounds", 5);

        rdo1Round.setToggleGroup(grpRoundAmount);
        rdo3Rounds.setToggleGroup(grpRoundAmount);
        rdo5Rounds.setToggleGroup(grpRoundAmount);

        rdo3Rounds.setSelected(true);

        btnStartGame.setOnAction(e -> {
            int numPlayers = ((IntRadioButton) grpPlayerAmount.getSelectedToggle()).getValue();
            int numRounds = ((IntRadioButton) grpRoundAmount.getSelectedToggle()).getValue();
            stage.setScene(new Scene(new GamePane(numRounds, numPlayers), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });


        VBox roundsBox = new VBox();
        Label lblRounds = new Label("Rounds:");
        lblRounds.setTextFill(Color.WHITE);
        roundsBox.getChildren().addAll(lblRounds,rdo1Round, rdo3Rounds, rdo5Rounds);
        roundsBox.setAlignment(Pos.CENTER_LEFT);

        VBox playersBox = new VBox();
        Label lblPlayer = new Label("Players:");
        lblPlayer.setTextFill(Color.WHITE);
        playersBox.getChildren().addAll(lblPlayer, rdo2players, rdo3players, rdo4players);
        playersBox.setAlignment(Pos.CENTER_LEFT);

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(btnStartGame, btnHome);


        StackPane leftPane = new StackPane();
        HBox leftAlignBox = new HBox(playersBox);
        leftAlignBox.setAlignment(Pos.CENTER);
        leftPane.setMinSize(500, 250);
        leftPane.getChildren().addAll(leftAlignBox);

        StackPane rightPane = new StackPane();
        HBox rightAlignBox = new HBox(roundsBox);
        rightAlignBox.setAlignment(Pos.CENTER);
        rightPane.setMinSize(500, 250);
        rightPane.getChildren().addAll(rightAlignBox);


        innerPane.setLeft(leftPane);
        innerPane.setRight(rightPane);
        innerPane.setBottom(buttonsBox);

        this.setBackground(new Background(new BackgroundFill(Color.rgb(64,64,64), new CornerRadii(0.0), new Insets(0,0,0,0))));

        this.getChildren().addAll(innerPane);

    }

    @Override
    public void setWidth(double width) { 
        super.setWidth(width);

        btnStartGame.setMinWidth(width / 2);
        btnStartGame.setPrefWidth(width / 2);
        btnStartGame.setMaxWidth(width / 2);

        btnHome.setMinWidth(width / 2);
        btnHome.setPrefWidth(width / 2);
        btnHome.setMaxWidth(width / 2);
    }

    @Override
    public void setHeight(double height) { 
        super.setHeight(Math.max(height, 200));

        btnStartGame.setMinHeight(height / 4);
        btnStartGame.setPrefHeight(height / 4);
        btnStartGame.setMaxHeight(height / 4);

        btnHome.setMinHeight(height / 4);
        btnHome.setPrefHeight(height / 4);
        btnHome.setMaxHeight(height / 4);
    }
    
    private class IntRadioButton extends RadioButton {
        private int value;

        private int getValue() {
            return this.value;
        }

        private IntRadioButton(String text, int value) {
            super(text);
            this.setTextFill(Color.WHITE);
            this.value = value;
        }
    }
}
