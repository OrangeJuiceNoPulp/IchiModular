package com.example;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CustomRadioButtonDisplay extends StackPane {
    public static enum Position {
        TOP, CENTER, BOTTOM
    }
    private String strCategory;
    private String strButton1;
    private String strButton2;
    private String strButton3;

    private Text txtCategory;
    private Text txtButton1;
    private Text txtButton2;
    private Text txtButton3;

    private Circle circle1;
    private Circle circle2;
    private Circle circle3;

    private HBox hboxButton1;
    private HBox hboxButton2;
    private HBox hboxButton3;

    private VBox alignmentBox;

    private Timeline buttonAnimation;
    

    private Position selection;

    public void stopAnimation() {
        buttonAnimation.stop();
    }

    private void setButtonAnimation(Circle circle) {
        buttonAnimation.stop();
        buttonAnimation.setCycleCount(Animation.INDEFINITE);
        buttonAnimation.getKeyFrames().clear();
        buttonAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(2500), new KeyValue(circle.fillProperty(), Color.rgb(255, 0, 0), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(5000), new KeyValue(circle.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(7500), new KeyValue(circle.fillProperty(), Color.rgb(0, 38, 255), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(10000), new KeyValue(circle.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(12500), new KeyValue(circle.fillProperty(), Color.rgb(0, 127, 14), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(15000), new KeyValue(circle.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(17500), new KeyValue(circle.fillProperty(), Color.rgb(255, 216, 0), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(20000), new KeyValue(circle.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(22500), new KeyValue(circle.fillProperty(), Color.rgb(255, 106, 0), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(25000), new KeyValue(circle.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(27500), new KeyValue(circle.fillProperty(), Color.rgb(87, 0, 127), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(30000), new KeyValue(circle.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH))
        );
        buttonAnimation.play();
    }

    public void select(Position position) {
        if (position != selection) {
            selection = position;
            switch (selection) {
                case TOP:
                    circle1.setFill(Color.rgb(64, 64, 64));
                    circle2.setFill(Color.rgb(32, 32, 32));
                    circle3.setFill(Color.rgb(32, 32, 32));

                    circle1.setEffect(new Blend(BlendMode.OVERLAY, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
                    circle2.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
                    circle3.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

                    setButtonAnimation(circle1);
                    break;
                case CENTER:
                    circle1.setFill(Color.rgb(32, 32, 32));
                    circle2.setFill(Color.rgb(64, 64, 64));
                    circle3.setFill(Color.rgb(32, 32, 32));
                    
                    circle1.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
                    circle2.setEffect(new Blend(BlendMode.OVERLAY, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
                    circle3.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

                    setButtonAnimation(circle2);
                    break;
                case BOTTOM:
                    circle1.setFill(Color.rgb(32, 32, 32));
                    circle2.setFill(Color.rgb(32, 32, 32));
                    circle3.setFill(Color.rgb(64, 64, 64));
                    
                    circle1.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
                    circle2.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
                    circle3.setEffect(new Blend(BlendMode.OVERLAY, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));

                    setButtonAnimation(circle3);
                    break; 
            }
        }
    }

    public Position getSelection() {
        return this.selection;
    }

    public CustomRadioButtonDisplay(String category, String choice1, String choice2, String choice3, Position initialSelection) {
        super();

        buttonAnimation = new Timeline();
        
        strCategory = category;
        strButton1 = choice1;
        strButton2 = choice2;
        strButton3 = choice3;

        txtCategory = new Text(strCategory);
        txtButton1 = new Text(choice1);
        txtButton2 = new Text(choice2);
        txtButton3 = new Text(choice3);

        txtCategory.setUnderline(true);

        txtCategory.setFill(Color.WHITE);
        txtButton1.setFill(Color.WHITE);
        txtButton2.setFill(Color.WHITE);
        txtButton3.setFill(Color.WHITE);

        txtCategory.setFont(new Font("Perpetua Bold Italic", 12));
        txtButton1.setFont(new Font("Perpetua Bold Italic", 12));
        txtButton2.setFont(new Font("Perpetua Bold Italic", 12));
        txtButton3.setFont(new Font("Perpetua Bold Italic", 12));

        

        txtCategory.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        txtButton1.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        txtButton2.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        txtButton3.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));


        circle1 = new Circle();
        circle2 = new Circle();
        circle3 = new Circle();

        circle1.setOnMouseClicked(e -> {
            select(Position.TOP);
        });
        circle2.setOnMouseClicked(e -> {
            select(Position.CENTER);
        });
        circle3.setOnMouseClicked(e -> {
            select(Position.BOTTOM);
        });
        
        circle1.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        circle2.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        circle3.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        circle1.setStroke(Color.WHITE);
        circle2.setStroke(Color.WHITE);
        circle3.setStroke(Color.WHITE);

        circle1.setFill(Color.rgb(32, 32, 32));
        circle2.setFill(Color.rgb(32, 32, 32));
        circle3.setFill(Color.rgb(32, 32, 32));

        hboxButton1 = new HBox(25);
        hboxButton2 = new HBox(25);
        hboxButton3 = new HBox(25);

        hboxButton1.setAlignment(Pos.CENTER_LEFT);
        hboxButton2.setAlignment(Pos.CENTER_LEFT);
        hboxButton3.setAlignment(Pos.CENTER_LEFT);

        hboxButton1.getChildren().addAll(circle1, txtButton1);
        hboxButton2.getChildren().addAll(circle2, txtButton2);
        hboxButton3.getChildren().addAll(circle3, txtButton3);

        alignmentBox = new VBox(25);
        alignmentBox.getChildren().addAll(txtCategory, hboxButton1, hboxButton2, hboxButton3);
        alignmentBox.setAlignment(Pos.CENTER);

        // HBox alignmentBox2 = new HBox();
        // alignmentBox2.getChildren().addAll(alignmentBox);
        // alignmentBox2.setAlignment(Pos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(alignmentBox);

        this.setPadding(new Insets(0, 25, 0, 25));

        select(initialSelection);

    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);


        hboxButton1.setMinHeight(height * 0.1);
        hboxButton1.setMaxHeight(height * 0.1);
        hboxButton2.setMinHeight(height * 0.1);
        hboxButton2.setMaxHeight(height * 0.1);
        hboxButton3.setMinHeight(height * 0.1);
        hboxButton3.setMaxHeight(height * 0.1);

        circle1.setRadius(hboxButton1.getHeight() * 0.45);
        circle2.setRadius(hboxButton2.getHeight() * 0.45);
        circle3.setRadius(hboxButton3.getHeight() * 0.45);

        txtCategory.setFont(new Font("Perpetua Bold Italic", Math.max(0.15 * height, 1)));
        txtButton1.setFont(new Font("Perpetua Bold Italic", Math.max(hboxButton1.getHeight() * 0.7, 1)));
        txtButton2.setFont(new Font("Perpetua Bold Italic", Math.max(hboxButton1.getHeight() * 0.7, 1)));
        txtButton3.setFont(new Font("Perpetua Bold Italic", Math.max(hboxButton1.getHeight() * 0.7, 1)));


        
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);

        alignmentBox.setMinWidth(width * 0.5);
        alignmentBox.setMaxWidth(width * 0.5);

        hboxButton1.setMinWidth(width * 0.5);
        hboxButton1.setMaxWidth(width * 0.5);
        hboxButton2.setMinWidth(width * 0.5);
        hboxButton2.setMaxWidth(width * 0.5);
        hboxButton3.setMinWidth(width * 0.5);
        hboxButton3.setMaxWidth(width * 0.5);
    }

}
