package com.example;

import javafx.scene.layout.GridPane;

public class TowerPane extends GridPane {
    private Game game;

    private TowerDisplay castleTower;
    private TowerDisplay northTower;
    private TowerDisplay southTower;
    private TowerDisplay eastTower;
    private TowerDisplay westTower;

    public void refresh() {
        for (Tower tower : game.getTowers()) {
            tower.refreshDisplay();
        }
    }

    public TowerPane(Game game) {
        this.game = game;

        castleTower = new TowerDisplay();
        northTower = new TowerDisplay();
        southTower = new TowerDisplay();
        eastTower = new TowerDisplay();
        westTower = new TowerDisplay();

        // this.add(game.getTowers().get(0).getTowerDisplay(), 1, 1);
        // this.add(game.getTowers().get(1).getTowerDisplay(), 1, 0);
        // this.add(game.getTowers().get(2).getTowerDisplay(), 1, 2);
        // this.add(game.getTowers().get(3).getTowerDisplay(), 2, 1);
        // this.add(game.getTowers().get(4).getTowerDisplay(), 0, 1);

        this.add(castleTower.getTowerDisplay(), 1, 1);
        this.add(northTower.getTowerDisplay(), 0, 1);
        this.add(southTower.getTowerDisplay(), 2, 1);
        this.add(eastTower.getTowerDisplay(), 1, 2);
        this.add(westTower.getTowerDisplay(), 1, 0);

        this.refresh();
    }
}
