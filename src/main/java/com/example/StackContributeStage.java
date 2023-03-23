package com.example;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StackContributeStage extends Stage {
    protected static final int topDeckPenalty = 10;

    private Card selectedCard;
    private Game game;
    private Player player;

    public Card getStackedCard() {
        return this.selectedCard;
    }

    // The player "top decks" (For 10 points, they draw the card from the top of the deck. 
    // If it is playable, they play it. Otherwise, they have to keep that card and accept the stack.)
    private void topDeck() {
        if (player.getScore() >= topDeckPenalty) {
            player.removePoints(topDeckPenalty);
            Card topDeckCard = game.getDeck().drawCard();
            player.drawCard(topDeckCard);
            if (game.getStack().isStackable(topDeckCard)) {
                this.selectedCard = topDeckCard;
            }
        }
        else {
            Stage notEnoughPointsStage = new Stage();
            notEnoughPointsStage.setTitle("Top Deck Failed");
            notEnoughPointsStage.initModality(Modality.APPLICATION_MODAL);
            notEnoughPointsStage.setResizable(false);

            BorderPane notEnoughPointsPane = new BorderPane();
            notEnoughPointsPane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));

            Button btnOK = new Button("OK");
            btnOK.setOnAction(e -> {
                notEnoughPointsStage.close();
            });
            btnOK.setMinSize(450, 100);
            btnOK.setMaxSize(450, 100);
            btnOK.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

            btnOK.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
            btnOK.setTextFill(Color.WHITESMOKE);
            btnOK.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
            btnOK.setFont(new Font("Perpetua Bold Italic", 35));
            btnOK.setOnMouseEntered(e -> {
                btnOK.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
            });
            btnOK.setOnMouseExited(e -> {
                btnOK.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
            });

            HBox hboxBtnOK = new HBox(btnOK);
            hboxBtnOK.setAlignment(Pos.CENTER);

            Text txtNotEnoughPoints = new Text("You do not have enough points to top deck.");
            txtNotEnoughPoints.setFill(Color.WHITESMOKE);
            txtNotEnoughPoints.setFont(new Font("Perpetua Bold Italic", 25));
            txtNotEnoughPoints.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));


            Text txtAcceptStack = new Text("You will now accept the stack instead.");
            txtAcceptStack.setFill(Color.WHITESMOKE);
            txtAcceptStack.setFont(new Font("Perpetua Bold Italic", 25));
            txtAcceptStack.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));


            VBox vboxText = new VBox(25);
            vboxText.setPadding(new Insets(25, 0, 25, 0));
            vboxText.setAlignment(Pos.CENTER);
            vboxText.getChildren().addAll(txtNotEnoughPoints, txtAcceptStack);

            notEnoughPointsPane.setTop(vboxText);
            notEnoughPointsPane.setBottom(hboxBtnOK);

            Scene notEnoughPointsScene = new Scene(notEnoughPointsPane);

            notEnoughPointsStage.setScene(notEnoughPointsScene);
            notEnoughPointsStage.showAndWait();
        }
    }

    public StackContributeStage(Player player, Game game) {
        super();

        this.game = game;
        this.player = player;
        this.selectedCard = null;

        this.setTitle("Continue the Stack");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        BorderPane stackChoicePane = new BorderPane();

        stackChoicePane.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));

        ObservableList<Card> cardChoicesList = FXCollections.observableArrayList(new ArrayList<Card>(105));

        //Creates a list of stackable cards in the player's hand
        for (Card card : player.getHand()) {
            if (game.getStack().isStackable(card)) {
                cardChoicesList.add(card);
            }
        }

        //Displays the list of stackable cards in the player's hand
        ListView<Card> lstCardChoices = new ListView<Card>(cardChoicesList);
        lstCardChoices.setCellFactory(new CardCellFactory());
        lstCardChoices.setOrientation(Orientation.HORIZONTAL);
        lstCardChoices.getFocusModel().focus(0);
        lstCardChoices.getSelectionModel().clearSelection();
        lstCardChoices.getSelectionModel().selectFirst();
        lstCardChoices.setMaxHeight(300);
        lstCardChoices.setMinHeight(300);
        lstCardChoices.setMaxWidth(500);
        lstCardChoices.setMinWidth(500);
        lstCardChoices.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        lstCardChoices.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        Button btnAcceptStack = new Button();
        Button btnTopDeck = new Button();
        Button btnPlay = new Button();

        btnAcceptStack.setMinSize(250, 100);
        btnAcceptStack.setMaxSize(250, 100);
        btnAcceptStack.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnAcceptStack.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnAcceptStack.setTextFill(Color.WHITESMOKE);
        btnAcceptStack.setFont(new Font("Perpetua Bold Italic", 25));
        btnAcceptStack.setOnMouseEntered(e -> {
            btnAcceptStack.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnAcceptStack.setOnMouseExited(e -> {
            btnAcceptStack.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });


        btnTopDeck.setMinSize(250, 100);
        btnTopDeck.setMaxSize(250, 100);
        btnTopDeck.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnTopDeck.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnTopDeck.setTextFill(Color.WHITESMOKE);
        btnTopDeck.setFont(new Font("Perpetua Bold Italic", 25));
        btnTopDeck.setOnMouseEntered(e -> {
            btnTopDeck.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnTopDeck.setOnMouseExited(e -> {
            btnTopDeck.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });


        btnPlay.setMinSize(250, 100);
        btnPlay.setMaxSize(250, 100);
        btnPlay.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));

        btnPlay.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnPlay.setTextFill(Color.WHITESMOKE);
        btnPlay.setFont(new Font("Perpetua Bold Italic", 25));
        btnPlay.setOnMouseEntered(e -> {
            btnPlay.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnPlay.setOnMouseExited(e -> {
            btnPlay.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

    
        btnAcceptStack.setText("Accept Stack");
        btnAcceptStack.setOnAction(e -> {
            this.close();
        });

        btnTopDeck.setText("Top Deck\n(-" + topDeckPenalty + " Points)");
        btnTopDeck.setTextAlignment(TextAlignment.CENTER);
        btnTopDeck.setOnAction(e -> {
            topDeck();
            this.close();
        });
      
        btnPlay.setText("Play Selected Card");
        btnPlay.setOnAction(e -> {
            selectedCard = lstCardChoices.getFocusModel().getFocusedItem();
            if (selectedCard == null) {
                topDeck();
            }
            this.close();
        });

        HBox hboxStackInfo = new HBox();

        String strStackType = "";
        
        switch (game.getStack().getStackType()) {
            case DRAW:
                strStackType = "Draw";
                break;
            case PRESS:
                strStackType = "Press";
                break;
            case SPIN:
                strStackType = "Spin";
                break;
            default:
                break;
        }

        Text txtStackInfo = new Text("Current Stack: " + strStackType + " " + game.getStack().getStackMagnitude());
        txtStackInfo.setFill(Color.WHITESMOKE);
        txtStackInfo.setFont(new Font("Perpetua Bold Italic", 25));
        txtStackInfo.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        Text txtPlayableCardsInfo = new Text("Playable Cards: " + cardChoicesList.size());
        txtPlayableCardsInfo.setFill(Color.WHITESMOKE);
        txtPlayableCardsInfo.setFont(new Font("Perpetua Bold Italic", 25));
        txtPlayableCardsInfo.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        VBox vboxInfoText = new VBox(25);
        vboxInfoText.setPadding(new Insets(25, 0, 25, 0));
        vboxInfoText.setAlignment(Pos.CENTER);
        vboxInfoText.getChildren().addAll(txtStackInfo, txtPlayableCardsInfo);


        hboxStackInfo.getChildren().addAll(vboxInfoText);
        hboxStackInfo.setAlignment(Pos.CENTER);
        hboxStackInfo.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        VBox vboxButtons = new VBox();
        vboxButtons.getChildren().addAll(btnAcceptStack, btnTopDeck, btnPlay);
        vboxButtons.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));

        stackChoicePane.setTop(hboxStackInfo);
        stackChoicePane.setCenter(lstCardChoices);
        stackChoicePane.setRight(vboxButtons);
        
        Scene mainScene = new Scene(stackChoicePane);
        this.setScene(mainScene);

    }

}
