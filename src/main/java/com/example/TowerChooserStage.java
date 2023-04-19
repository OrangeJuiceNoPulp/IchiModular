package com.example;

import java.util.ArrayList;

import com.example.SoundEffectPlayer.SoundEffectType;
import com.example.Tower.TowerPosition;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TowerChooserStage extends Stage {

    private boolean center;
    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    private Tower choice;


    private TowerBackgroundPane towerBackgroundPane;
    private TowerCardPane towerCardPane;

    public void stopAnimation() {
        if (towerBackgroundPane != null) {
            if (towerBackgroundPane.backgroundAnimation != null) {
                towerBackgroundPane.backgroundAnimation.stop();
            }
        }
    }

    public Tower getChoice() {
        return this.choice;
    }

    public void selectTower(TowerPosition position, Game game) {
        towerBackgroundPane.selectTower(position, game);
    }

    public TowerChooserStage(ArrayList<Tower> towerChoices, Game game) {
        super();
        this.choice = null;
        this.setTitle("Choose a Tower");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.getIcons().add(new Image(this.getClass().getResource("cardBackSquare.png").toExternalForm()));


        //GridPane towerChoicePane = new GridPane();

        center = false;
        north = false;
        south = false;
        east = false;
        west = false;

        for (Tower tower : towerChoices) {
            switch (tower.getPosition()) {
                case CENTER:
                    center = false; // TODO fix this. The castle is not meant to be selectable according to the rules (temporary fix in place)
                                    // All references to center in this class should be removed
                    break;
                case EAST:
                    east = true;
                    break;
                case NORTH:
                    north = true;
                    break;
                case SOUTH:
                    south = true;
                    break;
                case WEST:
                    west = true;
                    break;
                default:
                    break;
            }
        }

        // Button btnCenter = new Button();
        // Button btnNorth = new Button();
        // Button btnSouth = new Button();
        // Button btnEast = new Button();
        // Button btnWest = new Button();

        // btnCenter.setMinHeight(150);
        // btnCenter.setMinWidth(150);

        // btnNorth.setMinHeight(150);
        // btnNorth.setMinWidth(150);

        // btnSouth.setMinHeight(150);
        // btnSouth.setMinWidth(150);

        // btnEast.setMinHeight(150);
        // btnEast.setMinWidth(150);

        // btnWest.setMinHeight(150);
        // btnWest.setMinWidth(150);

        // if (center) {
        //     btnCenter.setText("Castle\nOwner: " + game.getTowers().get(0).getOwner().getPlayerName());
        //     btnCenter.setOnAction(e -> {
        //         choice = game.getTowers().get(0);
        //         this.close();
        //     });
        // }
        // if (north) {
        //     btnNorth.setText("Castle\nOwner: " + game.getTowers().get(1).getOwner().getPlayerName());
        //     btnNorth.setOnAction(e -> {
        //         choice = game.getTowers().get(1);
        //         this.close();
        //     });
        // }
        // if (south) {
        //     btnSouth.setText("Castle\nOwner: " + game.getTowers().get(2).getOwner().getPlayerName());
        //     btnSouth.setOnAction(e -> {
        //         choice = game.getTowers().get(2);
        //         this.close();
        //     });
        // }
        // if (east) {
        //     btnEast.setText("Castle\nOwner: " + game.getTowers().get(3).getOwner().getPlayerName());
        //     btnEast.setOnAction(e -> {
        //         choice = game.getTowers().get(3);
        //         this.close();
        //     });
        // }
        // if (west) {
        //     btnWest.setText("Castle\nOwner: " + game.getTowers().get(4).getOwner().getPlayerName());
        //     btnWest.setOnAction(e -> {
        //         choice = game.getTowers().get(4);
        //         this.close();
        //     });
        // }

        // towerChoicePane.add(btnCenter, 1, 1);
        // towerChoicePane.add(btnNorth, 1, 0);
        // towerChoicePane.add(btnSouth, 1, 2);
        // towerChoicePane.add(btnEast, 2, 1);
        // towerChoicePane.add(btnWest, 0, 1);

        // Button cornerButton1 = new Button();
        // Button cornerButton2 = new Button();
        // Button cornerButton3 = new Button();
        // Button cornerButton4 = new Button();

        // cornerButton1.setMinHeight(150);
        // cornerButton1.setMinWidth(150);

        // cornerButton2.setMinHeight(150);
        // cornerButton2.setMinWidth(150);

        // cornerButton3.setMinHeight(150);
        // cornerButton3.setMinWidth(150);

        // cornerButton4.setMinHeight(150);
        // cornerButton4.setMinWidth(150);

        // towerChoicePane.add(cornerButton1, 0, 0);
        // towerChoicePane.add(cornerButton2, 0, 2);
        // towerChoicePane.add(cornerButton3, 2, 0);
        // towerChoicePane.add(cornerButton4, 2, 2);

        Button btnConfirmSelection = new Button("Confirm Selection");
        btnConfirmSelection.setMinSize(550, 100);
        btnConfirmSelection.setMaxSize(550, 100);
        btnConfirmSelection.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnConfirmSelection.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnConfirmSelection.setTextFill(Color.WHITESMOKE);
        btnConfirmSelection.setFont(new Font("Perpetua Bold Italic", 25));
        btnConfirmSelection.setOnMouseEntered(e -> {
            btnConfirmSelection.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnConfirmSelection.setOnMouseExited(e -> {
            btnConfirmSelection.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

        btnConfirmSelection.setOnAction(e -> {
            this.stopAnimation();
            this.close();
        });

        this.setOnCloseRequest(e -> {
            this.stopAnimation();
            this.close();
        });



        towerBackgroundPane = new TowerBackgroundPane(500, game);
        towerCardPane = new TowerCardPane(500, towerBackgroundPane, game);
        

        for (Tower tower : towerChoices) {
            switch (tower.getPosition()) {
                case CENTER:
                    // TODO fix this. The castle is not meant to be selectable according to the rules (temporary fix in place)
                    // All references to center in this class should be removed
                    //selectTower(TowerPosition.CENTER);
                    break;
                case EAST:
                    selectTower(TowerPosition.EAST, game);
                    break;
                case NORTH:
                    selectTower(TowerPosition.NORTH, game);
                    break;
                case SOUTH:
                    selectTower(TowerPosition.SOUTH, game);
                    break;
                case WEST:
                    selectTower(TowerPosition.WEST, game);
                    break;
                default:
                    choice = null;
                    break;
            }
        }


        StackPane combineTowerDisplaysPane = new StackPane();
        combineTowerDisplaysPane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        combineTowerDisplaysPane.getChildren().addAll(towerBackgroundPane, towerCardPane);
        combineTowerDisplaysPane.setPadding(new Insets(25));

        towerCardPane.refresh();
        

        VBox vboxSelection = new VBox();
        vboxSelection.getChildren().addAll(combineTowerDisplaysPane, btnConfirmSelection);

        Scene mainScene = new Scene(vboxSelection);
        this.setScene(mainScene);

    }



    
    private class TowerBackgroundPane extends Pane {
        private Polygon castleBackground;
        private Polygon northBackground;
        private Polygon southBackground;
        private Polygon eastBackground;
        private Polygon westBackground;
        private Timeline backgroundAnimation;

        private void setBackgroundAnimation(Polygon background) {
            backgroundAnimation.stop();
            backgroundAnimation.setCycleCount(Animation.INDEFINITE);
            backgroundAnimation.getKeyFrames().clear();
            backgroundAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(2500), new KeyValue(background.fillProperty(), Color.rgb(255, 0, 0), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(5000), new KeyValue(background.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(7500), new KeyValue(background.fillProperty(), Color.rgb(0, 38, 255), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(10000), new KeyValue(background.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(12500), new KeyValue(background.fillProperty(), Color.rgb(0, 127, 14), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(15000), new KeyValue(background.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(17500), new KeyValue(background.fillProperty(), Color.rgb(255, 216, 0), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(20000), new KeyValue(background.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(22500), new KeyValue(background.fillProperty(), Color.rgb(255, 106, 0), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(25000), new KeyValue(background.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(27500), new KeyValue(background.fillProperty(), Color.rgb(87, 0, 127), Interpolator.EASE_OUT)),
                new KeyFrame(Duration.millis(30000), new KeyValue(background.fillProperty(), Color.rgb(64, 64, 64), Interpolator.EASE_BOTH))
            );
            backgroundAnimation.play();
        }

        public void selectTower(TowerPosition position, Game game) {

            //game.setSelectedTowerPosition(position); //TODO selectTowerhere

            switch(position) {
                case CENTER:
                choice = game.getCastle();
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(castleBackground);
                    break;
                case EAST:
                choice = game.getTowers().get(3);
                castleBackground.setFill(Color.rgb(64, 64, 64));
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(eastBackground);
                    break;
                case NORTH:
                choice = game.getTowers().get(1);
                castleBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(northBackground);
                    break;
                case SOUTH:
                choice = game.getTowers().get(2);
                castleBackground.setFill(Color.rgb(64, 64, 64));
                northBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(southBackground);
                    break;
                case WEST:
                choice = game.getTowers().get(4);
                castleBackground.setFill(Color.rgb(64, 64, 64));
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(westBackground);
                    break;
                default:
                choice = null;
                castleBackground.setFill(Color.rgb(64, 64, 64));
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                    break;

            }
        }

        public TowerBackgroundPane(double size, Game game) {
            super();
            super.setHeight(size);
            super.setWidth(size);
            castleBackground = new Polygon();
            northBackground = new Polygon();
            southBackground = new Polygon();
            eastBackground = new Polygon();
            westBackground = new Polygon();

            backgroundAnimation = new Timeline();

            castleBackground.getPoints().addAll(
                (11.5 / 34.0) * size, (11.5 / 34.0) * size,
                (11.5 / 34.0) * size, (22.5 / 34.0) * size,
                (22.5 / 34.0) * size, (22.5 / 34.0) * size,
                (22.5 / 34.0) * size, (11.5 / 34.0) * size
            );

            northBackground.getPoints().addAll(
                (11.5 / 34.0) * size, (0.5 / 34.0) * size,
                (6.0 / 34.0) * size, (6.0 / 34.0) * size,
                (11.5 / 34.0) * size, (11.5 / 34.0) * size,
                (22.5 / 34.0) * size, (11.5 / 34.0) * size,
                (28.0 / 34.0) * size, (6.0 / 34.0) * size,
                (22.5 / 34.0) * size, (0.5 / 34.0) * size
            );
            southBackground.getPoints().addAll(
                (11.5 / 34.0) * size, (33.5 / 34.0) * size,
                (6.0 / 34.0) * size, (28.0 / 34.0) * size,
                (11.5 / 34.0) * size, (22.5 / 34.0) * size,
                (22.5 / 34.0) * size, (22.5 / 34.0) * size,
                (28.0 / 34.0) * size, (28.0 / 34.0) * size,
                (22.5 / 34.0) * size, (33.5 / 34.0) * size
            );

            eastBackground.getPoints().addAll(
                (0.5 / 34.0) * size, (11.5 / 34.0) * size,
                (6.0 / 34.0) * size, (6.0 / 34.0) * size,
                (11.5 / 34.0) * size, (11.5 / 34.0) * size,
                (11.5 / 34.0) * size, (22.5 / 34.0) * size,
                (6.0 / 34.0) * size, (28.0 / 34.0) * size,
                (0.5 / 34.0) * size, (22.5 / 34.0) * size
            );
            westBackground.getPoints().addAll(
                (33.5 / 34.0) * size, (11.5 / 34.0) * size,
                (28.0 / 34.0) * size, (6.0 / 34.0) * size,
                (22.5 / 34.0) * size, (11.5 / 34.0) * size,
                (22.5 / 34.0) * size, (22.5 / 34.0) * size,
                (28.0 / 34.0) * size, (28.0 / 34.0) * size,
                (33.5 / 34.0) * size, (22.5 / 34.0) * size
            );

            castleBackground.setFill(Color.rgb(64,64,64));
            northBackground.setFill(Color.rgb(64,64,64));
            southBackground.setFill(Color.rgb(64,64,64));
            eastBackground.setFill(Color.rgb(64,64,64));
            westBackground.setFill(Color.rgb(64,64,64));

            

            castleBackground.setStroke(Color.BLACK);
            northBackground.setStroke(Color.BLACK);
            southBackground.setStroke(Color.BLACK);
            eastBackground.setStroke(Color.BLACK);
            westBackground.setStroke(Color.BLACK);

            Blend blendEffect = new Blend(BlendMode.OVERLAY, new InnerShadow(0.075 * size, Color.BLACK), new DropShadow(0.075 * size, Color.BLACK));

            castleBackground.setEffect(blendEffect);
            northBackground.setEffect(blendEffect);
            southBackground.setEffect(blendEffect);
            eastBackground.setEffect(blendEffect);
            westBackground.setEffect(blendEffect);
            
            selectTower(TowerPosition.CENTER, game);
            
            this.getChildren().addAll(castleBackground, northBackground, southBackground, eastBackground, westBackground);

        }

        // @Override
        // public void setHeight(double height) {
        //     this.setMinWidth(height);
        //     this.setMinHeight(height);
        //     //double height = Math.min(input, this.getWidth());
        //     super.setHeight(height);
        //     super.setWidth(height);

        //     castleBackground.getPoints().clear();
        //     northBackground.getPoints().clear();
        //     southBackground.getPoints().clear();
        //     eastBackground.getPoints().clear();
        //     westBackground.getPoints().clear();

        //     castleBackground.getPoints().addAll(
        //         (11.5 / 34.0) * height, (11.5 / 34.0) * height,
        //         (11.5 / 34.0) * height, (22.5 / 34.0) * height,
        //         (22.5 / 34.0) * height, (22.5 / 34.0) * height,
        //         (22.5 / 34.0) * height, (11.5 / 34.0) * height
        //     );

        //     northBackground.getPoints().addAll(
        //         (11.5 / 34.0) * height, (0.5 / 34.0) * height,
        //         (6.0 / 34.0) * height, (6.0 / 34.0) * height,
        //         (11.5 / 34.0) * height, (11.5 / 34.0) * height,
        //         (22.5 / 34.0) * height, (11.5 / 34.0) * height,
        //         (28.0 / 34.0) * height, (6.0 / 34.0) * height,
        //         (22.5 / 34.0) * height, (0.5 / 34.0) * height
        //     );
        //     southBackground.getPoints().addAll(
        //         (11.5 / 34.0) * height, (33.5 / 34.0) * height,
        //         (6.0 / 34.0) * height, (28.0 / 34.0) * height,
        //         (11.5 / 34.0) * height, (22.5 / 34.0) * height,
        //         (22.5 / 34.0) * height, (22.5 / 34.0) * height,
        //         (28.0 / 34.0) * height, (28.0 / 34.0) * height,
        //         (22.5 / 34.0) * height, (33.5 / 34.0) * height
        //     );

        //     eastBackground.getPoints().addAll(
        //         (0.5 / 34.0) * height, (11.5 / 34.0) * height,
        //         (6.0 / 34.0) * height, (6.0 / 34.0) * height,
        //         (11.5 / 34.0) * height, (11.5 / 34.0) * height,
        //         (11.5 / 34.0) * height, (22.5 / 34.0) * height,
        //         (6.0 / 34.0) * height, (28.0 / 34.0) * height,
        //         (0.5 / 34.0) * height, (22.5 / 34.0) * height
        //     );
        //     westBackground.getPoints().addAll(
        //         (33.5 / 34.0) * height, (11.5 / 34.0) * height,
        //         (28.0 / 34.0) * height, (6.0 / 34.0) * height,
        //         (22.5 / 34.0) * height, (11.5 / 34.0) * height,
        //         (22.5 / 34.0) * height, (22.5 / 34.0) * height,
        //         (28.0 / 34.0) * height, (28.0 / 34.0) * height,
        //         (33.5 / 34.0) * height, (22.5 / 34.0) * height
        //     );

        //     Blend blendEffect = new Blend(BlendMode.OVERLAY, new InnerShadow(0.075 * height, Color.BLACK), new DropShadow(0.075 * height, Color.BLACK));

        //     castleBackground.setEffect(blendEffect);
        //     northBackground.setEffect(blendEffect);
        //     southBackground.setEffect(blendEffect);
        //     eastBackground.setEffect(blendEffect);
        //     westBackground.setEffect(blendEffect);
        // }

        // @Override
        // public void setWidth(double width) {
        //     this.setMinWidth(width);
        //     this.setMinHeight(width);
        //     //double width = Math.min(input, this.getHeight());
        //     super.setHeight(width);
        //     super.setWidth(width);

        //     castleBackground.getPoints().clear();
        //     northBackground.getPoints().clear();
        //     southBackground.getPoints().clear();
        //     eastBackground.getPoints().clear();
        //     westBackground.getPoints().clear();

        //     castleBackground.getPoints().addAll(
        //         (11.5 / 34.0) * width, (11.5 / 34.0) * width,
        //         (11.5 / 34.0) * width, (22.5 / 34.0) * width,
        //         (22.5 / 34.0) * width, (22.5 / 34.0) * width,
        //         (22.5 / 34.0) * width, (11.5 / 34.0) * width
        //     );

        //     northBackground.getPoints().addAll(
        //         (11.5 / 34.0) * width, (0.5 / 34.0) * width,
        //         (6.0 / 34.0) * width, (6.0 / 34.0) * width,
        //         (11.5 / 34.0) * width, (11.5 / 34.0) * width,
        //         (22.5 / 34.0) * width, (11.5 / 34.0) * width,
        //         (28.0 / 34.0) * width, (6.0 / 34.0) * width,
        //         (22.5 / 34.0) * width, (0.5 / 34.0) * width
        //     );
        //     southBackground.getPoints().addAll(
        //         (11.5 / 34.0) * width, (33.5 / 34.0) * width,
        //         (6.0 / 34.0) * width, (28.0 / 34.0) * width,
        //         (11.5 / 34.0) * width, (22.5 / 34.0) * width,
        //         (22.5 / 34.0) * width, (22.5 / 34.0) * width,
        //         (28.0 / 34.0) * width, (28.0 / 34.0) * width,
        //         (22.5 / 34.0) * width, (33.5 / 34.0) * width
        //     );

        //     eastBackground.getPoints().addAll(
        //         (0.5 / 34.0) * width, (11.5 / 34.0) * width,
        //         (6.0 / 34.0) * width, (6.0 / 34.0) * width,
        //         (11.5 / 34.0) * width, (11.5 / 34.0) * width,
        //         (11.5 / 34.0) * width, (22.5 / 34.0) * width,
        //         (6.0 / 34.0) * width, (28.0 / 34.0) * width,
        //         (0.5 / 34.0) * width, (22.5 / 34.0) * width
        //     );
        //     westBackground.getPoints().addAll(
        //         (33.5 / 34.0) * width, (11.5 / 34.0) * width,
        //         (28.0 / 34.0) * width, (6.0 / 34.0) * width,
        //         (22.5 / 34.0) * width, (11.5 / 34.0) * width,
        //         (22.5 / 34.0) * width, (22.5 / 34.0) * width,
        //         (28.0 / 34.0) * width, (28.0 / 34.0) * width,
        //         (33.5 / 34.0) * width, (22.5 / 34.0) * width
        //     );

        //     Blend blendEffect = new Blend(BlendMode.OVERLAY, new InnerShadow(0.075 * width, Color.BLACK), new DropShadow(0.075 * width, Color.BLACK));

        //     castleBackground.setEffect(blendEffect);
        //     northBackground.setEffect(blendEffect);
        //     southBackground.setEffect(blendEffect);
        //     eastBackground.setEffect(blendEffect);
        //     westBackground.setEffect(blendEffect);
        // }
    }



    private class TowerCardPane extends Pane {
        private TowerDisplay castleTower;
        private TowerDisplay northTower;
        private TowerDisplay southTower;
        private TowerDisplay eastTower;
        private TowerDisplay westTower;
        private TowerBackgroundPane backgroundPane;

        public void refresh() {
            castleTower.updateTowerInformation();
            northTower.updateTowerInformation();
            southTower.updateTowerInformation();
            eastTower.updateTowerInformation();
            westTower.updateTowerInformation();
        }

        // public void setHeight(double height) {
        //     this.setMinWidth(height);
        //     this.setMinHeight(height);
        //     super.setHeight(height);
        //     super.setWidth(height);
        //     castleTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
        //     northTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
        //     southTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
        //     eastTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
        //     westTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);

        //     castleTower.relocate((12.0 / 34.0 ) * height, (12.0 / 34.0 ) *  height);
        //     northTower.relocate((12.0 / 34.0 ) * height, (1.0 / 34.0 ) *  height);
        //     southTower.relocate((12.0 / 34.0 ) * height, (23.0 / 34.0 ) *  height);
        //     eastTower.relocate((1.0 / 34.0 ) * height, (12.0 / 34.0 ) *  height);
        //     westTower.relocate((23.0 / 34.0 ) * height, (12.0 / 34.0 ) *  height);

        // }
        // public void setWidth(double width) {
        //     this.setMinWidth(width);
        //     this.setMinHeight(width);
        //     super.setHeight(width);
        //     super.setWidth(width);
        //     castleTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
        //     northTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
        //     southTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
        //     eastTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
        //     westTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);

        //     castleTower.relocate((12.0 / 34.0 ) * width, (12.0 / 34.0 ) *  width);
        //     northTower.relocate((12.0 / 34.0 ) * width, (1.0 / 34.0 ) *  width);
        //     southTower.relocate((12.0 / 34.0 ) * width, (23.0 / 34.0 ) *  width);
        //     eastTower.relocate((1.0 / 34.0 ) * width, (12.0 / 34.0 ) *  width);
        //     westTower.relocate((23.0 / 34.0 ) * width, (12.0 / 34.0 ) *  width);
        // }

        public TowerCardPane(double size, TowerBackgroundPane background, Game game) {
            super();
            super.setHeight(size);
            super.setWidth(size);

            backgroundPane = background;

            castleTower = game.getTowers().get(0).getTowerDisplay();
            northTower = game.getTowers().get(1).getTowerDisplay();
            southTower = game.getTowers().get(2).getTowerDisplay();
            eastTower = game.getTowers().get(3).getTowerDisplay();
            westTower = game.getTowers().get(4).getTowerDisplay();

            castleTower.resize((10.0 / 34.0 ) * size, (10.0 / 34.0 ) * size);
            northTower.resize((10.0 / 34.0 ) * size, (10.0 / 34.0 ) * size);
            southTower.resize((10.0 / 34.0 ) * size, (10.0 / 34.0 ) * size);
            eastTower.resize((10.0 / 34.0 ) * size, (10.0 / 34.0 ) * size);
            westTower.resize((10.0 / 34.0 ) * size, (10.0 / 34.0 ) * size);

            castleTower.relocate((12.0 / 34.0 ) * size, (12.0 / 34.0 ) *  size);
            northTower.relocate((12.0 / 34.0 ) * size, (1.0 / 34.0 ) *  size);
            southTower.relocate((12.0 / 34.0 ) * size, (23.0 / 34.0 ) *  size);
            eastTower.relocate((1.0 / 34.0 ) * size, (12.0 / 34.0 ) *  size);
            westTower.relocate((23.0 / 34.0 ) * size, (12.0 / 34.0 ) *  size);

            castleTower.setOnMouseClicked(e -> {
                if (center) {
                    selectTower(TowerPosition.CENTER, game);
                }
                else {
                    game.getGamePane().playSoundEffect(SoundEffectType.ERROR);
                }
            });
            northTower.setOnMouseClicked(e -> {
                if (north) {
                    selectTower(TowerPosition.NORTH, game);
                }
                else {
                    game.getGamePane().playSoundEffect(SoundEffectType.ERROR);
                }
            });
            southTower.setOnMouseClicked(e -> {
                if (south) {
                    selectTower(TowerPosition.SOUTH, game);
                }
                else {
                    game.getGamePane().playSoundEffect(SoundEffectType.ERROR);
                }
            });
            eastTower.setOnMouseClicked(e -> {
                if (east) {
                    selectTower(TowerPosition.EAST, game);
                }
                else {
                    game.getGamePane().playSoundEffect(SoundEffectType.ERROR);
                }
            });
            westTower.setOnMouseClicked(e -> {
                if (west) {
                    selectTower(TowerPosition.WEST, game);
                }
                else {
                    game.getGamePane().playSoundEffect(SoundEffectType.ERROR);
                }
            });


            this.getChildren().addAll(castleTower, northTower, southTower, eastTower, westTower);

        }
    }
}


