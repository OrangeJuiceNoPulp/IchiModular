package com.example;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
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

public class GameEndStage extends Stage {

    private Player determineOverallWinner(Game game) {
        ArrayList<Player> tempPlayerArray = new ArrayList<Player>(5);
        for (Player player: game.getPlayers()) {
            tempPlayerArray.add(player);
        }
        tempPlayerArray.sort(new PlayerScoreComparator());

        return tempPlayerArray.get(0);
    }

    public GameEndStage(Game game, Stage mainStage) {
        super();

        this.setTitle("Game Start!");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setOnCloseRequest(e -> {

            //TODO update leaderboard here
            game.getGamePane().closeSoundResources();
            mainStage.close();
            this.close();
        });

        BorderPane startGamePane = new BorderPane();

        startGamePane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));


        Button btnPlayAgain = new Button();

        btnPlayAgain.setMinSize(200, 100);
        btnPlayAgain.setMaxSize(200, 100);
        btnPlayAgain.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnPlayAgain.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnPlayAgain.setTextFill(Color.WHITESMOKE);
        btnPlayAgain.setFont(new Font("Perpetua Bold Italic", 25));
        btnPlayAgain.setOnMouseEntered(e -> {
            btnPlayAgain.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnPlayAgain.setOnMouseExited(e -> {
            btnPlayAgain.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });


        btnPlayAgain.setText("Play Again");
        btnPlayAgain.setOnAction(e -> {
            //TODO update leaderboard here
            game.getGamePane().closeSoundResources();
            game.setGameNull();
            mainStage.setScene(new Scene(new GameSetupPane(mainStage), mainStage.getScene().getWidth(), mainStage.getScene().getHeight()));
            this.close();
            Runtime.getRuntime().gc();
        });


        Button btnHome = new Button();

        btnHome.setMinSize(200, 100);
        btnHome.setMaxSize(200, 100);
        btnHome.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnHome.setTextFill(Color.WHITESMOKE);
        btnHome.setFont(new Font("Perpetua Bold Italic", 25));
        btnHome.setOnMouseEntered(e -> {
            btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnHome.setOnMouseExited(e -> {
            btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });


        btnHome.setText("Home");
        btnHome.setOnAction(e -> {

        //TODO update leaderboard here
            game.getGamePane().closeSoundResources();
            game.setGameNull();
            mainStage.setScene(new Scene(new HomeScreenPane(mainStage), mainStage.getScene().getWidth(), mainStage.getScene().getHeight()));
            this.close();
            Runtime.getRuntime().gc();
        });

        Button btnClose = new Button();

        btnClose.setMinSize(200, 100);
        btnClose.setMaxSize(200, 100);
        btnClose.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnClose.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnClose.setTextFill(Color.WHITESMOKE);
        btnClose.setFont(new Font("Perpetua Bold Italic", 25));
        btnClose.setOnMouseEntered(e -> {
            btnClose.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnClose.setOnMouseExited(e -> {
            btnClose.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });


        btnClose.setText("Exit");
        btnClose.setOnAction(e -> {

            //TODO update leaderboard here
            game.getGamePane().closeSoundResources();
            mainStage.close();
            this.close();
        });


        Text txtEndOfGame = new Text("End of Game!");
        txtEndOfGame.setFill(Color.WHITESMOKE);
        txtEndOfGame.setFont(new Font("Perpetua Bold Italic", 50));
        txtEndOfGame.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Player winner = determineOverallWinner(game);

        Text txtOverallWinner1 = new Text("");
        if (winner != null) {
            txtOverallWinner1.setText(winner.getPlayerName() + " Wins!");
        }
        txtOverallWinner1.setFill(Color.WHITESMOKE);
        txtOverallWinner1.setFont(new Font("Perpetua Bold Italic", 35));
        txtOverallWinner1.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtRoundWinner = new Text("Final Round Winner: " + game.getLastRoundWinner().getPlayerName());
        txtRoundWinner.setFill(Color.WHITESMOKE);
        txtRoundWinner.setFont(new Font("Perpetua Bold Italic", 25));
        txtRoundWinner.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtRoundVictoryBonus = new Text("Round Victory Bonus: +" + game.getLastRoundVictoryBonus() + " points");
        txtRoundVictoryBonus.setFill(Color.WHITESMOKE);
        txtRoundVictoryBonus.setFont(new Font("Perpetua Bold Italic", 25));
        txtRoundVictoryBonus.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtCurrentRankings = new Text("Final Rankings:");
        txtCurrentRankings.setFill(Color.WHITESMOKE);
        txtCurrentRankings.setFont(new Font("Perpetua Bold Italic", 25));
        txtCurrentRankings.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        VBox vboxFinalRankings = new VBox();
        vboxFinalRankings.setAlignment(Pos.CENTER_LEFT);
        vboxFinalRankings.getChildren().add(txtCurrentRankings);
        vboxFinalRankings.setMaxWidth(600);

        for (Text txt : game.getCurrentRankings()) {
            vboxFinalRankings.getChildren().add(txt);
        }

        Text txtOverallWinner2 = new Text("");
        if (winner != null) {
            txtOverallWinner2.setText("Overall Winner: " + winner.getPlayerName());
        }
        txtOverallWinner2.setFill(Color.WHITESMOKE);
        txtOverallWinner2.setFont(new Font("Perpetua Bold Italic", 35));
        txtOverallWinner2.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        HBox hboxAlignRankings = new HBox();
        hboxAlignRankings.getChildren().addAll(vboxFinalRankings);
        hboxAlignRankings.setAlignment(Pos.CENTER);

        VBox vboxEndGameInfo = new VBox(25);
        vboxEndGameInfo.setPadding(new Insets(25, 25, 25, 25));
        vboxEndGameInfo.setAlignment(Pos.CENTER);
        vboxEndGameInfo.getChildren().addAll(txtEndOfGame, txtOverallWinner1, txtRoundWinner, txtRoundVictoryBonus, hboxAlignRankings, txtOverallWinner2);
        vboxEndGameInfo.setMinWidth(600);
        vboxEndGameInfo.setMaxWidth(600);



        HBox hboxButtonsBox = new HBox();
        hboxButtonsBox.getChildren().addAll(btnPlayAgain, btnHome, btnClose);
        hboxButtonsBox.setAlignment(Pos.CENTER);
        hboxButtonsBox.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        

        startGamePane.setCenter(vboxEndGameInfo);
        startGamePane.setBottom(hboxButtonsBox);
        
        Scene mainScene = new Scene(startGamePane);
        this.setScene(mainScene);

    }

}