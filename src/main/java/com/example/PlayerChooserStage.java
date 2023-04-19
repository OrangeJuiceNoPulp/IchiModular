package com.example;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayerChooserStage extends Stage {

    private Player choice;

    private Button btnConfirmSelection;

    private BotStatsDisplay displayPlayer2;
    private BotStatsDisplay displayPlayer3;
    private BotStatsDisplay displayPlayer4;

    private Timeline backgroundAnimation;

    public Player getChoice() {
        return this.choice;
    }

    private void setBackgroundAnimation(Rectangle background) {
        backgroundAnimation.stop();
        backgroundAnimation.setCycleCount(Animation.INDEFINITE);
        backgroundAnimation.getKeyFrames().clear();
        backgroundAnimation.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(2500), new KeyValue(background.fillProperty(), Color.rgb(255, 0, 0), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(5000), new KeyValue(background.fillProperty(), Color.rgb(32, 32, 32), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(7500), new KeyValue(background.fillProperty(), Color.rgb(0, 38, 255), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(10000), new KeyValue(background.fillProperty(), Color.rgb(32, 32, 32), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(12500), new KeyValue(background.fillProperty(), Color.rgb(0, 127, 14), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(15000), new KeyValue(background.fillProperty(), Color.rgb(32, 32, 32), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(17500), new KeyValue(background.fillProperty(), Color.rgb(255, 216, 0), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(20000), new KeyValue(background.fillProperty(), Color.rgb(32, 32, 32), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(22500), new KeyValue(background.fillProperty(), Color.rgb(255, 106, 0), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(25000), new KeyValue(background.fillProperty(), Color.rgb(32, 32, 32), Interpolator.EASE_BOTH)),
            new KeyFrame(Duration.millis(27500), new KeyValue(background.fillProperty(), Color.rgb(87, 0, 127), Interpolator.EASE_OUT)),
            new KeyFrame(Duration.millis(30000), new KeyValue(background.fillProperty(), Color.rgb(32, 32, 32), Interpolator.EASE_BOTH))
        );
        backgroundAnimation.play();
    }

    private void selectPlayer(int playerNumber) {
        if (playerNumber == 2) {
            if (displayPlayer2 != null) {
                this.choice = displayPlayer2.getPlayer();
                setBackgroundAnimation(displayPlayer2.getBackgroundRectangle());

                if (displayPlayer3 != null) {
                    displayPlayer3.getBackgroundRectangle().setFill(Color.rgb(32, 32, 32));
                }
                if (displayPlayer4 != null) {
                    displayPlayer4.getBackgroundRectangle().setFill(Color.rgb(32, 32, 32));
                }
            }
        }
        else if (playerNumber == 3) {
            if (displayPlayer3 != null) {
                this.choice = displayPlayer3.getPlayer();
                setBackgroundAnimation(displayPlayer3.getBackgroundRectangle());

                if (displayPlayer2 != null) {
                    displayPlayer2.getBackgroundRectangle().setFill(Color.rgb(32, 32, 32));
                }
                if (displayPlayer4 != null) {
                    displayPlayer4.getBackgroundRectangle().setFill(Color.rgb(32, 32, 32));
                }
            }
        }
        else {
            if (displayPlayer4 != null) {
                this.choice = displayPlayer4.getPlayer();
                setBackgroundAnimation(displayPlayer4.getBackgroundRectangle());

                if (displayPlayer2 != null) {
                    displayPlayer2.getBackgroundRectangle().setFill(Color.rgb(32, 32, 32));
                }
                if (displayPlayer3 != null) {
                    displayPlayer3.getBackgroundRectangle().setFill(Color.rgb(32, 32, 32));
                }
            }
            
        }
    }

    public PlayerChooserStage(Player choosingPlayer, Game game) {
        super();
        this.choice = null;
        this.setTitle("Choose a Player");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.getIcons().add(new Image(this.getClass().getResource("cardBackSquare.png").toExternalForm()));

        backgroundAnimation = new Timeline();

        GridPane playerChoicePane = new GridPane();
        playerChoicePane.getColumnConstraints().addAll(new ColumnConstraints(250), new ColumnConstraints(250), new ColumnConstraints(225));
        playerChoicePane.getRowConstraints().addAll(new RowConstraints(150), new RowConstraints(150), new RowConstraints(100));

        playerChoicePane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        playerChoicePane.setPadding(new Insets(0, 0, 0, 25));

        BorderPane pane = new BorderPane();

        if (game.getNumOfPlayers() == 2) {
            this.choice = game.getPlayer(2);

            displayPlayer2 = new BotStatsDisplay(game, 2);
            displayPlayer2.scaleToHeight(100);
            displayPlayer2.updateDisplay();
            playerChoicePane.add(displayPlayer2, 1, 0);

            selectPlayer(this.choice.getPlayerNum());
        }
        else if (game.getNumOfPlayers() == 3) {

            //Determines which player to automatically have selected.
            ArrayList<Player> choices = new ArrayList<Player>(5);
            int minHandSize = 1000; // An absurdly large value which is more cards than any player could hold
            for (Player player : game.getPlayers()) {
                if (player.getPlayerNum() != choosingPlayer.getPlayerNum()) {
                    if (minHandSize == player.getHand().size()) {
                        choices.add(player);
                    } else if (minHandSize > player.getHand().size()) {
                        choices.clear();
                        minHandSize = player.getHand().size();
                        choices.add(player);
                    }
                }
            }
            int randomDecision = game.getRand().nextInt(choices.size());
            this.choice = choices.get(randomDecision);
            //Finished determining which player to automatically have selected.

            displayPlayer2 = new BotStatsDisplay(game, 2);
            displayPlayer2.setOnMouseClicked(e -> {
                this.choice = game.getPlayer(2);
                selectPlayer(this.choice.getPlayerNum());
            });
            displayPlayer2.scaleToHeight(100);
            displayPlayer2.updateDisplay();
            playerChoicePane.add(displayPlayer2, 2, 1);

            displayPlayer3 = new BotStatsDisplay(game, 3);
            displayPlayer3.setOnMouseClicked(e -> {
                this.choice = game.getPlayer(3);
                selectPlayer(this.choice.getPlayerNum());
            });
            displayPlayer3.scaleToHeight(100);
            displayPlayer3.updateDisplay();
            playerChoicePane.add(displayPlayer3, 1, 0);
            

            selectPlayer(this.choice.getPlayerNum());
        }
        else {
            //Determines which player to automatically have selected.
            ArrayList<Player> choices = new ArrayList<Player>(5);
            int minHandSize = 1000; // An absurdly large value which is more cards than any player could hold
            for (Player player : game.getPlayers()) {
                if (player.getPlayerNum() != choosingPlayer.getPlayerNum()) {
                    if (minHandSize == player.getHand().size()) {
                        choices.add(player);
                    } else if (minHandSize > player.getHand().size()) {
                        choices.clear();
                        minHandSize = player.getHand().size();
                        choices.add(player);
                    }
                }
            }
            int randomDecision = game.getRand().nextInt(choices.size());
            this.choice = choices.get(randomDecision);
            //Finished determining which player to automatically have selected.

            displayPlayer2 = new BotStatsDisplay(game, 2);
            displayPlayer2.setOnMouseClicked(e -> {
                this.choice = game.getPlayer(2);
                selectPlayer(this.choice.getPlayerNum());
            });
            displayPlayer2.scaleToHeight(100);
            displayPlayer2.updateDisplay();
            playerChoicePane.add(displayPlayer2, 2, 1);

            displayPlayer3 = new BotStatsDisplay(game, 3);
            displayPlayer3.setOnMouseClicked(e -> {
                this.choice = game.getPlayer(3);
                selectPlayer(this.choice.getPlayerNum());
            });
            displayPlayer3.scaleToHeight(100);
            displayPlayer3.updateDisplay();
            playerChoicePane.add(displayPlayer3, 1, 0);

            displayPlayer4 = new BotStatsDisplay(game, 4);
            displayPlayer4.setOnMouseClicked(e -> {
                this.choice = game.getPlayer(4);
                selectPlayer(this.choice.getPlayerNum());
            });
            displayPlayer4.scaleToHeight(100);
            displayPlayer4.updateDisplay();
            playerChoicePane.add(displayPlayer4, 0, 1);

            selectPlayer(this.choice.getPlayerNum());
        }


        btnConfirmSelection = new Button("Confirm Selection");
        btnConfirmSelection.setOnAction(e -> {
            System.out.println("Selected " + choice.getPlayerName() + ". They have " + choice.getHandSize() + " cards."); //TODO remove after debugging
            this.close();
        });
        btnConfirmSelection.setMinSize(750, 100);
        btnConfirmSelection.setMaxSize(750, 100);
        btnConfirmSelection.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnConfirmSelection.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnConfirmSelection.setTextFill(Color.WHITESMOKE);
        btnConfirmSelection.setFont(new Font("Perpetua Bold Italic", 25));
        btnConfirmSelection.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        btnConfirmSelection.setOnMouseEntered(e -> {
            btnConfirmSelection.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnConfirmSelection.setOnMouseExited(e -> {
            btnConfirmSelection.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

        pane.setCenter(playerChoicePane);
        pane.setBottom(btnConfirmSelection);
        
        
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

        // ArrayList<Button> playerSelectionButtons = new ArrayList<Button>(4);
        // playerSelectionButtons.add(btnEast);
        // playerSelectionButtons.add(btnNorth);
        // playerSelectionButtons.add(btnWest);

        // int index = 0;
        // for (Player player : game.getPlayers()) {
        //     if (player.getPlayerNum() != choosingPlayer.getPlayerNum()) {
        //         playerSelectionButtons.get(index).setText(player.getPlayerName());
        //         playerSelectionButtons.get(index).setOnAction(e -> {
        //             this.choice = player;
        //             this.close();
        //         });
        //         index++;
        //     }
        // }

        
        // playerChoicePane.add(btnCenter, 1, 1);
        // playerChoicePane.add(btnNorth, 1, 0);
        // playerChoicePane.add(btnSouth, 1, 2);
        // playerChoicePane.add(btnEast, 2, 1);
        // playerChoicePane.add(btnWest, 0, 1);

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

        // playerChoicePane.add(cornerButton1, 0, 0);
        // playerChoicePane.add(cornerButton2, 0, 2);
        // playerChoicePane.add(cornerButton3, 2, 0);
        // playerChoicePane.add(cornerButton4, 2, 2);

        Scene mainScene = new Scene(pane);
        this.setScene(mainScene);

    }

}
