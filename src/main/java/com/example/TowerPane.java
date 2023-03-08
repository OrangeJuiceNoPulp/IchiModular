package com.example;

import javafx.scene.layout.GridPane;

public class TowerPane extends GridPane {
    private Game game;

    public void refresh() {
        for (Tower tower : game.getTowers()) {
            tower.refreshDisplay();
        }
    }

    public TowerPane(Game game) {
        this.game = game;

        this.add(game.getTowers().get(0).getTowerDisplay(), 1, 1);
        this.add(game.getTowers().get(1).getTowerDisplay(), 1, 0);
        this.add(game.getTowers().get(2).getTowerDisplay(), 1, 2);
        this.add(game.getTowers().get(3).getTowerDisplay(), 2, 1);
        this.add(game.getTowers().get(4).getTowerDisplay(), 0, 1);

        this.refresh();
    }
}
