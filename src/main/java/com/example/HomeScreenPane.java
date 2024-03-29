package com.example;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreenPane extends StackPane {

    private Button btnPlayGame;
    private Button btnLeaderboard;
    private Button btnInstructions;
    private Button btnExit;

    private ImageView logoView;
    
    public HomeScreenPane(Stage stage) throws FileNotFoundException {
        super();

        this.setMinHeight(200);
        this.setMinWidth(200);

        this.setPrefSize(500, 400);

        BorderPane innerPane = new BorderPane();
        
        VBox vboxButtons = new VBox(10);

        logoView = new ImageView(new Image(this.getClass().getResourceAsStream("ichiLogo.png")));

        double min = Math.min(Math.max(150 - 130, 0), Math.max(200, 0));

        logoView.setFitHeight(min / 1.25);
        logoView.setFitWidth(min / 1.25);

        btnPlayGame = new Button("Play");
        btnLeaderboard = new Button("Leaderboard");
        btnInstructions = new Button("Instructions");
        btnExit = new Button("Exit");

        btnPlayGame.setMinWidth(getWidth() / 4);
        btnPlayGame.setPrefWidth(getWidth() / 4);
        btnPlayGame.setMaxWidth(getWidth() / 4);

        btnLeaderboard.setMinWidth(getWidth() / 4);
        btnLeaderboard.setPrefWidth(getWidth() / 4);
        btnLeaderboard.setMaxWidth(getWidth() / 4);

        btnInstructions.setMinWidth(getWidth() / 4);
        btnInstructions.setPrefWidth(getWidth() / 4);
        btnInstructions.setMaxWidth(getWidth() / 4);
        
        btnExit.setMinWidth(getWidth() / 4);
        btnExit.setPrefWidth(getWidth() / 4);
        btnExit.setMaxWidth(getWidth() / 4);

        

        btnPlayGame.setMinHeight(25);
        btnPlayGame.setPrefHeight(25);
        btnPlayGame.setMaxHeight(25);

        btnInstructions.setMinHeight(25);
        btnInstructions.setPrefHeight(25);
        btnInstructions.setMaxHeight(25);

        btnLeaderboard.setMinHeight(25);
        btnLeaderboard.setPrefHeight(25);
        btnLeaderboard.setMaxHeight(25);

        btnExit.setMinHeight(25);
        btnExit.setPrefHeight(25);
        btnExit.setMaxHeight(25);

        vboxButtons.getChildren().addAll(btnPlayGame, btnLeaderboard, btnInstructions, btnExit);
        vboxButtons.setAlignment(Pos.CENTER);

        VBox vboxTextSpacing = new VBox(10);
        vboxTextSpacing.getChildren().addAll(logoView, vboxButtons);
        vboxTextSpacing.setAlignment(Pos.CENTER);

        vboxTextSpacing.setMinHeight(getHeight());
        vboxTextSpacing.setPrefHeight(getHeight());
        vboxTextSpacing.setMaxHeight(getHeight());

        btnPlayGame.setOnAction(e -> {
            stage.setScene(new Scene(new GameSetupPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });

        btnLeaderboard.setOnAction(e -> {
            stage.setScene(new Scene(new LeaderboardPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });

        btnInstructions.setOnAction(e -> {
            stage.setScene(new Scene(new InstructionsPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });

        btnExit.setOnAction(e -> {
            stage.close();
        });
        
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64,64,64), new CornerRadii(0.0), new Insets(0,0,0,0))));


        innerPane.setCenter(vboxTextSpacing);
        this.getChildren().addAll(innerPane);
        
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        btnPlayGame.setMinWidth(width / 4);
        btnPlayGame.setPrefWidth(width / 4);
        btnPlayGame.setMaxWidth(width / 4);

        btnLeaderboard.setMinWidth(width / 4);
        btnLeaderboard.setPrefWidth(width / 4);
        btnLeaderboard.setMaxWidth(width / 4);

        btnInstructions.setMinWidth(width / 4);
        btnInstructions.setPrefWidth(width / 4);
        btnInstructions.setMaxWidth(width / 4);

        btnExit.setMinWidth(width / 4);
        btnExit.setPrefWidth(width / 4);
        btnExit.setMaxWidth(width / 4);

        double min = Math.min(Math.max(getHeight() - 130, 0), Math.max(getWidth(), 0));

        logoView.setFitHeight(min / 1.25);
        logoView.setFitWidth(min / 1.25);

    }

    @Override
    public void setHeight(double height) {
        super.setHeight(Math.max(height, 200));

        double min = Math.min(Math.max(getHeight() - 130, 0), Math.max(getWidth(), 0));

        logoView.setFitHeight(min / 1.25);
        logoView.setFitWidth(min / 1.25);
    }

}
