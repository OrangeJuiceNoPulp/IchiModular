package com.example;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class CardCellFactory implements Callback<ListView<Card>, ListCell<Card>>{
    public static final DataFormat cardFormat =
    new DataFormat("card.custom");
    @Override
    public ListCell<Card> call(ListView<Card> param) {
        ListCell<Card> cell = new ListCell<Card>() {
            @Override
            public void updateItem(Card card, boolean empty) {
                if (this.isSelected()) {
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(5), new Insets(2))));
                }
                else {
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
                }
                
                super.updateItem(card, empty);
                if (empty || card == null ) {
                    //setText(null);
                    setGraphic(null);
                }
                else {
                    //setItem(card);
                    if (card.getGame().getViewingDarkMode()) {
                        CardDisplaySquare display = card.getSquareCardDisplay(true);

                        //display.setMinSize(param.getHeight() * 0.49, param.getHeight() * 0.7); //TODO fix if broken //Won't work right if uncommented, leave as is
                        //display.setMaxSize(param.getHeight() * 0.49, param.getHeight() * 0.7);

                        display.scaleToHand(param.getHeight() * 0.7, param.getHeight() * 0.7);
                        //display.setAlignment(Pos.CENTER_LEFT);

                        setGraphic(display);
                        //setText(card.getDarkValue().toString());
                    }
                    else {
                        CardDisplaySquare display = card.getSquareCardDisplay(false);

                        //display.setMinSize(param.getHeight() * 0.49, param.getHeight() * 0.7); //TODO fix if broken //Won't work right if uncommented, leave as is
                        //display.setMaxSize(param.getHeight() * 0.49, param.getHeight() * 0.7);

                        display.scaleToHand(param.getHeight() * 0.7, param.getHeight() * 0.7);
                        //display.setAlignment(Pos.CENTER_LEFT);


                        setGraphic(display);
                        //setText(card.getLightValue().toString());
                    }
                }
            }
        };

        cell.setOnDragDetected(e -> {
            if (param.getFocusModel().getFocusedItem().getGame().getWaitingForUserToTakeTurn()) {
                Dragboard db = cell.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.put(cardFormat, param.getFocusModel().getFocusedItem().getCardBase());
                db.setContent(content);
            }
            
            e.consume();
        });
        cell.setOnDragDone(e -> {
            // if (e.getTransferMode() == TransferMode.MOVE) {
            // }
            e.consume();
        });
        cell.setOnMouseEntered(e -> {
            param.refresh();
        });
        return cell;
    }

}