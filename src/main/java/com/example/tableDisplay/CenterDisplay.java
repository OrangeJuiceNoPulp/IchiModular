package com.example.tableDisplay;

import com.example.CardDisplay;
import com.example.Game;
import com.example.TowerPane;
import com.example.Tower.TowerPosition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CenterDisplay extends HBox {
    private TowerPane towerPane;
    private VBox vboxDrawArea;
    private CardDisplay imgDeckCard;
    private Button btnDraw;

    private Game game;

    private LeftBotDisplay leftDisplay;
    private RightBotDisplay rightDisplay;

    private double towerHeight;
    private double towerWidth;

    public void refresh() {
        towerPane.refresh();
        if (leftDisplay != null) {
            leftDisplay.refresh();
        }
        if (rightDisplay != null) {
            rightDisplay.refresh();
        }
    }

    public void selectTower(TowerPosition position) {
        towerPane.selectTower(position);
    }

    public CenterDisplay(Game game) {
        super(50);

        this.game = game;

        towerPane = new TowerPane(game, 600);
        towerPane.setAlignment(Pos.CENTER);

        imgDeckCard = new CardDisplay();
        imgDeckCard.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        //imgDeckCard.scaleToHand(70, 100); //TODO fix if broken

        btnDraw = new Button("Draw");
        btnDraw.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnDraw.setTextFill(Color.WHITESMOKE);
        btnDraw.setFont(new Font("Perpetua Bold Italic", 750 * 0.09375));
        btnDraw.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        btnDraw.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        btnDraw.setOnMouseEntered(e -> {
            btnDraw.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnDraw.setOnMouseExited(e -> {
            btnDraw.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });
        

        vboxDrawArea = new VBox(25);
        vboxDrawArea.getChildren().addAll(imgDeckCard, btnDraw);
        vboxDrawArea.setAlignment(Pos.CENTER);

        vboxDrawArea.setMaxHeight(1000 * 0.6);
        vboxDrawArea.setMinHeight(1000 * 0.6);

        if (game.getNumOfPlayers() == 2) {
            this.getChildren().addAll(towerPane, vboxDrawArea);
            this.setAlignment(Pos.CENTER);
        }
        else if (game.getNumOfPlayers() == 3) {
            rightDisplay = new RightBotDisplay(game, 2);
            this.getChildren().addAll(towerPane, vboxDrawArea, rightDisplay);
            btnDraw.setFont(new Font("Perpetua Bold Italic", Math.max(Math.min( btnDraw.getHeight() * 0.325, btnDraw.getWidth() * 0.325), 1)));
            this.setAlignment(Pos.CENTER_RIGHT);
        }
        else {
            rightDisplay = new RightBotDisplay(game, 2);
            leftDisplay = new LeftBotDisplay(game, 4);
            this.getChildren().addAll(leftDisplay, towerPane, vboxDrawArea, rightDisplay);
            this.setAlignment(Pos.CENTER);
        }
        
    }

    @Override
    public void setHeight(double height) {
        this.towerHeight = height;
        super.setHeight(height);
        towerPane.setHeight(Math.min(height, towerWidth * 0.3375));

        double minimumWidth = Math.min(Math.max((vboxDrawArea.getHeight() - 25) * 0.3, 1), Math.max((0.5 * this.getWidth()) - towerPane.getHeight() - 50, 1));

        //imgDeckCard.scaleToHand(minimumWidth * 0.7, minimumWidth);
        imgDeckCard.setMinHeight(minimumWidth); //TODO fix if broken
        imgDeckCard.setMaxHeight(minimumWidth);

        btnDraw.setMaxHeight(Math.max((height - 25) * 0.15, 1));
        btnDraw.setMinHeight(Math.max((height - 25) * 0.15, 1));
        btnDraw.setFont(new Font("Perpetua Bold Italic", Math.max(Math.min( btnDraw.getHeight() * 0.325, btnDraw.getWidth() * 0.325), 1)));

        btnDraw.setOnAction(e -> {
            if (game.getWaitingForUserToTakeTurn()) {
                game.setWaitingForUserToTakeTurn(false);
                game.drawCard(game.getUser());
                game.getUser().endTurn();
            }
        });

        imgDeckCard.setOnMouseClicked(e -> {
            if (game.getWaitingForUserToTakeTurn()) {
                game.setWaitingForUserToTakeTurn(false);
                game.drawCard(game.getUser());
                game.getUser().endTurn();
            }
        });

        vboxDrawArea.setMaxHeight(height);
        vboxDrawArea.setMinHeight(height);

        if (leftDisplay != null) {
            leftDisplay.setMinHeight(height);
            leftDisplay.setMaxHeight(height);
        }
        if (rightDisplay != null) {
            rightDisplay.setMinHeight(height);
            rightDisplay.setMaxHeight(height);
        }

    }

    // @Override
    // public void setWidth(double width) {
    //     super.setWidth(width);
        
    //     double remainingCenterWidth = Math.max((0.5 * width) - towerPane.getHeight() - 50, 1);
    //     double remainingWidth = Math.max(width - remainingCenterWidth - towerPane.getHeight(), 1);

    //     vboxDrawArea.setMinWidth(remainingCenterWidth);
    //     vboxDrawArea.setMaxWidth(remainingCenterWidth);

    //     double minimumWidth = Math.min(Math.max((this.getHeight() - 25) * 0.3, 1), remainingCenterWidth);
    //     imgDeckCard.scaleToHand(minimumWidth * 0.7, minimumWidth);
    //     btnDraw.setMinWidth(minimumWidth);
    //     btnDraw.setMaxWidth(minimumWidth);

    //     if (game.getNumOfPlayers() == 3) {
    //         minimumWidth = Math.max((remainingWidth - 150) * 0.5, 1);
    //         rightDisplay.setWidth(minimumWidth);
    //     }
    //     else {
    //         minimumWidth = Math.max((remainingWidth - 200) * 0.5, 1);
    //         rightDisplay.setWidth(minimumWidth);
    //         leftDisplay.setWidth(minimumWidth);
    //     }

    // }

    @Override
    public void setWidth(double width) {
        this.towerWidth = width;
        super.setWidth(width);
        towerPane.setWidth(Math.min(towerHeight, width * 0.3375));
        
        double remainingCenterWidth = Math.max((0.5 * width) - towerPane.getHeight() - 50, 1);
        double remainingWidth = Math.max(width - remainingCenterWidth - towerPane.getHeight(), 1);

        vboxDrawArea.setMinWidth(remainingCenterWidth);
        vboxDrawArea.setMaxWidth(remainingCenterWidth);

        double minimumWidth = Math.min(Math.max((this.getHeight() - 25) * 0.3, 1), remainingCenterWidth);


        //imgDeckCard.scaleToHand(minimumWidth * 0.7, minimumWidth); //TODO fix if broken
        imgDeckCard.setMinHeight(minimumWidth);
        imgDeckCard.setMaxHeight(minimumWidth);

        btnDraw.setMinWidth(minimumWidth);
        btnDraw.setMaxWidth(minimumWidth);

        if (game.getNumOfPlayers() == 2) {
            return;
        }
        else if (game.getNumOfPlayers() == 3) {
            minimumWidth = Math.max((remainingWidth - 150) * 0.5, 1);
            rightDisplay.setWidth(minimumWidth);
        }
        else {
            minimumWidth = Math.max((remainingWidth - 200) * 0.5, 1);
            rightDisplay.setWidth(minimumWidth);
            leftDisplay.setWidth(minimumWidth);
        }

    }





    // @Override
    // public void setWidth(double width) {
    //     super.setWidth(width);

    //     double size = Math.min(width * 0.5, this.getHeight());
    //     if (width * 0.5 < this.getHeight()) {
    //         towerPane.scale(size);
    //     }
    //     else {
    //         double remainingWidth = width - size;
    //         if (game.getNumOfPlayers() == 2) {
    //             double minimumWidth = Math.min((this.getHeight() - 25) * 0.21, (remainingWidth - 50) * (1.0 / 4.5));
    //             imgDeckCard.scaleToHand(minimumWidth, minimumWidth / 0.7);
    //             btnDraw.setMinWidth((remainingWidth - 50) * (1.0 / 3.0));
    //             btnDraw.setMaxWidth((remainingWidth - 50) * (1.0 / 3.0));
    
    //         }
    //         else if (game.getNumOfPlayers() == 3) {
    //             double minimumWidth = Math.min((this.getHeight() - 25) * 0.21, (remainingWidth - 100) * (1.0 / 4.5));
    //             imgDeckCard.scaleToHand(minimumWidth, minimumWidth / 0.7);
    //             btnDraw.setMinWidth((remainingWidth - 100) * (1.0 / 3.0));
    //             btnDraw.setMaxWidth((remainingWidth - 100) * (1.0 / 3.0));
    //             rightDisplay.setWidth((remainingWidth - 100) * (1.0 / 3.0));
    //         }
    //         else {
    //             double minimumWidth = Math.min((this.getHeight() - 25) * 0.21, (remainingWidth - 150) * (1.0 / 4.5));
    //             imgDeckCard.scaleToHand(minimumWidth, minimumWidth / 0.7);
    //             btnDraw.setMinWidth((remainingWidth - 150) * (1.0 / 3.0));
    //             btnDraw.setMaxWidth((remainingWidth - 150) * (1.0 / 3.0));
    //             rightDisplay.setWidth((remainingWidth - 150) * (1.0 / 3.0));
    //             leftDisplay.setWidth((remainingWidth - 150) * (1.0 / 3.0));
    //         }
    
    //     }
        
        
    // }


}
