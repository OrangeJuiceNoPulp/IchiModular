package com.example;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TowerChooserStage extends Stage {

    private Tower choice;

    public Tower getChoice() {
        return this.choice;
    }

    public TowerChooserStage(ArrayList<Tower> towerChoices, Game game) {
        super();
        this.choice = null;
        this.setTitle("Choose a Tower");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        GridPane towerChoicePane = new GridPane();

        boolean center = false;
        boolean north = false;
        boolean south = false;
        boolean east = false;
        boolean west = false;

        for (Tower tower : towerChoices) {
            switch (tower.getPosition()) {
                case CENTER:
                    center = false; // TODO fix this. The castle is not meant to be selectable according to the rules (temporary fix in place)
                                    // All references to center in this class should be removed
                    break;
                case EAST:
                    east = true;
                    break;
                case NORTH:
                    north = true;
                    break;
                case SOUTH:
                    south = true;
                    break;
                case WEST:
                    west = true;
                    break;
                default:
                    break;
            }
        }

        Button btnCenter = new Button();
        Button btnNorth = new Button();
        Button btnSouth = new Button();
        Button btnEast = new Button();
        Button btnWest = new Button();

        btnCenter.setMinHeight(150);
        btnCenter.setMinWidth(150);

        btnNorth.setMinHeight(150);
        btnNorth.setMinWidth(150);

        btnSouth.setMinHeight(150);
        btnSouth.setMinWidth(150);

        btnEast.setMinHeight(150);
        btnEast.setMinWidth(150);

        btnWest.setMinHeight(150);
        btnWest.setMinWidth(150);

        if (center) {
            btnCenter.setText("Castle\nOwner: " + game.getTowers().get(0).getOwner().getPlayerName());
            btnCenter.setOnAction(e -> {
                choice = game.getTowers().get(0);
                this.close();
            });
        }
        if (north) {
            btnNorth.setText("Castle\nOwner: " + game.getTowers().get(1).getOwner().getPlayerName());
            btnNorth.setOnAction(e -> {
                choice = game.getTowers().get(1);
                this.close();
            });
        }
        if (south) {
            btnSouth.setText("Castle\nOwner: " + game.getTowers().get(2).getOwner().getPlayerName());
            btnSouth.setOnAction(e -> {
                choice = game.getTowers().get(2);
                this.close();
            });
        }
        if (east) {
            btnEast.setText("Castle\nOwner: " + game.getTowers().get(3).getOwner().getPlayerName());
            btnEast.setOnAction(e -> {
                choice = game.getTowers().get(3);
                this.close();
            });
        }
        if (west) {
            btnWest.setText("Castle\nOwner: " + game.getTowers().get(4).getOwner().getPlayerName());
            btnWest.setOnAction(e -> {
                choice = game.getTowers().get(4);
                this.close();
            });
        }

        towerChoicePane.add(btnCenter, 1, 1);
        towerChoicePane.add(btnNorth, 1, 0);
        towerChoicePane.add(btnSouth, 1, 2);
        towerChoicePane.add(btnEast, 2, 1);
        towerChoicePane.add(btnWest, 0, 1);

        Button cornerButton1 = new Button();
        Button cornerButton2 = new Button();
        Button cornerButton3 = new Button();
        Button cornerButton4 = new Button();

        cornerButton1.setMinHeight(150);
        cornerButton1.setMinWidth(150);

        cornerButton2.setMinHeight(150);
        cornerButton2.setMinWidth(150);

        cornerButton3.setMinHeight(150);
        cornerButton3.setMinWidth(150);

        cornerButton4.setMinHeight(150);
        cornerButton4.setMinWidth(150);

        towerChoicePane.add(cornerButton1, 0, 0);
        towerChoicePane.add(cornerButton2, 0, 2);
        towerChoicePane.add(cornerButton3, 2, 0);
        towerChoicePane.add(cornerButton4, 2, 2);

        Scene mainScene = new Scene(towerChoicePane);
        this.setScene(mainScene);

    }

}
