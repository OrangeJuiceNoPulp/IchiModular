package com.example.tableDisplay;

import com.example.BotStatsDisplay;
import com.example.Game;
import com.example.HandDisplay;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RightBotDisplay extends VBox {
    private BotStatsDisplay statsDisplay;
    private HandDisplay handDisplay;
    private double idealWidth;

    public void refresh() {
        statsDisplay.updateDisplay();
        handDisplay.updateDisplay();
    }

    public RightBotDisplay(Game game, int playerNum) {
        super(50);
        this.setPadding(new Insets(0, 25, 25, 25));
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        statsDisplay = new BotStatsDisplay(game, playerNum);
        handDisplay = new HandDisplay(game, playerNum, game.getImageLoader());
        handDisplay.setRotate(270);
        handDisplay.fitToSize(100, 70);
        this.getChildren().addAll(statsDisplay, handDisplay);
    }

    @Override
    public void setWidth(double width) {
        idealWidth = width;
        super.setWidth(width);
        handDisplay.fitToSize(Math.max((width - 50), 1), Math.max((width - 50), 1) * 0.7);
        statsDisplay.scaleToHeight(Math.max((width - 50), 1) * 0.5);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);

        double minimumSize = Math.max(Math.min(height * (1.0 / 2.0), idealWidth - 50), 1);

        handDisplay.fitToSize(minimumSize, minimumSize * 0.7);
        statsDisplay.scaleToHeight(minimumSize * 0.5);

        this.setMinHeight(height);
        this.setMaxHeight(height);
    }
}
