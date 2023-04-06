package com.example;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class IntegerCellFactory implements Callback<ListView<Integer>, ListCell<Integer>> {
    @Override
    public ListCell<Integer> call(ListView<Integer> param) {
        ListCell<Integer> cell = new ListCell<Integer>() {
            @Override
            public void updateItem(Integer number, boolean empty) {
                super.updateItem(number, empty);

                if (this.isSelected()) {
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(2))));
                }
                else {
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
                }
                
                if (empty || number == null ) {
                    setText(null);
                }
                else {
                    setFont(new Font("Perpetua Bold Italic", 18));
                    setTextFill(Color.WHITESMOKE);
                    setText(number.toString());
                }
            }
        };

        return cell;
    }
}

