package com.example;

import com.example.Deck.DeckType;
import com.example.Tower.TowerPosition;
import com.example.tableDisplay.BottomUserDisplay;
import com.example.tableDisplay.CenterDisplay;
import com.example.tableDisplay.TopBotDisplay;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameInnerPane extends VBox {
    private GamePane outerPane;

    private BottomUserDisplay bottomDisplay;
    private CenterDisplay centerDisplay;
    private TopBotDisplay topDisplay;

    private int numPlayers;

    // // private Button btnSortLightColor;
    // // private Button btnSortDarkColor;
    // // private Button btnSortLightValue;
    // // private Button btnSortDarkValue;

    // private Button btnSortColor;
    // private Button btnSortValue;

    // private Button btnDraw;
    // private Button btnPlayCard;

    private Game game;

    // private Button btnViewMode;

    // private CheckBox chkViewDarkMode;

    

    //private TextArea txtPlayerScores;
    // private UserStatsDisplay txtPlayerInfo;
    // private BotStatsDisplay txtBot3InfoPlayer4;
    // private BotStatsDisplay txtBot1InfoPlayer2;
    // private BotStatsDisplay txtBot2InfoPlayer3;

    // private Label lblWhoseTurn;
    // private Label lblRoundNum;


    // private ListView<Card> lstPlayerHand1; // TODO rename to lstUserHand after debugging

    // private CardDisplay imgDeckCard;
    // private HandDisplay imgBotCard3Player4;
    // private HandDisplay imgBotCard1Player2;
    // private HandDisplay imgBotCard2Player3;

    // private HBox hboxSortButtons;
    // private VBox vboxInfoSortButtons;
    // private VBox vboxPlayViewModeButtons;
    // private HBox hboxControlsPlayer1;
    // private VBox vboxDrawArea;
    // private HBox hboxPlayArea;
    // private VBox vboxBotArea1;
    // private VBox vboxBotArea2;
    // private HBox hboxBotArea3;
    // private VBox vboxRoundInfo;

    public Point2D getPlayerPositionInScene(int playerNum, int numOfPlayers) {
        if (numOfPlayers == 2 && playerNum == 2) {
            return topDisplay.getHandDisplayPosition();
        }
        else if (playerNum == 3) {
            return topDisplay.getHandDisplayPosition();
        }
        else if (playerNum == 1) {
            return bottomDisplay.getHandDisplayPosition();
        }
        else {
            return centerDisplay.getHandDisplayPosition(playerNum, numOfPlayers);
        }
        
    }

    public Point2D getTowerPositionInScene(TowerPosition position) {
        return centerDisplay.getTowerPositionInScene(position);
    }

    public Point2D getDrawPilePosition() {
        return centerDisplay.getDrawPilePosition();
    }

    public void selectTower(TowerPosition position) {
        centerDisplay.selectTower(position);
    }

    public void refreshPlayerHand() {
        bottomDisplay.refreshPlayerHand();
    }

    public void setGameNull() {
        this.outerPane = null;
        bottomDisplay.clear();
        this.bottomDisplay = null;
        topDisplay.clear();
        this.topDisplay = null;
        centerDisplay.clear();
        this.centerDisplay = null;
        this.game = null;
    }

    // public void refreshPlayerHand() {
    //     lstPlayerHand1.setItems(game.getPlayer(1).getHand());
    // }

    public void refreshPane() {
        //refreshTurnLabel();

        //refreshPlayerScores();
        //refreshRoundLabel();
        //refreshPlayerHand();
        //towerPane.refresh();
        centerDisplay.refresh();
        topDisplay.refresh();
        bottomDisplay.refresh();

        // txtPlayerInfo.updateDisplay();
        // if (numPlayers == 2) {
        //     imgBotCard2Player3.updateDisplay(); //Two player game uses the display usually for player 3
        //     txtBot2InfoPlayer3.updateDisplay();
        // }
        // else if (numPlayers == 3) {
        //     imgBotCard1Player2.updateDisplay();
        //     txtBot1InfoPlayer2.updateDisplay();

        //     imgBotCard2Player3.updateDisplay();
        //     txtBot2InfoPlayer3.updateDisplay();
        // }
        // else {
        //     imgBotCard1Player2.updateDisplay();
        //     txtBot1InfoPlayer2.updateDisplay();

        //     imgBotCard2Player3.updateDisplay();
        //     txtBot2InfoPlayer3.updateDisplay();

        //     imgBotCard3Player4.updateDisplay();
        //     txtBot3InfoPlayer4.updateDisplay();
        // }
    }

    public Game getGame() {
        return this.game;
    }

    public Card getSelectedCard() {
        return bottomDisplay.getSelectedCard();
    }

    // private void refreshTurnLabel() {
    //     lblWhoseTurn.setText("Whose turn: Player " + game.getCurrentPlayer().getPlayerNum());
    // }
    // private void refreshRoundLabel() {
    //     lblRoundNum.setText("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds());
    // }

    // private void refreshPlayerScores() {
    //     String scoreString = new String();
    //     int i = 1;
    //     for (Player player : game.getPlayers()) {
    //         scoreString = scoreString + "Player " + i + " : " + player.getScore() + "\n";
    //         i++;
    //     }
    //     txtPlayerScores.setText(scoreString);
    // }

    // private void initializeNodeActions() {
    //     // chkViewDarkMode.setOnAction(e -> {
    //     //     game.setViewingDarkMode(chkViewDarkMode.isSelected());
    //     //     refreshListViews();
    //     //     lstPlayerHand1.refresh();
    //     //     lstPlayerHand2.refresh();
    //     //     if (numPlayers >= 3) {
    //     //         lstPlayerHand3.refresh();
    //     //     }
    //     //     if (numPlayers >= 4) {
    //     //         lstPlayerHand4.refresh();
    //     //     }
    //     // });

    //     // TODO fix this so that only the user's hand is playable once done debugging
    //     // Check if it is the user's turn/ waiting for user input
    //     btnPlayCard.setOnAction(e -> {


    //         if(game.getWaitingForUserToTakeTurn()) {
    //             game.setWaitingForUserToTakeTurn(false);
    //             Card selectedCard = getSelectedCard(); //TODO namely, fix this method

    //             boolean success = game.playCard(game.getUser(), game.getSelectedTower(), selectedCard);
    
    //             if (success) {
    //                 game.getUser().endTurn();
    //                 //refreshPane();
    //             }
    //             else {
    //                 game.setWaitingForUserToTakeTurn(true);
    //             }
    //         }
            

    //     });

    //     //TODO Fix this method to only let the user draw a card, not the current player
    //     // Check if it is the user's turn/ waiting for user input
    //     btnDraw.setOnAction(e -> {
    //         if (game.getWaitingForUserToTakeTurn()) {
    //             game.setWaitingForUserToTakeTurn(false);
    //             game.drawCard(game.getUser());
    //             game.getUser().endTurn();
    //         }
            
    //         //refreshPane();
    //     });

    //     // btnSortDarkColor.setOnAction(e -> {
    //     //     lstPlayerHand1.getItems().sort(new CardDarkColorComparator());
    //     //     lstPlayerHand2.getItems().sort(new CardDarkColorComparator());
    //     //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardDarkColorComparator());
    //     //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardDarkColorComparator());
    //     // });

    //     // btnSortLightColor.setOnAction(e -> {
    //     //     lstPlayerHand1.getItems().sort(new CardLightColorComparator());
    //     //     lstPlayerHand2.getItems().sort(new CardLightColorComparator());
    //     //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardLightColorComparator());
    //     //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardLightColorComparator());
    //     // });

    //     // btnSortDarkValue.setOnAction(e -> {
    //     //     lstPlayerHand1.getItems().sort(new CardDarkValueComparator());
    //     //     lstPlayerHand2.getItems().sort(new CardDarkValueComparator());
    //     //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardDarkValueComparator());
    //     //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardDarkValueComparator());
    //     // });

    //     // btnSortLightValue.setOnAction(e -> {
    //     //     lstPlayerHand1.getItems().sort(new CardLightValueComparator());
    //     //     lstPlayerHand2.getItems().sort(new CardLightValueComparator());
    //     //     if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardLightValueComparator());
    //     //     if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardLightValueComparator());
    //     // });

    //     // btnViewMode.setOnAction(e -> {
    //     //     game.setViewingDarkMode(!game.getViewingDarkMode());
    //     //     refreshPlayerHand();
    //     //     lstPlayerHand1.refresh();
    //     //     if (game.getViewingDarkMode()) {
    //     //         btnViewMode.setText("View Light Mode");
    //     //     }
    //     //     else {
    //     //         btnViewMode.setText("View Dark Mode");
    //     //     }
    //     // });

    //     // btnSortColor.setOnAction(e -> {
    //     //     if (game.getViewingDarkMode()) {
    //     //         lstPlayerHand1.getItems().sort(new CardDarkColorComparator());
    //     //     }
    //     //     else {
    //     //         lstPlayerHand1.getItems().sort(new CardLightColorComparator());
    //     //     }
    //     // });

    //     // btnSortValue.setOnAction(e -> {
    //     //     if (game.getViewingDarkMode()) {
    //     //         lstPlayerHand1.getItems().sort(new CardDarkValueComparator());
    //     //     }
    //     //     else {
    //     //         lstPlayerHand1.getItems().sort(new CardLightValueComparator());
    //     //     }
    //     // });

    // }

    public GameInnerPane(int numOfRounds, int numOfPlayers, DeckType deckType, String userName, GamePane outerPane) {
        super();

        this.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        
        this.outerPane = outerPane;
        this.numPlayers = numOfPlayers;
        game = new Game(numOfRounds, numOfPlayers, deckType, userName, outerPane);

        // btnDraw = new Button("Draw");
        // btnDraw.setMinSize(100, 50);
        // btnDraw.setPrefSize(100, 50);
        // btnDraw.setMaxSize(100, 50);

        

        // chkViewDarkMode = new CheckBox("Dark Mode");
        // chkViewDarkMode.setSelected(false);
        // chkViewDarkMode.setAllowIndeterminate(false);
        // chkViewDarkMode.selectedProperty().bindBidirectional(game.getViewingDarkModeProperty());

       

        
        

        // lstPlayerHand1.setMinSize(200, 200);
        // lstPlayerHand1.setPrefSize(800, 200);
        // lstPlayerHand1.setMaxSize(Double.MAX_VALUE, 200);

        

        // txtPlayerScores = new TextArea();
        // txtPlayerScores.setEditable(false);

        // TODO: add the num of cards each player has
        // txtPlayerInfo = new TextArea("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds() + 
        //                              "\n\nYou: \n Cards: " + "\n Points: " + game.getPlayer(1).getScore());
        // txtPlayerInfo.setEditable(false);
        // txtPlayerInfo.setMinSize(200, 100);
        // txtPlayerInfo.setPrefSize(200, 100);
        // txtPlayerInfo.setMaxSize(200, 100);

        // txtPlayerInfo = new UserStatsDisplay(game);


        //Two player game uses the display usually for player 3
        // if (numOfPlayers == 2) {
        //     // txtBot2InfoPlayer3 = new TextArea("Mike (Bot): \n Cards: " + "\n Points: " + game.getPlayer(2).getScore());
        //     // txtBot2InfoPlayer3.setEditable(false);
        //     // txtBot2InfoPlayer3.setMinSize(150, 65);
        //     // txtBot2InfoPlayer3.setPrefSize(150, 65);
        //     // txtBot2InfoPlayer3.setMaxSize(150, 65);

        //     txtBot2InfoPlayer3 = new BotStatsDisplay(game, 2);
        // }
        // else if (numOfPlayers == 3) {

        //     // txtBot1InfoPlayer2 = new TextArea("John (Bot): \n Cards: " + "\n Points: " + game.getPlayer(4).getScore());
        //     // txtBot1InfoPlayer2.setEditable(false);
        //     // txtBot1InfoPlayer2.setMinSize(150, 65);
        //     // txtBot1InfoPlayer2.setPrefSize(150, 65);
        //     // txtBot1InfoPlayer2.setMaxSize(150, 65);

        //     // txtBot2InfoPlayer3 = new TextArea("Mike (Bot): \n Cards: " + "\n Points: " + game.getPlayer(2).getScore());
        //     // txtBot2InfoPlayer3.setEditable(false);
        //     // txtBot2InfoPlayer3.setMinSize(150, 65);
        //     // txtBot2InfoPlayer3.setPrefSize(150, 65);
        //     // txtBot2InfoPlayer3.setMaxSize(150, 65);
        //     txtBot1InfoPlayer2 = new BotStatsDisplay(game, 2);
        //     txtBot2InfoPlayer3 = new BotStatsDisplay(game, 3);
        // }
        // else {

        //     // txtBot1InfoPlayer2 = new TextArea("John (Bot): \n Cards: " + "\n Points: " + game.getPlayer(4).getScore());
        //     // txtBot1InfoPlayer2.setEditable(false);
        //     // txtBot1InfoPlayer2.setMinSize(150, 65);
        //     // txtBot1InfoPlayer2.setPrefSize(150, 65);
        //     // txtBot1InfoPlayer2.setMaxSize(150, 65);

        //     // txtBot2InfoPlayer3 = new TextArea("Mike (Bot): \n Cards: " + "\n Points: " + game.getPlayer(2).getScore());
        //     // txtBot2InfoPlayer3.setEditable(false);
        //     // txtBot2InfoPlayer3.setMinSize(150, 65);
        //     // txtBot2InfoPlayer3.setPrefSize(150, 65);
        //     // txtBot2InfoPlayer3.setMaxSize(150, 65);

        //     // txtBot3InfoPlayer4 = new TextArea("Steve (Bot): \n Cards: " + "\n Points: " + game.getPlayer(3).getScore());
        //     // txtBot3InfoPlayer4.setEditable(false);
        //     // txtBot3InfoPlayer4.setMinSize(150, 65);
        //     // txtBot3InfoPlayer4.setPrefSize(150, 65);
        //     // txtBot3InfoPlayer4.setMaxSize(150, 65);
        //     txtBot1InfoPlayer2 = new BotStatsDisplay(game, 2);
        //     txtBot2InfoPlayer3 = new BotStatsDisplay(game, 3);
        //     txtBot3InfoPlayer4 = new BotStatsDisplay(game, 4);
        // }


        

        // lblWhoseTurn = new Label("Whose turn: Player " + game.getCurrentPlayer().getPlayerNum());
        // lblRoundNum = new Label("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds());

        // towerPane = new TowerPane(game, 500);
        // towerPane.setAlignment(Pos.CENTER);

        

        // imgDeckCard = new CardDisplay();
        // imgDeckCard.scaleToHand();
 

        //Two player game uses the display usually for player 3
        // if (numOfPlayers == 2) {
        //     imgBotCard2Player3 = new HandDisplay(game, 2);
        //     imgBotCard2Player3.setRotate(180);
        //     imgBotCard2Player3.fitToSize(100, 70);
        // }
        // else if (numOfPlayers == 3) {
        //     imgBotCard1Player2 = new HandDisplay(game, 2);
        //     imgBotCard1Player2.setRotate(270);
        //     imgBotCard1Player2.fitToSize(100, 70);
    
        //     imgBotCard2Player3 = new HandDisplay(game, 3);
        //     imgBotCard2Player3.setRotate(180);
        //     imgBotCard2Player3.fitToSize(100, 70);
        // }
        // else {
        //     imgBotCard1Player2 = new HandDisplay(game, 2);
        //     imgBotCard1Player2.setRotate(270);
        //     imgBotCard1Player2.fitToSize(100, 70);
    
        //     imgBotCard2Player3 = new HandDisplay(game, 3);
        //     imgBotCard2Player3.setRotate(180);
        //     imgBotCard2Player3.fitToSize(100, 70);
    
        //     imgBotCard3Player4 = new HandDisplay(game, 4);
        //     imgBotCard3Player4.setRotate(90);
        //     imgBotCard3Player4.fitToSize(100, 70);
    
        // }

        
        //initializeNodeActions();

        

        

        //hboxControlsPlayer1 = new HBox();
        //hboxControlsPlayer1.getChildren().addAll(vboxInfoSortButtons, lstPlayerHand1, vboxPlayViewModeButtons);
        //hboxControlsPlayer1.setAlignment(Pos.BOTTOM_CENTER);
        //HBox.setHgrow(lstPlayerHand1, Priority.ALWAYS); 

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

        // vboxDrawArea = new VBox();
        // vboxDrawArea.getChildren().addAll(imgDeckCard, btnDraw);
        // vboxDrawArea.setAlignment(Pos.CENTER);
        // vboxDrawArea.setSpacing(25);

        // hboxPlayArea = new HBox();
        // hboxPlayArea.getChildren().addAll(towerPane, vboxDrawArea);
        // hboxPlayArea.setAlignment(Pos.CENTER);
        // hboxPlayArea.setSpacing(50);

        // VBox miscBox = new VBox();
        // miscBox.getChildren().addAll(lblRoundNum, lblWhoseTurn, txtPlayerScores);

        // VBox vboxDeck = new VBox();
        // vboxDeck.getChildren().addAll(new Label("Deck Listing"), lstDeck);

        // VBox vboxBurn = new VBox();
        // vboxBurn.getChildren().addAll(new Label("Burn Pile"), lstBurn);

        // HBox hboxDeckInfo = new HBox();
        // hboxDeckInfo.getChildren().addAll(vboxDeck, vboxBurn);

        // if (numOfPlayers >= 3) {
        //     vboxBotArea1 = new VBox();
        //     vboxBotArea1.getChildren().addAll(txtBot3InfoPlayer4, imgBotCard3Player4);
        //     vboxBotArea1.setAlignment(Pos.CENTER);
        //     vboxBotArea1.setSpacing(25);
        //     vboxBotArea1.setPadding(new Insets(0, 10, 100, 10));
        // }
        
        // if (numOfPlayers >= 4) {
        //     vboxBotArea2 = new VBox();
        //     vboxBotArea2.getChildren().addAll(txtBot1InfoPlayer2, imgBotCard1Player2);
        //     vboxBotArea2.setAlignment(Pos.CENTER);
        //     vboxBotArea2.setSpacing(25);
        //     vboxBotArea2.setPadding(new Insets(0, 10, 100, 10));
        // }
        
        // hboxBotArea3 = new HBox();
        // hboxBotArea3.getChildren().addAll(imgBotCard2Player3, txtBot2InfoPlayer3);
        // hboxBotArea3.setAlignment(Pos.CENTER);
        // hboxBotArea3.setSpacing(50);
        // hboxBotArea3.setPadding(new Insets(10, 0, 10, 0));

        // vboxRoundInfo = new VBox();
        // vboxRoundInfo.getChildren().addAll(lblRoundNum, lblWhoseTurn);
        // vboxRoundInfo.setPadding(new Insets(10, 0, 0, 10));

        // BorderPane.setAlignment(hboxControlsPlayer1, Pos.BOTTOM_CENTER);
        // BorderPane.setAlignment(hboxBotArea3, Pos.TOP_CENTER);

        //this.setBottom(hboxControlsPlayer1);

        centerDisplay = new CenterDisplay(game);
        bottomDisplay = new BottomUserDisplay(game);
        //this.setCenter(hboxPlayArea);
        //this.setCenter(centerDisplay);
        //if (numOfPlayers >= 3) this.setLeft(vboxBotArea1);
        //if (numOfPlayers >= 3) this.setLeft(new LeftBotDisplay(game, 4));
        //if (numOfPlayers >= 4) this.setRight(vboxBotArea2);
        //if (numOfPlayers >= 4) this.setRight(new RightBotDisplay(game, 2));
        //this.setTop(hboxBotArea3);
        //this.setTop(new TopBotDisplay(game, 3));

        if (numOfPlayers == 2) {
            topDisplay = new TopBotDisplay(game, 2);
        }
        else {
            topDisplay = new TopBotDisplay(game, 3);
        }

        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(topDisplay, centerDisplay, bottomDisplay);

        //lblRoundNum.relocate(10, 10);

        //refreshPane();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        centerDisplay.setMinHeight(0.6 * height);
        centerDisplay.setMaxHeight(0.6 * height);
        bottomDisplay.setMinHeight(height * 0.2);
        bottomDisplay.setMaxHeight(height * 0.2);
        topDisplay.setMinHeight(height * 0.2);
        topDisplay.setMaxHeight(height * 0.2);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        centerDisplay.setMinWidth(width);
        centerDisplay.setMaxWidth(width);
        bottomDisplay.setMinWidth(width);
        bottomDisplay.setMaxWidth(width);
        topDisplay.setWidth(width);
        topDisplay.setMinWidth(width);
        topDisplay.setMaxWidth(width);
    }
}
