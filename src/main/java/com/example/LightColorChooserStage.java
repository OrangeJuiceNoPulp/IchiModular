package com.example;

import java.util.Random;

import javafx.scene.Scene;
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

        redTriangle.setFill(Color.RED);
        blueTriangle.setFill(Color.BLUE);
        greenTriangle.setFill(Color.GREEN);
        yellowTriangle.setFill(Color.YELLOW);
        purpleTriangle.setFill(Color.PURPLE.darker());
        orangeTriangle.setFill(Color.ORANGERED.brighter().brighter());

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

        lightColorChoicePane.getChildren().addAll(redTriangle, blueTriangle, greenTriangle, yellowTriangle, purpleTriangle, orangeTriangle,
        redText, blueText, greenText, yellowText, purpleText, orangeText);
        Scene mainScene = new Scene(lightColorChoicePane);
        this.setScene(mainScene);
    }
    
}
