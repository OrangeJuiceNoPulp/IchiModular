package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameStartStage extends Stage {

    public GameStartStage(Game game) {
        super();

        this.setTitle("Game Start!");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.getIcons().add(new Image(this.getClass().getResource("cardBackSquare.png").toExternalForm()));

        BorderPane startGamePane = new BorderPane();

        startGamePane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));


        Button btnStartRound = new Button();

        btnStartRound.setMinSize(400, 100);
        btnStartRound.setMaxSize(400, 100);
        btnStartRound.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnStartRound.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnStartRound.setTextFill(Color.WHITESMOKE);
        btnStartRound.setFont(new Font("Perpetua Bold Italic", 25));
        btnStartRound.setOnMouseEntered(e -> {
            btnStartRound.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnStartRound.setOnMouseExited(e -> {
            btnStartRound.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });
        btnStartRound.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));



        btnStartRound.setText("Start Round");
        btnStartRound.setOnAction(e -> {
            this.close();
        });




        Text txtGameStart = new Text("Game Start!");
        txtGameStart.setFill(Color.WHITESMOKE);
        txtGameStart.setFont(new Font("Perpetua Bold Italic", 50));
        txtGameStart.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtRoundNumber = new Text("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds());
        txtRoundNumber.setFill(Color.WHITESMOKE);
        txtRoundNumber.setFont(new Font("Perpetua Bold Italic", 40));
        txtRoundNumber.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtSpacing = new Text("");
        txtSpacing.setFill(Color.WHITESMOKE);
        txtSpacing.setFont(new Font("Perpetua Bold Italic", 10));
        txtSpacing.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtFirstPlayer = new Text("First Player: " + game.getCurrentPlayer().getPlayerName());
        txtFirstPlayer.setFill(Color.WHITESMOKE);
        txtFirstPlayer.setFont(new Font("Perpetua Bold Italic", 25));
        txtFirstPlayer.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        VBox vboxInfoText = new VBox(25);
        vboxInfoText.setPadding(new Insets(25, 0, 25, 0));
        vboxInfoText.setAlignment(Pos.CENTER);
        vboxInfoText.getChildren().addAll(txtGameStart, txtRoundNumber, txtSpacing, txtFirstPlayer);
        vboxInfoText.setMaxWidth(400);



        HBox hboxRoundInfo = new HBox();
        hboxRoundInfo.getChildren().addAll(vboxInfoText);
        hboxRoundInfo.setAlignment(Pos.CENTER);
       // hboxRoundInfo.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        

        startGamePane.setCenter(hboxRoundInfo);
        startGamePane.setBottom(btnStartRound);
        
        Scene mainScene = new Scene(startGamePane);
        this.setScene(mainScene);

    }

}
