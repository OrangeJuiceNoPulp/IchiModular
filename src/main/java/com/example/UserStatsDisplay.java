package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UserStatsDisplay extends StackPane {
    private Player player;

    private Text txtRoundNumber;

    private Text txtPlayerName;
    private Text txtNumCards;
    private Text txtOverallPoints;
    private Text txtRoundPoints;
    private Game game;

    private double inputHeight;
    private double inputWidth;

    private Rectangle background;
    private VBox vboxText;

    public void scaleToHeight(double height) {
        inputHeight = height;
        // inputWidth = height * 2.0;
        
        txtRoundNumber.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtPlayerName.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtNumCards.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtOverallPoints.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtRoundPoints.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));

        txtRoundNumber.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtPlayerName.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtNumCards.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtOverallPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtRoundPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));

        //background.setEffect(new Blend(BlendMode.OVERLAY, new InnerShadow(0.075 * inputWidth, Color.BLACK), new DropShadow(0.075 * inputWidth, Color.BLACK)));
        background.setEffect(new DropShadow(0.075 * inputWidth, Color.BLACK));

        background.setWidth(inputWidth);
        background.setHeight(inputHeight);

        vboxText.setMinSize(inputWidth, inputHeight);
        vboxText.setMaxSize(inputWidth, inputHeight);

        this.setMinSize(inputWidth, inputHeight);
        this.setMaxSize(inputWidth, inputHeight);
    }
    
    public void updateDisplay() {
        if (player == null) {
            player = game.getUser();
        }

        if (player != null) {
            txtRoundNumber.setText("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds());
            txtPlayerName.setText(player.getPlayerName());
            txtNumCards.setText("\tCards: " + player.getHandSize());
            txtOverallPoints.setText("\tGame Points: " + player.getOverallScore());
            txtRoundPoints.setText("\tRound Points: " + player.getScore());
        }

        // txtPlayerName.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        // txtNumCards.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        // txtOverallPoints.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        // txtRoundPoints.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));

        // txtPlayerName.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        // txtNumCards.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        // txtOverallPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        // txtRoundPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        vboxText.setMinSize(inputWidth, inputHeight);
        vboxText.setMaxSize(inputWidth, inputHeight);
        
        this.setMinSize(inputWidth, inputHeight);
        this.setMaxSize(inputWidth, inputHeight);
    }

    public UserStatsDisplay(Game game) {
        this.game = game;

        inputHeight = 100;
        inputWidth = 200;

        txtRoundNumber = new Text("");
        txtRoundNumber.setTextAlignment(TextAlignment.CENTER);
        txtRoundNumber.setUnderline(true);
 

        txtPlayerName = new Text("");
        txtNumCards = new Text("");
        txtOverallPoints = new Text("");
        txtRoundPoints= new Text("");

        vboxText = new VBox();

        background = new Rectangle(inputWidth, inputHeight);
        background.setFill(Color.rgb(32, 32, 32));
        background.setStroke(Color.WHITE);
        //background.setEffect(new Blend(BlendMode.OVERLAY, new InnerShadow(0.075 * inputWidth, Color.BLACK), new DropShadow(0.075 * inputWidth, Color.BLACK)));
        background.setEffect(new DropShadow(0.075 * inputWidth, Color.BLACK));

        background.setWidth(inputWidth);
        background.setHeight(inputHeight);

        txtRoundNumber.setFill(Color.WHITESMOKE);
        txtPlayerName.setFill(Color.WHITESMOKE);
        txtNumCards.setFill(Color.WHITESMOKE);
        txtOverallPoints.setFill(Color.WHITESMOKE);
        txtRoundPoints.setFill(Color.WHITESMOKE);

        txtRoundNumber.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtPlayerName.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtNumCards.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtOverallPoints.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));
        txtRoundPoints.setFont(new Font("Perpetua Bold Italic", 0.15 * inputHeight));

        txtRoundNumber.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtPlayerName.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtNumCards.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtOverallPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtRoundPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));

        VBox vboxTemp = new VBox();
        vboxTemp.getChildren().addAll(txtPlayerName, txtNumCards, txtOverallPoints, txtRoundPoints);
        vboxText.getChildren().addAll(txtRoundNumber, vboxTemp);
        vboxText.setAlignment(Pos.CENTER);

        vboxText.setMinSize(inputWidth, inputHeight);
        vboxText.setMaxSize(inputWidth, inputHeight);
        vboxText.setPadding(new Insets(5, 5, 5, 5));

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(background, vboxText);
    }
}
