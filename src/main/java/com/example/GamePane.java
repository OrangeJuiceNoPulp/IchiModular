package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GamePane extends StackPane {
    private GameInnerPane gameTable;
    private Pane animationPane;

    public void refreshGameTable() {
        gameTable.refreshPane();
    }

    public Card getSelectedCard() {
        return gameTable.getSelectedCard();
    }

    public GameInnerPane getGameTable() {
        return this.gameTable;
    }

    public GamePane(int numOfRounds, int numOfPlayers) {
        super();
        this.gameTable = new GameInnerPane(numOfRounds, numOfPlayers, this);
        this.animationPane = new Pane();
        //animationPane.setMouseTransparent(true);
        this.getChildren().add(gameTable);
        //this.getChildren().add(animationPane);
        gameTable.getGame().startFirstPlayerTurn();
    }
}
