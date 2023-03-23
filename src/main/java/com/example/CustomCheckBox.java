package com.example;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CustomCheckBox extends HBox {

    private CheckPane checkbox;
    private Text txtLabel;

    public boolean getIsChecked() {
        return checkbox.getIsChecked();
    }
    
    public CustomCheckBox(boolean initialState, String description) {
        super(25);

        checkbox = new CheckPane(initialState);
        txtLabel = new Text(description);
        txtLabel.setFill(Color.WHITESMOKE);
        txtLabel.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        txtLabel.setFont(new Font("Perpetua Bold Italic", 25));

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(checkbox, txtLabel);
    }

    private class CheckPane extends StackPane {
        private Rectangle checkboxBackground;
        private Polyline checkmark;

        private boolean isChecked;

        private double idealHeight;
        private double idealWidth;

        public boolean getIsChecked() {
            return this.isChecked;
        }

        public void checkCheckbox() {
            this.isChecked = !isChecked;
            checkmark.setVisible(isChecked);
        }

        public CheckPane(boolean initialState) {
            super();

            isChecked = initialState;

            checkboxBackground = new Rectangle();
            checkboxBackground.setFill(Color.rgb(128, 128, 128));
            checkboxBackground.setStroke(Color.WHITESMOKE);
            checkboxBackground.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

            checkboxBackground.setOnMouseEntered(e -> {
                checkboxBackground.setFill(Color.rgb(32, 32, 32));
            });
            checkboxBackground.setOnMouseExited(e -> {
                checkboxBackground.setFill(Color.rgb(128, 128, 128));
            });

            checkmark = new Polyline();
            //checkmark.setFill(Color.WHITESMOKE);
            checkmark.setStroke(Color.WHITESMOKE);
            checkmark.setStrokeWidth(2);
            checkmark.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
            checkmark.setMouseTransparent(true);

            checkmark.setVisible(isChecked);
            
            checkboxBackground.setOnMouseClicked(e -> {
                checkCheckbox();
            });

            double size = 30;
            checkboxBackground.setWidth(size);
            checkboxBackground.setHeight(size);

            checkmark.getPoints().clear();
            checkmark.getPoints().addAll(
                (1.0 / 6.0) * size, (3.0 / 6.0) * size,
                (2.5 / 6.0) * size, (4.5 / 6.0) * size,
                (5.0 / 6.0) * size, (1.0 / 6.0) * size);

            checkmark.setStrokeWidth(size * 0.05);


            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(checkboxBackground, checkmark);
        }

        @Override
        public void setHeight(double height) {
            idealHeight = height;

            double size = Math.min(idealHeight, idealWidth);
            checkboxBackground.setWidth(size);
            checkboxBackground.setHeight(size);

            checkmark.getPoints().clear();
            checkmark.getPoints().addAll(
                (1.0 / 6.0) * size, (3.0 / 6.0) * size,
                (2.5 / 6.0) * size, (4.5 / 6.0) * size,
                (5.0 / 6.0) * size, (1.0 / 6.0) * size);

            checkmark.setStrokeWidth(size * 0.05);

            super.setHeight(size);
            super.setWidth(size);
        }

        @Override
        public void setWidth(double width) {
            idealWidth = width;

            double size = Math.min(idealHeight, idealWidth);
            // checkboxBackground.setWidth(size);
            // checkboxBackground.setHeight(size);

            checkmark.getPoints().clear();
            checkmark.getPoints().addAll(
                (1.0 / 6.0) * size, (3.0 / 6.0) * size,
                (2.5 / 6.0) * size, (4.5 / 6.0) * size,
                (5.0 / 6.0) * size, (1.0 / 6.0) * size);

            checkmark.setStrokeWidth(size * 0.05);

            super.setHeight(size);
            super.setWidth(size);
        }
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        checkbox.setMinHeight(height * 0.8);
        checkbox.setMaxHeight(height * 0.8);
        txtLabel.setFont(new Font("Perpetua Bold Italic", 0.75 * height));
    }

    // @Override
    // public void setWidth(double width) {
        
    // }
}
