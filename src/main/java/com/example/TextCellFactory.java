package com.example;

import com.example.Deck.DeckType;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class TextCellFactory implements Callback<ListView<DeckType>, ListCell<DeckType>>{
    @Override
    public ListCell<DeckType> call(ListView<DeckType> param) {
        ListCell<DeckType> cell = new ListCell<DeckType>() {
            @Override
            public void updateItem(DeckType type, boolean empty) {
                super.updateItem(type, empty);

                if (this.isSelected()) {
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(2))));
                }
                else {
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
                }
                
                if (empty || type == null ) {
                    setText(null);
                }
                else {
                    setFont(new Font("Perpetua Bold Italic", 18));
                    setTextFill(Color.WHITESMOKE);
                    setText(type.toString());
                }
            }
        };

        return cell;
    }

}