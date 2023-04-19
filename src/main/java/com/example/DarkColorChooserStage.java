package com.example;

import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
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

public class DarkColorChooserStage extends Stage {

    private Card.DarkColor choice;
    private Random rand;

    public Card.DarkColor getChoice() {
        if (this.choice != null) {
            return this.choice;
        }
        // If there is no choice, randomly select a color;
        else {
            int color = rand.nextInt(4);

            if (color == 0) {
                return Card.DarkColor.WILD_AQUA;
            } 
            else if (color == 1) {
                return Card.DarkColor.WILD_LIME;
            }
            else if (color == 2) {
                return Card.DarkColor.WILD_MAGENTA;
            }
            else {
                return Card.DarkColor.WILD_CURRANT;
            }
        }
    }

    public DarkColorChooserStage(Random rand) {
        super();
        this.choice = null;
        this.rand = rand;
        this.setTitle("Choose a Color");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.getIcons().add(new Image(this.getClass().getResource("cardBackSquare.png").toExternalForm()));

        Pane darkColorChoicePane = new Pane();

        Polygon aquaDiamond = new Polygon();
        Polygon limeDiamond = new Polygon();
        Polygon magentaDiamond = new Polygon();
        Polygon currantDiamond = new Polygon();

        int scaleFactor = 250;
 
        aquaDiamond.getPoints().addAll(new Double[]{
            (scaleFactor * 0.5), (scaleFactor * 0.5),
            (scaleFactor * 1.0), (scaleFactor * 0.0),
            (scaleFactor * 1.5), (scaleFactor * 0.5),
            (scaleFactor * 1.0), (scaleFactor * 1.0)
        });
        limeDiamond.getPoints().addAll(new Double[]{
            (scaleFactor * 1.5), (scaleFactor * 1.5),
            (scaleFactor * 2.0), (scaleFactor * 1.0),
            (scaleFactor * 1.5), (scaleFactor * 0.5),
            (scaleFactor * 1.0), (scaleFactor * 1.0)
        });
        magentaDiamond.getPoints().addAll(new Double[]{
            (scaleFactor * 0.5), (scaleFactor * 1.5),
            (scaleFactor * 1.0), (scaleFactor * 2.0),
            (scaleFactor * 1.5), (scaleFactor * 1.5),
            (scaleFactor * 1.0), (scaleFactor * 1.0)
        });
        currantDiamond.getPoints().addAll(new Double[]{
            (scaleFactor * 0.5), (scaleFactor * 0.5),
            (scaleFactor * 0.0), (scaleFactor * 1.0),
            (scaleFactor * 0.5), (scaleFactor * 1.5),
            (scaleFactor * 1.0), (scaleFactor * 1.0)
        });


        aquaDiamond.setOnMouseClicked(e -> {
            this.choice = Card.DarkColor.WILD_AQUA;
            this.close();
        });
        limeDiamond.setOnMouseClicked(e -> {
            this.choice = Card.DarkColor.WILD_LIME;
            this.close();
        });
        magentaDiamond.setOnMouseClicked(e -> {
            this.choice = Card.DarkColor.WILD_MAGENTA;
            this.close();
        });
        currantDiamond.setOnMouseClicked(e -> {
            this.choice = Card.DarkColor.WILD_CURRANT;
            this.close();
        });
        

        aquaDiamond.setFill(Color.rgb(0, 255, 255));
        limeDiamond.setFill(Color.rgb(76, 255, 0));
        magentaDiamond.setFill(Color.rgb(255, 0, 220));
        currantDiamond.setFill(Color.rgb(127, 0, 0));

        aquaDiamond.setStroke(Color.BLACK);
        limeDiamond.setStroke(Color.BLACK);
        magentaDiamond.setStroke(Color.BLACK);
        currantDiamond.setStroke(Color.BLACK);
        
        aquaDiamond.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        limeDiamond.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        magentaDiamond.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));
        currantDiamond.setEffect(new Blend(BlendMode.DARKEN, new InnerShadow(0.2 * 1000 * 0.125, Color.BLACK), new DropShadow(0.2 * 1000 * 0.125, Color.BLACK)));


        Text aquaText = new Text("Aqua");
        Text limeText = new Text("Lime");
        Text magentaText = new Text("Magenta");
        Text currantText = new Text("Currant");


        aquaText.setX((scaleFactor * 0.79));
        aquaText.setY((scaleFactor * 0.55));
        limeText.setX((scaleFactor * 1.32));
        limeText.setY((scaleFactor * 1.05));
        magentaText.setX((scaleFactor * 0.65));
        magentaText.setY((scaleFactor * 1.55));
        currantText.setX((scaleFactor * 0.16));
        currantText.setY((scaleFactor * 1.05));
        

        aquaText.setFont(new Font("Perpetua Bold Italic", 48));
        limeText.setFont(new Font("Perpetua Bold Italic", 48));
        magentaText.setFont(new Font("Perpetua Bold Italic", 48));
        currantText.setFont(new Font("Perpetua Bold Italic", 48));
        

        aquaText.setMouseTransparent(true);
        limeText.setMouseTransparent(true);
        magentaText.setMouseTransparent(true);
        currantText.setMouseTransparent(true);

        aquaText.setFill(Color.WHITESMOKE);
        limeText.setFill(Color.WHITESMOKE);
        magentaText.setFill(Color.WHITESMOKE);
        currantText.setFill(Color.WHITESMOKE);

        aquaText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        limeText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        magentaText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        currantText.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        

        darkColorChoicePane.getChildren().addAll(aquaDiamond, limeDiamond, magentaDiamond, currantDiamond, aquaText, limeText, magentaText, currantText);
        darkColorChoicePane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0)))); 
        darkColorChoicePane.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Scene mainScene = new Scene(darkColorChoicePane);
        this.setScene(mainScene);
    }
    
}
