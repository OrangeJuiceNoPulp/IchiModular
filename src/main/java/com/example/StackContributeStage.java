package com.example;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StackContributeStage extends Stage {
    protected static final int topDeckPenalty = 50;

    private Card selectedCard;
    private Game game;
    private Player player;

    public Card getStackedCard() {
        return this.selectedCard;
    }

    // The player "top decks" (For 50 points, they draw the card from the top of the deck. 
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

            Button btnOK = new Button("OK");
            btnOK.setOnAction(e -> {
                notEnoughPointsStage.close();
            });
            btnOK.setMinWidth(100);

            HBox hboxBtnOK = new HBox(btnOK);
            hboxBtnOK.setAlignment(Pos.CENTER);

            VBox vboxText = new VBox();
            vboxText.setAlignment(Pos.CENTER);
            vboxText.getChildren().addAll(new Text(""),
            new Text("   You do not have enough points to top deck.   "), 
            new Text(""),
            new Text("You will now accept the stack instead."),
            new Text(""));

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
        lstCardChoices.setMaxHeight(300);
        lstCardChoices.setMinHeight(300);
        lstCardChoices.setMaxWidth(750);
        lstCardChoices.setMinWidth(750);

        Button btnAcceptStack = new Button();
        Button btnTopDeck = new Button();
        Button btnPlay = new Button();

        btnAcceptStack.setMinHeight(100);
        btnAcceptStack.setMinWidth(150);

        btnTopDeck.setMinHeight(100);
        btnTopDeck.setMinWidth(150);

        btnPlay.setMinHeight(100);
        btnPlay.setMinWidth(150);

    
        btnAcceptStack.setText("Accept Stack");
        btnAcceptStack.setOnAction(e -> {
            this.close();
        });

        btnTopDeck.setText("Top Deck\n(-50 Points)");
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

        hboxStackInfo.getChildren().addAll(txtStackInfo);
        hboxStackInfo.setAlignment(Pos.CENTER);

        VBox vboxButtons = new VBox();
        vboxButtons.getChildren().addAll(btnAcceptStack, btnTopDeck, btnPlay);

        stackChoicePane.setTop(hboxStackInfo);
        stackChoicePane.setCenter(lstCardChoices);
        stackChoicePane.setRight(vboxButtons);
        
        Scene mainScene = new Scene(stackChoicePane);
        this.setScene(mainScene);

    }

}
