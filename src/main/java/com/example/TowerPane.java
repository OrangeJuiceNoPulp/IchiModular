package com.example;

import com.example.Tower.TowerPosition;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class TowerPane extends StackPane {
    private Game game;

    private TowerCardPane towerCardPane;
    private TowerBackgroundPane towerBackgroundPane;

    private double towerHeight;
    private double towerWidth;
    private double towerSize;

    public void stopAnimation() {
        if (towerBackgroundPane != null) {
            if (towerBackgroundPane.backgroundAnimation != null) {
                towerBackgroundPane.backgroundAnimation.stop();
            }
        }
    }

    public void refresh() {
        towerCardPane.refresh();
        //new Exception().printStackTrace(System.out); //TODO remove this after debugging
    }

    public void clear() {
        this.getChildren().clear();
        game = null;
        towerBackgroundPane = null;
        towerBackgroundPane = null;
    }

    // public void selectTower(Tower.TowerPosition position) {
    //     switch(position) {
    //         case CENTER:
    //             imgTowerSelector = new ImageView(new Image(this.getClass().getResourceAsStream("castleTowerSelected.png")));
    //             break;
    //         case EAST:
    //             imgTowerSelector = new ImageView(new Image(this.getClass().getResourceAsStream("eastTowerSelected.png")));
    //             break;
    //         case NORTH:
    //             imgTowerSelector = new ImageView(new Image(this.getClass().getResourceAsStream("northTowerSelected.png")));
    //             break;
    //         case SOUTH:
    //             imgTowerSelector = new ImageView(new Image(this.getClass().getResourceAsStream("southTowerSelected.png")));
    //             break;
    //         case WEST:
    //             imgTowerSelector = new ImageView(new Image(this.getClass().getResourceAsStream("westTowerSelected.png")));
    //             break;
    //         default:
    //             imgTowerSelector = new ImageView(new Image(this.getClass().getResourceAsStream("castleTowerSelected.png")));
    //             break;
            
    //     }

    // }

    public void selectTower(TowerPosition position) {
        towerBackgroundPane.selectTower(position);
    }

    public TowerPane(Game game, double initialSize) {
        super();
        
        this.game = game;

        // towersPane = new GridPane();
        // ColumnConstraints col1 = new ColumnConstraints();
        // col1.setPercentWidth((100.0 / 3.0));
        // ColumnConstraints col2 = new ColumnConstraints();
        // col2.setPercentWidth((100.0 / 3.0));
        // ColumnConstraints col3 = new ColumnConstraints();
        // col3.setPercentWidth((100.0 / 3.0));
        // RowConstraints row1 = new RowConstraints();
        // row1.setPercentHeight((100.0 / 3.0));
        // RowConstraints row2 = new RowConstraints();
        // row2.setPercentHeight((100.0 / 3.0));
        // RowConstraints row3 = new RowConstraints();
        // row3.setPercentHeight((100.0 / 3.0));

        // towersPane.getColumnConstraints().addAll(col1, col2, col3);
        // towersPane.getRowConstraints().addAll(row1, row2, row3);
        // towersPane.setMaxSize(0.9 * initialSize, 0.9 * initialSize);
        // towersPane.setMinSize(0.9 * initialSize, 0.9 * initialSize);

        // imgColorSpinner = new ImageView(new Image(this.getClass().getResourceAsStream("ColorWheel.png")));
        // imgColorSpinner.setFitHeight(initialSize);
        // imgColorSpinner.setFitWidth(initialSize);
        // imgColorSpinner.setScaleZ(50);

        // RotateTransition spinSpinner = new RotateTransition();
        // spinSpinner.setNode(imgColorSpinner);
        // spinSpinner.setDuration(Duration.millis(5000));
        // spinSpinner.setCycleCount(RotateTransition.INDEFINITE);
        // spinSpinner.setInterpolator(Interpolator.LINEAR);
        // spinSpinner.setByAngle(360);
        // spinSpinner.play();

        // imgTowerPaneBackground = new ImageView(new Image(this.getClass().getResourceAsStream("towerPaneBackground.png")));
        // imgTowerPaneBackground.setFitHeight(initialSize);
        // imgTowerPaneBackground.setFitWidth(initialSize);

        //imgTowerSelector = new ImageView(new Image(this.getClass().getResourceAsStream("castleTowerSelected.png")));
        //imgTowerSelector.setFitHeight(initialSize);
        //imgTowerSelector.setFitWidth(initialSize);

        // this.add(game.getTowers().get(0).getTowerDisplay(), 1, 1);
        // this.add(game.getTowers().get(1).getTowerDisplay(), 1, 0);
        // this.add(game.getTowers().get(2).getTowerDisplay(), 1, 2);
        // this.add(game.getTowers().get(3).getTowerDisplay(), 2, 1);
        // this.add(game.getTowers().get(4).getTowerDisplay(), 0, 1);

        // towersPane.add(castleTower, 1, 1);
        // towersPane.add(northTower, 0, 1);
        // towersPane.add(southTower, 2, 1);
        // towersPane.add(eastTower, 1, 2);
        // towersPane.add(westTower, 1, 0);
        // towersPane.setHgap(30);
        // towersPane.setVgap(10);
        // towersPane.setAlignment(Pos.TOP_LEFT);

        // GridPane.setMargin(castleTower, new Insets(10, 10, 10, 10));
        // GridPane.setMargin(northTower, new Insets(10, 10, 10, 10));
        // GridPane.setMargin(southTower, new Insets(10, 10, 10, 10));
        // GridPane.setMargin(eastTower, new Insets(10, 10, 10, 10));
        // GridPane.setMargin(westTower, new Insets(10, 30, 10, 0));

        towerBackgroundPane = new TowerBackgroundPane(initialSize);
        towerCardPane = new TowerCardPane(initialSize, towerBackgroundPane);
        
        this.getChildren().addAll(towerBackgroundPane, towerCardPane);
        this.setAlignment(Pos.TOP_LEFT);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64,64,64), new CornerRadii(0.0), new Insets(0,0,0,0))));
        //this.refresh();
    }

    public void scale(double size) {

        towerBackgroundPane.setHeight(size);
        towerCardPane.setHeight(size);

        // imgColorSpinner.setFitHeight(size);
        // imgColorSpinner.setFitWidth(size);

        // imgTowerPaneBackground.setFitHeight(size);
        // imgTowerPaneBackground.setFitWidth(size);

        //imgTowerSelector.setFitHeight(size);
        //imgTowerSelector.setFitWidth(size);

        // towersPane.setMaxSize(0.9 * size, 0.9 * size);
        // towersPane.setMinSize(0.9 * size, 0.9 * size);
    }

    // public void resize(double height, double width) {
    //     super.resize(Math.min(height, width), Math.min(height, width));
    //     scale(Math.min(height, width));
    // }

    @Override
    public void setHeight(double input) {
        towerHeight = input;
        double size = Math.max(Math.min(input, towerWidth), 1);

        if (Math.abs(size - towerSize) > 0.5) {
            towerSize = size;
            //System.out.println("Height: " + input + " " + this.getWidth() + " " + size);
            super.setHeight(size);
            super.setWidth(size);
        
            scale(size);
            //new Error().printStackTrace();
        }
    }

    @Override
    public void setWidth(double input) {
        towerWidth = input;
        double size = Math.max(Math.min(input, towerHeight), 1);

        if (Math.abs(size - towerSize) > 0.5) {
            towerSize = size;
            //System.out.println("Width: " + input + " " + this.getHeight() + " " + size);
            super.setHeight(size);
            super.setWidth(size);
            
            scale(size);
        }
        
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

        public void setHeight(double height) {
            this.setMinWidth(height);
            this.setMinHeight(height);
            super.setHeight(height);
            super.setWidth(height);
            castleTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
            northTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
            southTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
            eastTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);
            westTower.resize((10.0 / 34.0 ) * height, (10.0 / 34.0 ) * height);

            castleTower.relocate((12.0 / 34.0 ) * height, (12.0 / 34.0 ) *  height);
            northTower.relocate((12.0 / 34.0 ) * height, (1.0 / 34.0 ) *  height);
            southTower.relocate((12.0 / 34.0 ) * height, (23.0 / 34.0 ) *  height);
            eastTower.relocate((1.0 / 34.0 ) * height, (12.0 / 34.0 ) *  height);
            westTower.relocate((23.0 / 34.0 ) * height, (12.0 / 34.0 ) *  height);

        }
        public void setWidth(double width) {
            this.setMinWidth(width);
            this.setMinHeight(width);
            super.setHeight(width);
            super.setWidth(width);
            castleTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
            northTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
            southTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
            eastTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);
            westTower.resize((10.0 / 34.0 ) * width, (10.0 / 34.0 ) * width);

            castleTower.relocate((12.0 / 34.0 ) * width, (12.0 / 34.0 ) *  width);
            northTower.relocate((12.0 / 34.0 ) * width, (1.0 / 34.0 ) *  width);
            southTower.relocate((12.0 / 34.0 ) * width, (23.0 / 34.0 ) *  width);
            eastTower.relocate((1.0 / 34.0 ) * width, (12.0 / 34.0 ) *  width);
            westTower.relocate((23.0 / 34.0 ) * width, (12.0 / 34.0 ) *  width);
        }

        public TowerCardPane(double size, TowerBackgroundPane background) {
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
                towerCardPane.refresh();
                System.out.println("Clicked c");
                background.selectTower(TowerPosition.CENTER);
            });
            northTower.setOnMouseClicked(e -> {
                towerCardPane.refresh();
                System.out.println("Clicked n");
                background.selectTower(TowerPosition.NORTH);
            });
            southTower.setOnMouseClicked(e -> {
                towerCardPane.refresh();
                System.out.println("Clicked s");
                background.selectTower(TowerPosition.SOUTH);
            });
            eastTower.setOnMouseClicked(e -> {
                towerCardPane.refresh();
                System.out.println("Clicked e");
                background.selectTower(TowerPosition.EAST);
            });
            westTower.setOnMouseClicked(e -> {
                towerCardPane.refresh();
                System.out.println("Clicked w");
                background.selectTower(TowerPosition.WEST);
            });


            this.getChildren().addAll(castleTower, northTower, southTower, eastTower, westTower);

        }
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

        public void selectTower(TowerPosition position) {
            game.setSelectedTowerPosition(position);
            switch(position) {
                case CENTER:
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(castleBackground);
                    break;
                case EAST:
                castleBackground.setFill(Color.rgb(64, 64, 64));
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(eastBackground);
                    break;
                case NORTH:
                castleBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(northBackground);
                    break;
                case SOUTH:
                castleBackground.setFill(Color.rgb(64, 64, 64));
                northBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(southBackground);
                    break;
                case WEST:
                castleBackground.setFill(Color.rgb(64, 64, 64));
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(westBackground);
                    break;
                default:
                northBackground.setFill(Color.rgb(64, 64, 64));
                southBackground.setFill(Color.rgb(64, 64, 64));
                eastBackground.setFill(Color.rgb(64, 64, 64));
                westBackground.setFill(Color.rgb(64, 64, 64));
                setBackgroundAnimation(castleBackground);
                    break;

            }
        }

        public TowerBackgroundPane(double size) {
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
            
            selectTower(TowerPosition.CENTER);
            
            this.getChildren().addAll(castleBackground, northBackground, southBackground, eastBackground, westBackground);

        }

        @Override
        public void setHeight(double height) {
            this.setMinWidth(height);
            this.setMinHeight(height);
            //double height = Math.min(input, this.getWidth());
            super.setHeight(height);
            super.setWidth(height);

            castleBackground.getPoints().clear();
            northBackground.getPoints().clear();
            southBackground.getPoints().clear();
            eastBackground.getPoints().clear();
            westBackground.getPoints().clear();

            castleBackground.getPoints().addAll(
                (11.5 / 34.0) * height, (11.5 / 34.0) * height,
                (11.5 / 34.0) * height, (22.5 / 34.0) * height,
                (22.5 / 34.0) * height, (22.5 / 34.0) * height,
                (22.5 / 34.0) * height, (11.5 / 34.0) * height
            );

            northBackground.getPoints().addAll(
                (11.5 / 34.0) * height, (0.5 / 34.0) * height,
                (6.0 / 34.0) * height, (6.0 / 34.0) * height,
                (11.5 / 34.0) * height, (11.5 / 34.0) * height,
                (22.5 / 34.0) * height, (11.5 / 34.0) * height,
                (28.0 / 34.0) * height, (6.0 / 34.0) * height,
                (22.5 / 34.0) * height, (0.5 / 34.0) * height
            );
            southBackground.getPoints().addAll(
                (11.5 / 34.0) * height, (33.5 / 34.0) * height,
                (6.0 / 34.0) * height, (28.0 / 34.0) * height,
                (11.5 / 34.0) * height, (22.5 / 34.0) * height,
                (22.5 / 34.0) * height, (22.5 / 34.0) * height,
                (28.0 / 34.0) * height, (28.0 / 34.0) * height,
                (22.5 / 34.0) * height, (33.5 / 34.0) * height
            );

            eastBackground.getPoints().addAll(
                (0.5 / 34.0) * height, (11.5 / 34.0) * height,
                (6.0 / 34.0) * height, (6.0 / 34.0) * height,
                (11.5 / 34.0) * height, (11.5 / 34.0) * height,
                (11.5 / 34.0) * height, (22.5 / 34.0) * height,
                (6.0 / 34.0) * height, (28.0 / 34.0) * height,
                (0.5 / 34.0) * height, (22.5 / 34.0) * height
            );
            westBackground.getPoints().addAll(
                (33.5 / 34.0) * height, (11.5 / 34.0) * height,
                (28.0 / 34.0) * height, (6.0 / 34.0) * height,
                (22.5 / 34.0) * height, (11.5 / 34.0) * height,
                (22.5 / 34.0) * height, (22.5 / 34.0) * height,
                (28.0 / 34.0) * height, (28.0 / 34.0) * height,
                (33.5 / 34.0) * height, (22.5 / 34.0) * height
            );

            Blend blendEffect = new Blend(BlendMode.OVERLAY, new InnerShadow(0.075 * height, Color.BLACK), new DropShadow(0.075 * height, Color.BLACK));

            castleBackground.setEffect(blendEffect);
            northBackground.setEffect(blendEffect);
            southBackground.setEffect(blendEffect);
            eastBackground.setEffect(blendEffect);
            westBackground.setEffect(blendEffect);
        }

        @Override
        public void setWidth(double width) {
            this.setMinWidth(width);
            this.setMinHeight(width);
            //double width = Math.min(input, this.getHeight());
            super.setHeight(width);
            super.setWidth(width);

            castleBackground.getPoints().clear();
            northBackground.getPoints().clear();
            southBackground.getPoints().clear();
            eastBackground.getPoints().clear();
            westBackground.getPoints().clear();

            castleBackground.getPoints().addAll(
                (11.5 / 34.0) * width, (11.5 / 34.0) * width,
                (11.5 / 34.0) * width, (22.5 / 34.0) * width,
                (22.5 / 34.0) * width, (22.5 / 34.0) * width,
                (22.5 / 34.0) * width, (11.5 / 34.0) * width
            );

            northBackground.getPoints().addAll(
                (11.5 / 34.0) * width, (0.5 / 34.0) * width,
                (6.0 / 34.0) * width, (6.0 / 34.0) * width,
                (11.5 / 34.0) * width, (11.5 / 34.0) * width,
                (22.5 / 34.0) * width, (11.5 / 34.0) * width,
                (28.0 / 34.0) * width, (6.0 / 34.0) * width,
                (22.5 / 34.0) * width, (0.5 / 34.0) * width
            );
            southBackground.getPoints().addAll(
                (11.5 / 34.0) * width, (33.5 / 34.0) * width,
                (6.0 / 34.0) * width, (28.0 / 34.0) * width,
                (11.5 / 34.0) * width, (22.5 / 34.0) * width,
                (22.5 / 34.0) * width, (22.5 / 34.0) * width,
                (28.0 / 34.0) * width, (28.0 / 34.0) * width,
                (22.5 / 34.0) * width, (33.5 / 34.0) * width
            );

            eastBackground.getPoints().addAll(
                (0.5 / 34.0) * width, (11.5 / 34.0) * width,
                (6.0 / 34.0) * width, (6.0 / 34.0) * width,
                (11.5 / 34.0) * width, (11.5 / 34.0) * width,
                (11.5 / 34.0) * width, (22.5 / 34.0) * width,
                (6.0 / 34.0) * width, (28.0 / 34.0) * width,
                (0.5 / 34.0) * width, (22.5 / 34.0) * width
            );
            westBackground.getPoints().addAll(
                (33.5 / 34.0) * width, (11.5 / 34.0) * width,
                (28.0 / 34.0) * width, (6.0 / 34.0) * width,
                (22.5 / 34.0) * width, (11.5 / 34.0) * width,
                (22.5 / 34.0) * width, (22.5 / 34.0) * width,
                (28.0 / 34.0) * width, (28.0 / 34.0) * width,
                (33.5 / 34.0) * width, (22.5 / 34.0) * width
            );

            Blend blendEffect = new Blend(BlendMode.OVERLAY, new InnerShadow(0.075 * width, Color.BLACK), new DropShadow(0.075 * width, Color.BLACK));

            castleBackground.setEffect(blendEffect);
            northBackground.setEffect(blendEffect);
            southBackground.setEffect(blendEffect);
            eastBackground.setEffect(blendEffect);
            westBackground.setEffect(blendEffect);
        }
    }


    
}
