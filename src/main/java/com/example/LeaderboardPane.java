package com.example;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LeaderboardPane extends BorderPane {

    private Button btnHome;

    public LeaderboardPane(Stage stage) {
        super();

        //ScrollPane textPane = new ScrollPane();
        btnHome = new Button("Back");

        btnHome.setOnAction(e -> {
            stage.setScene(new Scene(new HomeScreenPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });

        VBox vboxScores = new VBox();

        Text txtComingSoon = new Text("Leaderboard Coming Soon!");
        txtComingSoon.setFont(new Font("Perpetua Bold Italic", 60));



        vboxScores.getChildren().addAll(txtComingSoon);
        vboxScores.setAlignment(Pos.CENTER);

        //textPane.setContent(vboxScores);

        this.setCenter(vboxScores);
        this.setBottom(btnHome);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);

        btnHome.setMinWidth(width);
        btnHome.setPrefWidth(width);
        btnHome.setMaxWidth(width);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);

        btnHome.setMinHeight(height / 8);
        btnHome.setPrefHeight(height / 8);
        btnHome.setMaxHeight(height / 8);
    }
}
