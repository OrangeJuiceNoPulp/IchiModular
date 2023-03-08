package com.example;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;

public class CardCellFactory implements Callback<ListView<Card>, ListCell<Card>>{
    public static final DataFormat cardFormat =
    new DataFormat("card.custom");
    @Override
    public ListCell<Card> call(ListView<Card> param) {
        ListCell<Card> cell = new ListCell<Card>() {
            @Override
            public void updateItem(Card card, boolean empty) {
                super.updateItem(card, empty);
                if (empty || card == null ) {
                    setText(null);
                    setGraphic(null);
                }
                else {
                    if (card.getGame().getViewingDarkMode()) {
                        CardDisplay display = card.getCardDisplay(true);
                        display.scaleToHand();
                        setGraphic(display);
                        setText(card.getDarkValue().toString());
                    }
                    else {
                        CardDisplay display = card.getCardDisplay(false);
                        display.scaleToHand();
                        setGraphic(display);
                        setText(card.getLightValue().toString());
                    }
                }
            }
        };

        cell.setOnDragDetected(e -> {
            Dragboard db = cell.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.put(cardFormat, cell.getItem().getCardBase());
            db.setContent(content);
            e.consume();
        });
        cell.setOnDragDone(e -> {
            //System.out.println("CellFactory DragDone"); // TODO remove this after debugging
            if (e.getTransferMode() == TransferMode.MOVE) {
            }
            e.consume();
        });
        cell.setOnMouseEntered(e -> {
            param.refresh();
        });
        return cell;
    }

}