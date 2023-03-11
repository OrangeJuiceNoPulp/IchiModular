package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameInnerPane extends BorderPane {
    private GamePane outerPane;

    private int numPlayers;

    // private Button btnSortLightColor;
    // private Button btnSortDarkColor;
    // private Button btnSortLightValue;
    // private Button btnSortDarkValue;

    private Button btnSortColor;
    private Button btnSortValue;

    private Button btnDraw;
    private Button btnPlayCard;

    private Game game;

    private Button btnViewMode;

    // private CheckBox chkViewDarkMode;

    private TowerPane towerPane;

    private TextArea txtPlayerScores;
    private TextArea txtPlayerInfo;
    private TextArea txtBotInfo1;
    private TextArea txtBotInfo2;
    private TextArea txtBotInfo3;

    private Label lblWhoseTurn;
    private Label lblRoundNum;

    private ListView<Card> lstDeck; // TODO remove after debugging
    private ListView<Card> lstBurn; // TODO remove after debugging

    private ListView<Card> lstPlayerHand1; // TODO rename to lstUserHand after debugging
    private ListView<Card> lstPlayerHand2; // TODO remove after debugging
    private ListView<Card> lstPlayerHand3; // TODO remove after debugging
    private ListView<Card> lstPlayerHand4; // TODO remove after debugging

    private Image cardBack;
    private ImageView imgDeckCard;
    private ImageView imgBotCard1;
    private ImageView imgBotCard2;
    private ImageView imgBotCard3;

    private HBox hboxSortButtons;
    private VBox vboxInfoSortButtons;
    private VBox vboxPlayViewModeButtons;
    private HBox hboxControlsPlayer1;
    private VBox vboxDrawArea;
    private HBox hboxPlayArea;
    private VBox vboxBotArea1;
    private VBox vboxBotArea2;
    private HBox hboxBotArea3;
    private VBox vboxRoundInfo;

    public void refreshListViews() {
        lstPlayerHand1.setItems(game.getPlayer(1).getHand());
        lstPlayerHand2.setItems(game.getPlayer(2).getHand());
        if (numPlayers >= 3) lstPlayerHand3.setItems(game.getPlayer(3).getHand());
        if (numPlayers >= 4) lstPlayerHand4.setItems(game.getPlayer(4).getHand());
    }

    public void refreshPane() {
        refreshTurnLabel();
        refreshPlayerScores();
        refreshRoundLabel();
        lstDeck.refresh();
        lstBurn.refresh();
        refreshListViews();
        towerPane.refresh();
    }

    public Game getGame() {
        return this.game;
    }

    public Card getSelectedCard() {
        int whoseTurn = game.getCurrentPlayer().getPlayerNum();
        Card selectedCard;
        if (whoseTurn == 2) {
            selectedCard = lstPlayerHand2.getFocusModel().getFocusedItem();
        } else if (whoseTurn == 3) {
            selectedCard = lstPlayerHand3.getFocusModel().getFocusedItem();
        } else if (whoseTurn == 4) {
            selectedCard = lstPlayerHand4.getFocusModel().getFocusedItem();
        } else {
            selectedCard = lstPlayerHand1.getFocusModel().getFocusedItem();
        }
        return selectedCard;
    }

    private void refreshTurnLabel() {
        lblWhoseTurn.setText("Whose turn: Player " + game.getCurrentPlayer().getPlayerNum());
    }
    private void refreshRoundLabel() {
        lblRoundNum.setText("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds());
    }

    private void refreshPlayerScores() {
        String scoreString = new String();
        int i = 1;
        for (Player player : game.getPlayers()) {
            scoreString = scoreString + "Player " + i + " : " + player.getScore() + "\n";
            i++;
        }
        txtPlayerScores.setText(scoreString);
    }

    private void initializeNodeActions() {
        // chkViewDarkMode.setOnAction(e -> {
        //     game.setViewingDarkMode(chkViewDarkMode.isSelected());
        //     refreshListViews();
        //     lstPlayerHand1.refresh();
        //     lstPlayerHand2.refresh();
        //     if (numPlayers >= 3) {
        //         lstPlayerHand3.refresh();
        //     }
        //     if (numPlayers >= 4) {
        //         lstPlayerHand4.refresh();
        //     }
        // });

        // TODO fix this so that only the user's hand is playable once done debugging
        btnPlayCard.setOnAction(e -> {

            Card selectedCard = getSelectedCard(); //TODO namely, fix this method

            boolean success = game.playCard(game.getCurrentPlayer(), game.getSelectedTower(), selectedCard);

            if (success) {
                game.getCurrentPlayer().endTurn();
                refreshPane();
            }

        });

        btnDraw.setOnAction(e -> {
            game.drawCard(game.getCurrentPlayer());
            lstDeck.setItems(game.getDeck().getPile());
            lstBurn.setItems(game.getDeck().getBurn());
            game.getCurrentPlayer().endTurn();
            refreshPane();
        });

        // btnSortDarkColor.setOnAction(e -> {
        //     lstPlayerHand1.getItems().sort(new CardDarkColorComparator());
        //     lstPlayerHand2.getItems().sort(new CardDarkColorComparator());
        //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardDarkColorComparator());
        //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardDarkColorComparator());
        // });

        // btnSortLightColor.setOnAction(e -> {
        //     lstPlayerHand1.getItems().sort(new CardLightColorComparator());
        //     lstPlayerHand2.getItems().sort(new CardLightColorComparator());
        //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardLightColorComparator());
        //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardLightColorComparator());
        // });

        // btnSortDarkValue.setOnAction(e -> {
        //     lstPlayerHand1.getItems().sort(new CardDarkValueComparator());
        //     lstPlayerHand2.getItems().sort(new CardDarkValueComparator());
        //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardDarkValueComparator());
        //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardDarkValueComparator());
        // });

        // btnSortLightValue.setOnAction(e -> {
        //     lstPlayerHand1.getItems().sort(new CardLightValueComparator());
        //     lstPlayerHand2.getItems().sort(new CardLightValueComparator());
        //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardLightValueComparator());
        //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardLightValueComparator());
        // });

        btnViewMode.setOnAction(e -> {
            game.setViewingDarkMode(!game.getViewingDarkMode());
            refreshListViews();
            lstPlayerHand1.refresh();
            if (game.getViewingDarkMode()) {
                btnViewMode.setText("View Light Mode");
            }
            else {
                btnViewMode.setText("View Dark Mode");
            }
        });

        btnSortColor.setOnAction(e -> {
            if (game.getViewingDarkMode()) {
                lstPlayerHand1.getItems().sort(new CardDarkColorComparator());
            }
            else {
                lstPlayerHand1.getItems().sort(new CardLightColorComparator());
            }
        });

        btnSortValue.setOnAction(e -> {
            if (game.getViewingDarkMode()) {
                lstPlayerHand1.getItems().sort(new CardDarkValueComparator());
            }
            else {
                lstPlayerHand1.getItems().sort(new CardLightValueComparator());
            }
        });

    }

    public GameInnerPane(int numOfRounds, int numOfPlayers, GamePane outerPane) {
        super();

        this.outerPane = outerPane;
        this.numPlayers = numOfPlayers;
        game = new Game(numOfRounds, numOfPlayers, outerPane);

        btnDraw = new Button("Draw");
        btnDraw.setMinSize(100, 50);
        btnDraw.setPrefSize(100, 50);
        btnDraw.setMaxSize(100, 50);

        btnPlayCard = new Button("Play");
        btnPlayCard.setMinSize(150, 100);
        btnPlayCard.setPrefSize(150, 100);
        btnPlayCard.setMaxSize(150, 100);

        btnViewMode = new Button("View Dark Mode");
        btnViewMode.setMinSize(150, 50);
        btnViewMode.setPrefSize(150, 50);
        btnViewMode.setMaxSize(150, 50);

        // chkViewDarkMode = new CheckBox("Dark Mode");
        // chkViewDarkMode.setSelected(false);
        // chkViewDarkMode.setAllowIndeterminate(false);
        // chkViewDarkMode.selectedProperty().bindBidirectional(game.getViewingDarkModeProperty());

        lstDeck = new ListView<Card>(game.getDeck().getPile());
        lstBurn = new ListView<Card>(game.getDeck().getBurn());

        lstPlayerHand1 = new ListView<Card>(game.getPlayer(1).getHand());
        lstPlayerHand2 = new ListView<Card>(game.getPlayer(2).getHand());
        if (numPlayers >= 3) lstPlayerHand3 = new ListView<Card>(game.getPlayer(3).getHand());
        if (numPlayers >= 4) lstPlayerHand4 = new ListView<Card>(game.getPlayer(4).getHand());

        lstPlayerHand1.setCellFactory(new CardCellFactory());
        lstPlayerHand2.setCellFactory(new CardCellFactory());
        if (numPlayers >= 3) lstPlayerHand3.setCellFactory(new CardCellFactory());
        if (numPlayers >= 4) lstPlayerHand4.setCellFactory(new CardCellFactory());

        lstPlayerHand1.setOrientation(Orientation.HORIZONTAL);
        lstPlayerHand1.getFocusModel().focus(0);
        lstPlayerHand2.setOrientation(Orientation.HORIZONTAL);
        lstPlayerHand2.getFocusModel().focus(0);
        if (numPlayers >= 3) lstPlayerHand3.setOrientation(Orientation.HORIZONTAL);
        if (numPlayers >= 3) lstPlayerHand3.getFocusModel().focus(0);
        if (numPlayers >= 4) lstPlayerHand4.setOrientation(Orientation.HORIZONTAL);
        if (numPlayers >= 4) lstPlayerHand4.getFocusModel().focus(0);

        // lstPlayerHand1.setMinSize(200, 200);
        // lstPlayerHand1.setPrefSize(800, 200);
        // lstPlayerHand1.setMaxSize(Double.MAX_VALUE, 200);

        lstPlayerHand1.setMinHeight(150);
        lstPlayerHand1.setPrefHeight(150);
        lstPlayerHand1.setMaxHeight(150);

        txtPlayerScores = new TextArea();
        txtPlayerScores.setEditable(false);

        // TODO: add the num of cards each player has
        txtPlayerInfo = new TextArea("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds() + 
                                     "\n\nYou: \n Cards: " + "\n Points: " + game.getPlayer(1).getScore());
        txtPlayerInfo.setEditable(false);
        txtPlayerInfo.setMinSize(200, 100);
        txtPlayerInfo.setPrefSize(200, 100);
        txtPlayerInfo.setMaxSize(200, 100);

        if (numOfPlayers >= 3) {
            txtBotInfo1 = new TextArea("Steve (Bot): \n Cards: " + "\n Points: " + game.getPlayer(3).getScore());
            txtBotInfo1.setEditable(false);
            txtBotInfo1.setMinSize(150, 65);
            txtBotInfo1.setPrefSize(150, 65);
            txtBotInfo1.setMaxSize(150, 65);
        }
        
        if (numOfPlayers >= 4) {
            txtBotInfo2 = new TextArea("John (Bot): \n Cards: " + "\n Points: " + game.getPlayer(4).getScore());
            txtBotInfo2.setEditable(false);
            txtBotInfo2.setMinSize(150, 65);
            txtBotInfo2.setPrefSize(150, 65);
            txtBotInfo2.setMaxSize(150, 65);
        }

        txtBotInfo3 = new TextArea("Mike (Bot): \n Cards: " + "\n Points: " + game.getPlayer(2).getScore());
        txtBotInfo3.setEditable(false);
        txtBotInfo3.setMinSize(150, 65);
        txtBotInfo3.setPrefSize(150, 65);
        txtBotInfo3.setMaxSize(150, 65);

        lblWhoseTurn = new Label("Whose turn: Player " + game.getCurrentPlayer().getPlayerNum());
        lblRoundNum = new Label("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds());

        towerPane = new TowerPane(game);
        towerPane.setAlignment(Pos.CENTER);

        btnSortColor = new Button("Sort Color");
        btnSortColor.setMinSize(100, 50);
        btnSortColor.setPrefSize(100, 50);
        btnSortColor.setMaxSize(100, 50);

        btnSortValue = new Button("Sort Value");
        btnSortValue.setMinSize(100, 50);
        btnSortValue.setPrefSize(100, 50);
        btnSortValue.setMaxSize(100, 50);

        cardBack = new Image("D:/IchiModular-master/IchiModular-master/src/main/resources/com/example/aquaBackground.png", 70, 100, false, false);

        imgDeckCard = new ImageView();
        imgDeckCard.setImage(cardBack);

        imgBotCard1 = new ImageView();
        imgBotCard1.setImage(cardBack);
        imgBotCard1.setRotate(90);

        imgBotCard2 = new ImageView();
        imgBotCard2.setImage(cardBack);
        imgBotCard2.setRotate(270);

        imgBotCard3 = new ImageView();
        imgBotCard3.setImage(cardBack);
        imgBotCard3.setRotate(180);

        initializeNodeActions();

        hboxSortButtons = new HBox();
        hboxSortButtons.getChildren().addAll(btnSortColor, btnSortValue);

        vboxInfoSortButtons = new VBox();
        vboxInfoSortButtons.getChildren().addAll(txtPlayerInfo, hboxSortButtons);

        vboxPlayViewModeButtons = new VBox();
        vboxPlayViewModeButtons.getChildren().addAll(btnPlayCard, btnViewMode);

        hboxControlsPlayer1 = new HBox();
        hboxControlsPlayer1.getChildren().addAll(vboxInfoSortButtons, lstPlayerHand1, vboxPlayViewModeButtons);
        hboxControlsPlayer1.setAlignment(Pos.BOTTOM_CENTER);
        HBox.setHgrow(lstPlayerHand1, Priority.ALWAYS); 

        // VBox vboxPlayer1 = new VBox();
        // vboxPlayer1.getChildren().addAll(new Label("Player 1 Hand"), lstPlayerHand1);

        // VBox vboxPlayer2 = new VBox();
        // vboxPlayer1.getChildren().addAll(new Label("Player 2 Hand"), lstPlayerHand2);

        // VBox vboxPlayer3 = new VBox();
        // if (numPlayers >= 3) vboxPlayer1.getChildren().addAll(new Label("Player 3 Hand"), lstPlayerHand3);

        // VBox vboxPlayer4 = new VBox();
        // if (numPlayers >= 4) vboxPlayer1.getChildren().addAll(new Label("Player 4 Hand"), lstPlayerHand4);

        // VBox vboxOrganizeHandLayout = new VBox();
        // HBox hboxOrganizeHandLayout1 = new HBox();
        // HBox hboxOrganizeHandLayout2 = new HBox();
        // hboxOrganizeHandLayout1.getChildren().addAll(vboxPlayer1, vboxPlayer2);
        // if (numPlayers >= 3) {hboxOrganizeHandLayout2.getChildren().addAll(vboxPlayer3);}
        // else if (numPlayers >= 4) {hboxOrganizeHandLayout2.getChildren().addAll(vboxPlayer3, vboxPlayer4);}
        // vboxOrganizeHandLayout.getChildren().addAll(hboxOrganizeHandLayout1, hboxOrganizeHandLayout2);

        vboxDrawArea = new VBox();
        vboxDrawArea.getChildren().addAll(imgDeckCard, btnDraw);
        vboxDrawArea.setAlignment(Pos.CENTER);
        vboxDrawArea.setSpacing(25);

        hboxPlayArea = new HBox();
        hboxPlayArea.getChildren().addAll(towerPane, vboxDrawArea);
        hboxPlayArea.setAlignment(Pos.CENTER);
        hboxPlayArea.setSpacing(50);

        // VBox miscBox = new VBox();
        // miscBox.getChildren().addAll(lblRoundNum, lblWhoseTurn, txtPlayerScores);

        // VBox vboxDeck = new VBox();
        // vboxDeck.getChildren().addAll(new Label("Deck Listing"), lstDeck);

        // VBox vboxBurn = new VBox();
        // vboxBurn.getChildren().addAll(new Label("Burn Pile"), lstBurn);

        // HBox hboxDeckInfo = new HBox();
        // hboxDeckInfo.getChildren().addAll(vboxDeck, vboxBurn);

        if (numOfPlayers >= 3) {
            vboxBotArea1 = new VBox();
            vboxBotArea1.getChildren().addAll(txtBotInfo1, imgBotCard1);
            vboxBotArea1.setAlignment(Pos.CENTER);
            vboxBotArea1.setSpacing(25);
            vboxBotArea1.setPadding(new Insets(0, 10, 100, 10));
        }
        
        if (numOfPlayers >= 4) {
            vboxBotArea2 = new VBox();
            vboxBotArea2.getChildren().addAll(txtBotInfo2, imgBotCard2);
            vboxBotArea2.setAlignment(Pos.CENTER);
            vboxBotArea2.setSpacing(25);
            vboxBotArea2.setPadding(new Insets(0, 10, 100, 10));
        }
        
        hboxBotArea3 = new HBox();
        hboxBotArea3.getChildren().addAll(imgBotCard3, txtBotInfo3);
        hboxBotArea3.setAlignment(Pos.CENTER);
        hboxBotArea3.setSpacing(50);
        hboxBotArea3.setPadding(new Insets(10, 0, 10, 0));

        vboxRoundInfo = new VBox();
        vboxRoundInfo.getChildren().addAll(lblRoundNum, lblWhoseTurn);
        vboxRoundInfo.setPadding(new Insets(10, 0, 0, 10));

        BorderPane.setAlignment(hboxControlsPlayer1, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(hboxBotArea3, Pos.TOP_CENTER);

        this.setBottom(hboxControlsPlayer1);
        this.setCenter(hboxPlayArea);
        if (numOfPlayers >= 3) this.setLeft(vboxBotArea1);
        if (numOfPlayers >= 4) this.setRight(vboxBotArea2);
        this.setTop(hboxBotArea3);

        lblRoundNum.relocate(10, 10);

        refreshPane();
    }
}
