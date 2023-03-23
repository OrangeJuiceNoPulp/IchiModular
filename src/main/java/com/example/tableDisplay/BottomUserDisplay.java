package com.example.tableDisplay;

import com.example.Card;
import com.example.CardCellFactory;
import com.example.CardDarkColorComparator;
import com.example.CardDarkValueComparator;
import com.example.CardLightColorComparator;
import com.example.CardLightValueComparator;
import com.example.Game;
import com.example.UserStatsDisplay;
import com.example.SoundEffectPlayer.SoundEffectType;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

public class BottomUserDisplay extends HBox {
    private UserStatsDisplay statsDisplay;
    private ListView<Card> handDisplay;

    private Button btnSortColor;
    private Button btnSortValue;

    private HBox hboxSortButtons;
    private VBox vboxInfoSortButtons;
    private VBox vboxPlayViewModeButtons;

    private Button btnPlayCard;
    private Button btnViewMode;

    public Card getSelectedCard() {
        return handDisplay.getFocusModel().getFocusedItem();
    }

    public void refresh() {
        statsDisplay.updateDisplay();
        refreshHandDisplay();
    }

    private void refreshHandDisplay() {
        handDisplay.refresh();
    }

    public BottomUserDisplay(Game game) {
        super();
        //this.setPadding(new Insets(25));
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        this.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        statsDisplay = new UserStatsDisplay(game);

        btnPlayCard = new Button("Play");
        btnPlayCard.setMinWidth(200);
        btnPlayCard.setMaxWidth(200);
        btnPlayCard.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnPlayCard.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnPlayCard.setTextFill(Color.WHITESMOKE);
        btnPlayCard.setFont(new Font("Perpetua Bold Italic", 25));
        btnPlayCard.setOnMouseEntered(e -> {
            btnPlayCard.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnPlayCard.setOnMouseExited(e -> {
            btnPlayCard.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

        btnPlayCard.setOnAction(e -> {
            System.out.println("Tried Playing Card at " + game.getSelectedTower().getPosition()); //TODO remove debug text
            System.out.println("The game is waiting for user turn: " + game.getWaitingForUserToTakeTurn()); //TODO remove debug text
            if(game.getWaitingForUserToTakeTurn()) {
                game.setWaitingForUserToTakeTurn(false);
                Card selectedCard = getSelectedCard(); 

                boolean success = game.playCard(game.getUser(), game.getSelectedTower(), selectedCard);
    
                if (success) {
                    game.getUser().endTurn();
                    //refreshPane();
                }
                else {
                    game.getGamePane().playSoundEffect(SoundEffectType.ERROR);
                    game.setWaitingForUserToTakeTurn(true);
                }
            }
        });

        


        btnViewMode = new Button("View Dark Mode");
        btnViewMode.setMinWidth(200);
        btnViewMode.setMaxWidth(200);
        btnViewMode.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnViewMode.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnViewMode.setTextFill(Color.WHITESMOKE);
        btnViewMode.setFont(new Font("Perpetua Bold Italic", 15));
        btnViewMode.setOnMouseEntered(e -> {
            btnViewMode.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnViewMode.setOnMouseExited(e -> {
            btnViewMode.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

        btnViewMode.setOnAction(e -> {
            game.setViewingDarkMode(!game.getViewingDarkMode());
            refreshHandDisplay();
            if (game.getViewingDarkMode()) {
                btnViewMode.setText("View Light Mode");
            }
            else {
                btnViewMode.setText("View Dark Mode");
            }
        });



        btnSortColor = new Button("Sort Color");
        btnSortColor.setMinWidth(100);
        btnSortColor.setMaxWidth(100);
        btnSortColor.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnSortColor.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnSortColor.setTextFill(Color.WHITESMOKE);
        btnSortColor.setFont(new Font("Perpetua Bold Italic", 15));
        btnSortColor.setOnMouseEntered(e -> {
            btnSortColor.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnSortColor.setOnMouseExited(e -> {
            btnSortColor.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

        btnSortColor.setOnAction(e -> {
            if (game.getViewingDarkMode()) {
                handDisplay.getItems().sort(new CardDarkColorComparator());
            }
            else {
                handDisplay.getItems().sort(new CardLightColorComparator());
            }
        });

        


        btnSortValue = new Button("Sort Value");
        btnSortValue.setMinWidth(100);
        btnSortValue.setMaxWidth(100);
        btnSortValue.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnSortValue.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnSortValue.setTextFill(Color.WHITESMOKE);
        btnSortValue.setFont(new Font("Perpetua Bold Italic", 15));
        btnSortValue.setOnMouseEntered(e -> {
            btnSortValue.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnSortValue.setOnMouseExited(e -> {
            btnSortValue.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

        btnSortValue.setOnAction(e -> {
            if (game.getViewingDarkMode()) {
                handDisplay.getItems().sort(new CardDarkValueComparator());
            }
            else {
                handDisplay.getItems().sort(new CardLightValueComparator());
            }
        });
        


        hboxSortButtons = new HBox();
        hboxSortButtons.getChildren().addAll(btnSortColor, btnSortValue);
        hboxSortButtons.setMinWidth(200);
        hboxSortButtons.setMaxWidth(200);

        vboxInfoSortButtons = new VBox();
        vboxInfoSortButtons.getChildren().addAll(statsDisplay, hboxSortButtons);

        vboxPlayViewModeButtons = new VBox();
        vboxPlayViewModeButtons.getChildren().addAll(btnPlayCard, btnViewMode);

        handDisplay = new ListView<Card>(game.getUser().getHand());

        handDisplay.setCellFactory(new CardCellFactory());
        handDisplay.setOrientation(Orientation.HORIZONTAL);
        handDisplay.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        handDisplay.getFocusModel().focus(0);
        handDisplay.getSelectionModel().clearSelection();
        handDisplay.getSelectionModel().selectFirst();
        handDisplay.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));


        this.getChildren().addAll(vboxInfoSortButtons, handDisplay, vboxPlayViewModeButtons);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        handDisplay.setMinWidth(Math.max((width - 400), 1));
        handDisplay.setMaxWidth(Math.max((width - 400), 1));

    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        handDisplay.setMinHeight(height);
        handDisplay.setMaxHeight(height);

        statsDisplay.scaleToHeight(height * (2.0 / 3.0));
        btnSortValue.setMinHeight(height * (1.0 / 3.0));
        btnSortValue.setMaxHeight(height * (1.0 / 3.0));
        btnSortColor.setMinHeight(height * (1.0 / 3.0));
        btnSortColor.setMaxHeight(height * (1.0 / 3.0));

        hboxSortButtons.setMinHeight(height * (1.0 / 3.0));
        hboxSortButtons.setMaxHeight(height * (1.0 / 3.0));

        vboxInfoSortButtons.setMinHeight(height);
        vboxInfoSortButtons.setMaxHeight(height);

        btnPlayCard.setMinHeight(height * (2.0 / 3.0));
        btnPlayCard.setMaxHeight(height * (2.0 / 3.0));

        btnViewMode.setMinHeight(height * (1.0 / 3.0));
        btnViewMode.setMaxHeight(height * (1.0 / 3.0));

        vboxPlayViewModeButtons.setMinHeight(height);
        vboxPlayViewModeButtons.setMaxHeight(height);

    }
}
