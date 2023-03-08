package com.example;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerChooserStage extends Stage {

    private Player choice;

    public Player getChoice() {
        return this.choice;
    }

    public PlayerChooserStage(Player choosingPlayer, Game game) {
        super();
        this.choice = null;
        this.setTitle("Choose a Player");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        GridPane towerChoicePane = new GridPane();

        
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

        ArrayList<Button> playerSelectionButtons = new ArrayList<Button>(4);
        playerSelectionButtons.add(btnEast);
        playerSelectionButtons.add(btnNorth);
        playerSelectionButtons.add(btnWest);

        int index = 0;
        for (Player player : game.getPlayers()) {
            if (player.getPlayerNum() != choosingPlayer.getPlayerNum()) {
                playerSelectionButtons.get(index).setText(player.getPlayerName());
                playerSelectionButtons.get(index).setOnAction(e -> {
                    this.choice = player;
                    this.close();
                });
                index++;
            }
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
