package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TowerDisplay {
    private Label lblOwnerName;
    private Image imgCardImage;
    private ImageView imgCardView;
    private VBox vboxTowerDisplay;

    private String ownerName;

    public TowerDisplay() {
        ownerName = "";
        lblOwnerName = new Label("Owner: " + ownerName);
        imgCardImage = new Image("D:/IchiModular-master/IchiModular-master/src/main/resources/com/example/aquaBackground.png", 70, 100, false, false);
        imgCardView = new ImageView(imgCardImage);

        vboxTowerDisplay = new VBox();
        vboxTowerDisplay.getChildren().addAll(lblOwnerName, imgCardView);
        vboxTowerDisplay.setAlignment(Pos.CENTER);
        vboxTowerDisplay.setPadding(new Insets(10, 25, 10, 25));
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        lblOwnerName.setText("Owner: " + ownerName);
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public VBox getTowerDisplay()
    {
        return vboxTowerDisplay;
    }
}
