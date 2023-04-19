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

public class RoundStartStage extends Stage {

    public RoundStartStage(Game game) {
        super();

        this.setTitle("Round " + game.getRoundNumber() + " Start!");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.getIcons().add(new Image(this.getClass().getResource("cardBackSquare.png").toExternalForm()));

        BorderPane startRoundPane = new BorderPane();

        startRoundPane.setBackground(
                new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));

        Button btnStartRound = new Button();

        btnStartRound.setMinSize(800, 100);
        btnStartRound.setMaxSize(800, 100);
        btnStartRound.setBorder(new Border(
                new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnStartRound.setBackground(
                new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnStartRound.setTextFill(Color.WHITESMOKE);
        btnStartRound.setFont(new Font("Perpetua Bold Italic", 25));
        btnStartRound.setOnMouseEntered(e -> {
            btnStartRound.setBackground(
                    new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnStartRound.setOnMouseExited(e -> {
            btnStartRound.setBackground(
                    new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });
        btnStartRound.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        btnStartRound.setText("Start Round");
        btnStartRound.setOnAction(e -> {
            this.close();
        });

        Text txtGameStart = new Text("New Round Start!");
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
        vboxInfoText.setPadding(new Insets(25, 25, 25, 25));
        vboxInfoText.setAlignment(Pos.CENTER);
        vboxInfoText.getChildren().addAll(txtGameStart, txtRoundNumber, txtSpacing, txtFirstPlayer);
        vboxInfoText.setMinWidth(400);
        vboxInfoText.setMaxWidth(400);

        HBox hboxRoundInfo = new HBox();
        hboxRoundInfo.getChildren().addAll(vboxInfoText);
        hboxRoundInfo.setAlignment(Pos.CENTER);
        // hboxRoundInfo.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtEndRound = new Text("End of Round " + (game.getRoundNumber() - 1));
        txtEndRound.setFill(Color.WHITESMOKE);
        txtEndRound.setFont(new Font("Perpetua Bold Italic", 25));
        txtEndRound.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtRoundWinner = new Text("Round Winner: " + game.getLastRoundWinner().getPlayerName());
        txtRoundWinner.setFill(Color.WHITESMOKE);
        txtRoundWinner.setFont(new Font("Perpetua Bold Italic", 25));
        txtRoundWinner.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtRoundVictoryBonus = new Text("Round Victory Bonus: +" + game.getLastRoundVictoryBonus() + " points");
        txtRoundVictoryBonus.setFill(Color.WHITESMOKE);
        txtRoundVictoryBonus.setFont(new Font("Perpetua Bold Italic", 25));
        txtRoundVictoryBonus.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtCurrentRankings = new Text("  Current Rankings:");
        txtCurrentRankings.setFill(Color.WHITESMOKE);
        txtCurrentRankings.setFont(new Font("Perpetua Bold Italic", 25));
        txtCurrentRankings.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        VBox vboxCurrentRankings = new VBox();
        vboxCurrentRankings.setAlignment(Pos.CENTER_LEFT);
        vboxCurrentRankings.getChildren().add(txtCurrentRankings);
        vboxCurrentRankings.setMaxWidth(400);

        for (Text txt : game.getCurrentRankings()) {
            vboxCurrentRankings.getChildren().add(txt);
        }

        VBox vboxEndRoundInfo = new VBox(25);
        vboxEndRoundInfo.setPadding(new Insets(25, 25, 25, 25));
        vboxEndRoundInfo.setAlignment(Pos.CENTER);
        vboxEndRoundInfo.getChildren().addAll(txtEndRound, txtRoundWinner, txtRoundVictoryBonus, vboxCurrentRankings);
        vboxEndRoundInfo.setMinWidth(400);
        vboxEndRoundInfo.setMaxWidth(400);

        vboxEndRoundInfo.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 1, 0, 0))));
        hboxRoundInfo.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 0, 0, 1))));

        startRoundPane.setLeft(vboxEndRoundInfo);
        startRoundPane.setRight(hboxRoundInfo);
        startRoundPane.setBottom(btnStartRound);

        Scene mainScene = new Scene(startRoundPane);
        this.setScene(mainScene);

    }

}
