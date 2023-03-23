package com.example;

import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LightColorChooserStage extends Stage {

    private Card.LightColor choice;
    private Random rand;

    public Card.LightColor getChoice() {
        if (this.choice != null) {
            return this.choice;
        }
        // If there is no choice, randomly select a color;
        else {
            int color = rand.nextInt(6);

            if (color == 0) {
                return Card.LightColor.WILD_RED;
            } 
            else if (color == 1) {
                return Card.LightColor.WILD_BLUE;
            }
            else if (color == 2) {
                return Card.LightColor.WILD_GREEN;
            }
            else if (color == 3) {
                return Card.LightColor.WILD_YELLOW;
            }
            else if (color == 4) {
                return Card.LightColor.WILD_PURPLE;
            }
            else {
                return Card.LightColor.WILD_ORANGE;
            }
        }
    }

    public LightColorChooserStage(Random rand) {
        super();
        this.choice = null;
        this.rand = rand;
        this.setTitle("Choose a Color");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        Pane lightColorChoicePane = new Pane();

        Polygon redTriangle = new Polygon();
        Polygon blueTriangle = new Polygon();
        Polygon greenTriangle = new Polygon();
        Polygon yellowTriangle = new Polygon();
        Polygon purpleTriangle = new Polygon();
        Polygon orangeTriangle = new Polygon();

        int scaleFactor = 150;
 
        redTriangle.getPoints().addAll(new Double[]{
            (scaleFactor * 1.0), (scaleFactor * 0.0),
            (scaleFactor * 0.0), (scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 2.0), (scaleFactor * Math.sqrt(3.0))
        });
        blueTriangle.getPoints().addAll(new Double[]{
            (scaleFactor * 1.0), (scaleFactor * 0.0),
            (scaleFactor * 3.0), (scaleFactor * 0.0),
            (scaleFactor * 2.0), (scaleFactor * Math.sqrt(3.0))
        });
        greenTriangle.getPoints().addAll(new Double[]{
            (scaleFactor * 4.0), (scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 3.0), (scaleFactor * 0.0),
            (scaleFactor * 2.0), (scaleFactor * Math.sqrt(3.0))
        });
        yellowTriangle.getPoints().addAll(new Double[]{
            (scaleFactor * 4.0), (scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 3.0), (2 * scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 2.0), (scaleFactor * Math.sqrt(3.0))
        });
        purpleTriangle.getPoints().addAll(new Double[]{
            (scaleFactor * 1.0), (2 * scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 3.0), (2 * scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 2.0), (scaleFactor * Math.sqrt(3.0))
        });
        orangeTriangle.getPoints().addAll(new Double[]{
            (scaleFactor * 1.0), (2 * scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 0.0), (scaleFactor * Math.sqrt(3.0)),
            (scaleFactor * 2.0), (scaleFactor * Math.sqrt(3.0))
        });


        redTriangle.setOnMouseClicked(e -> {
            this.choice = Card.LightColor.WILD_RED;
            this.close();
        });
        blueTriangle.setOnMouseClicked(e -> {
            this.choice = Card.LightColor.WILD_BLUE;
            this.close();
        });
        greenTriangle.setOnMouseClicked(e -> {
            this.choice = Card.LightColor.WILD_GREEN;
            this.close();
        });
        yellowTriangle.setOnMouseClicked(e -> {
            this.choice = Card.LightColor.WILD_YELLOW;
            this.close();
        });
        purpleTriangle.setOnMouseClicked(e -> {
            this.choice = Card.LightColor.WILD_PURPLE;
            this.close();
        });
        orangeTriangle.setOnMouseClicked(e -> {
            this.choice = Card.LightColor.WILD_ORANGE;
            this.close();
        });

        redTriangle.setFill(Color.rgb(255, 0, 0));
        blueTriangle.setFill(Color.rgb(0, 38, 255));
        greenTriangle.setFill(Color.rgb(0, 127, 14));
        yellowTriangle.setFill(Color.rgb(255, 216, 0));
        purpleTriangle.setFill(Color.rgb(87, 0, 127));
        orangeTriangle.setFill(Color.rgb(255, 106, 0));

        redTriangle.setStroke(Color.BLACK);
        blueTriangle.setStroke(Color.BLACK);
        greenTriangle.setStroke(Color.BLACK);
        yellowTriangle.setStroke(Color.BLACK);
        purpleTriangle.setStroke(Color.BLACK);
        orangeTriangle.setStroke(Color.BLACK);

        redTriangle.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        blueTriangle.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        greenTriangle.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        yellowTriangle.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        purpleTriangle.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        orangeTriangle.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));

        Text redText = new Text("Red");
        Text blueText = new Text("Blue");
        Text greenText = new Text("Green");
        Text yellowText = new Text("Yellow");
        Text purpleText = new Text("Purple");
        Text orangeText = new Text("Orange");

        redText.setX((scaleFactor * 0.75));
        redText.setY(((6.0 / 9.0) * scaleFactor * Math.sqrt(3.0)));
        blueText.setX((scaleFactor * 1.70));
        blueText.setY(((4.0 / 9.0) * scaleFactor * Math.sqrt(3.0)));
        greenText.setX((scaleFactor * 2.65));
        greenText.setY(((6.0 / 9.0) * scaleFactor * Math.sqrt(3.0)));
        yellowText.setX((scaleFactor * 2.60));
        yellowText.setY(((1 + (4.0 / 9.0)) * scaleFactor * Math.sqrt(3.0)));
        purpleText.setX((scaleFactor * 1.58));
        purpleText.setY(((1 + (6.0 / 9.0)) * scaleFactor * Math.sqrt(3.0)));
        orangeText.setX((scaleFactor * 0.55));
        orangeText.setY(((1 + (4.0 / 9.0)) * scaleFactor * Math.sqrt(3.0)));

        redText.setFont(new Font("Perpetua Bold Italic", 48));
        blueText.setFont(new Font("Perpetua Bold Italic", 48));
        greenText.setFont(new Font("Perpetua Bold Italic", 48));
        yellowText.setFont(new Font("Perpetua Bold Italic", 48));
        purpleText.setFont(new Font("Perpetua Bold Italic", 48));
        orangeText.setFont(new Font("Perpetua Bold Italic", 48));

        redText.setMouseTransparent(true);
        blueText.setMouseTransparent(true);
        greenText.setMouseTransparent(true);
        yellowText.setMouseTransparent(true);
        purpleText.setMouseTransparent(true);
        orangeText.setMouseTransparent(true);

        redText.setFill(Color.WHITESMOKE);
        blueText.setFill(Color.WHITESMOKE);
        greenText.setFill(Color.WHITESMOKE);
        yellowText.setFill(Color.WHITESMOKE);
        purpleText.setFill(Color.WHITESMOKE);
        orangeText.setFill(Color.WHITESMOKE);

        
        redText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        blueText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        greenText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        yellowText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        purpleText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        orangeText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        lightColorChoicePane.getChildren().addAll(redTriangle, blueTriangle, greenTriangle, yellowTriangle, purpleTriangle, orangeTriangle,
        redText, blueText, greenText, yellowText, purpleText, orangeText);
        lightColorChoicePane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0)))); 


        Scene mainScene = new Scene(lightColorChoicePane);
        this.setScene(mainScene);
    }
    
}
