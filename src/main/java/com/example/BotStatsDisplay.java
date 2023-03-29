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

public class BotStatsDisplay extends StackPane {
    private Player player;
    private Text txtPlayerName;
    private Text txtNumCards;
    private Text txtOverallPoints;
    private Text txtRoundPoints;
    private Game game;
    private int playerNum;

    private double inputHeight;
    private double inputWidth;

    private Rectangle background;
    private VBox vboxText;

    public void clear() {
        this.getChildren().clear();
        this.background = null;
        this.vboxText = null;
        this.game = null;
    }

    public Rectangle getBackgroundRectangle() {
        return background;
    }

    public Player getPlayer() {
        return this.player;
    }
 
    public void scaleToHeight(double height) {
        inputHeight = height;
        inputWidth = height * 2.0;
        
        txtPlayerName.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        txtNumCards.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        txtOverallPoints.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        txtRoundPoints.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));

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
            player = game.getPlayer(playerNum);
        }

        if (player != null) {
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
        // vboxText.setMinSize(inputWidth, inputHeight);
        // vboxText.setMaxSize(inputWidth, inputHeight);
        
        // this.setMinSize(inputWidth, inputHeight);
        // this.setMaxSize(inputWidth, inputHeight);
    }

    public BotStatsDisplay(Game game, int playerNum) {
        this.game = game;
        this.playerNum = playerNum;

        inputHeight = 75;
        inputWidth = 150;

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

        txtPlayerName.setFill(Color.WHITESMOKE);
        txtNumCards.setFill(Color.WHITESMOKE);
        txtOverallPoints.setFill(Color.WHITESMOKE);
        txtRoundPoints.setFill(Color.WHITESMOKE);

        txtPlayerName.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        txtNumCards.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        txtOverallPoints.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));
        txtRoundPoints.setFont(new Font("Perpetua Bold Italic", 0.1875 * inputHeight));

        txtPlayerName.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtNumCards.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtOverallPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));
        txtRoundPoints.setEffect(new DropShadow(0.2 * inputWidth, Color.BLACK));

        vboxText.getChildren().addAll(txtPlayerName, txtNumCards, txtOverallPoints, txtRoundPoints);

        vboxText.setMinSize(inputWidth, inputHeight);
        vboxText.setMaxSize(inputWidth, inputHeight);
        vboxText.setPadding(new Insets(5, 5, 5, 5));

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(background, vboxText);
    }
}
