package com.example;

import com.example.SoundEffectPlayer.SoundEffectType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TowerDisplay extends StackPane {
    private Text lblOwner;
    private Text lblOwnerName;
    private CardDisplay imgCardView;
    private VBox vboxTowerDisplay;
    private Tower tower;

    private String ownerName;

    public TowerDisplay(Tower tower) {
        super();
        this.tower = tower;
        ownerName = "None";
        lblOwner = new Text("Owner:");
    
        lblOwnerName = new Text(ownerName);

        //System.out.println(lblOwner.getBoundsInLocal().getMaxY() + " " + lblOwner.getBoundsInParent().getMinY()); // TODO remove this after debugging

        lblOwner.setFill(Color.WHITESMOKE);
        lblOwnerName.setFill(Color.WHITESMOKE);

        imgCardView = new CardDisplay();
        imgCardView.setRotate(270);
        

        vboxTowerDisplay = new VBox(0);
        vboxTowerDisplay.getChildren().addAll(lblOwner, lblOwnerName, imgCardView);
        vboxTowerDisplay.setAlignment(Pos.CENTER);
        scale(150);
        this.getChildren().addAll(vboxTowerDisplay);

        this.setOnDragDropped(e -> {
                // System.out.println("Discard DragDone"); // TODO remove this after debugging
                Dragboard db = e.getDragboard();
                boolean dropSuccess = false;
                boolean turnSuccess = false;
    
                if (tower.getGame().getWaitingForUserToTakeTurn()) {
                    tower.getGame().setWaitingForUserToTakeTurn(false);
                    if (db.hasContent(CardCellFactory.cardFormat)) {
                        if (db.getContent(CardCellFactory.cardFormat) instanceof CardBase) {
        
                            CardBase draggedCard = (CardBase) db.getContent(CardCellFactory.cardFormat);
                            Card selectedCard = tower.getGame().getSelectedCard();
        
                            //System.out.println(draggedCard.isTheSameCard(selectedCard)); // TODO remove
                            // this after debugging
        
                            if (draggedCard.isTheSameCard(selectedCard)) {
                                turnSuccess = tower.getGame().playCard(tower.getGame().getUser(), tower, selectedCard);
                            }
                        }
        
                        if (turnSuccess) {
                            tower.getGame().getUser().endTurn();
                            //tower.getGame().refreshGamePane();
                        }
                        else {
                            tower.getGame().getGamePane().playSoundEffect(SoundEffectType.ERROR);
                            tower.getGame().setWaitingForUserToTakeTurn(true);
                        }
                        dropSuccess = true;
                    }
                    else {
                        tower.getGame().setWaitingForUserToTakeTurn(true);
                    }
                }
                e.setDropCompleted(dropSuccess);
                e.consume();
            });

        this.setOnDragOver(e -> {
                if (e.getGestureSource() != this && e.getDragboard().hasContent(CardCellFactory.cardFormat)) {
                    e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
            });
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        lblOwnerName.setText(ownerName);
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void updateTowerInformation(double size) {
        imgCardView.updateDisplay(tower.getDisplayedCard());

        //imgCardView.scaleToHand(0.7 * size, size); //TODO fix if broken

        imgCardView.setMaxHeight(0.7 * size);
        imgCardView.setMinHeight(0.7 * size);
        imgCardView.setEffect(new DropShadow(0.10 * size, Color.BLACK));

        imgCardView.setMaxWidth(size);
        imgCardView.setMinWidth(size);

        if (tower.getIsOwned()) {
            ownerName = tower.getOwner().getPlayerName();
        }
        else {
            ownerName = "None";
        }
        lblOwnerName.setText(ownerName);
        scale(size);
    }
    public void updateTowerInformation() {
        imgCardView.updateDisplay(tower.getDisplayedCard());

        if (tower.getIsOwned()) {
            ownerName = tower.getOwner().getPlayerName();
        }
        else {
            ownerName = "None";
        }
        lblOwnerName.setText(ownerName);
        //scale(150);
    }

    public void scale(double size) {
        
        lblOwner.setFont(new Font("Perpetua Bold Italic", 0.1125 * size));
        lblOwnerName.setFont(new Font("Perpetua Bold Italic", 0.1125 * size));

        lblOwner.setEffect(new DropShadow(0.15 * size, Color.BLACK));
        lblOwnerName.setEffect(new DropShadow(0.15 * size, Color.BLACK));
        

        //imgCardView.scaleToHand(0.7 * size, size); //TODO fix if broken 


        imgCardView.setMaxHeight(0.7 * size);
        imgCardView.setMinHeight(0.7 * size);
        imgCardView.setEffect(new DropShadow(0.10 * size, Color.BLACK));

        imgCardView.setMaxWidth(size);
        imgCardView.setMinWidth(size);

        vboxTowerDisplay.setMaxWidth(size);
        vboxTowerDisplay.setMinWidth(size);
        
        vboxTowerDisplay.setMaxHeight(size);
        vboxTowerDisplay.setMinHeight(size);
    }

    public void resize(double height, double width) {
        super.resize(Math.min(height, width), Math.min(height, width));
        scale(Math.min(height, width));
    }
    
}
