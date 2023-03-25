package com.example.tableDisplay;

import com.example.BotStatsDisplay;
import com.example.Game;
import com.example.HandDisplay;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TopBotDisplay extends HBox {
    private BotStatsDisplay statsDisplay;
    private HandDisplay handDisplay;
    private double idealHeight;
    private double idealWidth;

    public void refresh() {
        statsDisplay.updateDisplay();
        handDisplay.updateDisplay();
    }

    public TopBotDisplay(Game game, int playerNum) {
        super(50);
        this.setPadding(new Insets(25));
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        statsDisplay = new BotStatsDisplay(game, playerNum);
        handDisplay = new HandDisplay(game, playerNum, game.getImageLoader());
        handDisplay.setRotate(180);
        handDisplay.fitToSize(100, 70);
        this.getChildren().addAll(statsDisplay, handDisplay);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        idealHeight = height;

        double minimumSize = Math.max(Math.min(idealWidth * 0.1, height - 35), 1);

        handDisplay.fitToSize(minimumSize / 0.7, minimumSize);
        statsDisplay.scaleToHeight(minimumSize);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        idealWidth = width;

        double minimumSize = Math.max(Math.min(width * 0.1, idealHeight - 35), 1);

        handDisplay.fitToSize(minimumSize / 0.7, minimumSize);
        statsDisplay.scaleToHeight(minimumSize);

        this.setMinWidth(width);
        this.setMaxWidth(width);
    }
}
