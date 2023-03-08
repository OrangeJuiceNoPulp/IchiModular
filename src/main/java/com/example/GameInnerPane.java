package com.example;



import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameInnerPane extends BorderPane {
    private GamePane outerPane;

    private int numPlayers;

    private Button btnSortLightColor;
    private Button btnSortDarkColor;
    private Button btnSortLightValue;
    private Button btnSortDarkValue;

    private Button btnDraw;
    private Button btnPlayCard;

    private Game game;

    private CheckBox chkViewDarkMode;

    private TowerPane towerPane;

    private TextArea txtPlayerScores;

    private Label lblWhoseTurn;
    private Label lblRoundNum;

    private ListView<Card> lstDeck; // TODO remove after debugging
    private ListView<Card> lstBurn; // TODO remove after debugging

    private ListView<Card> lstPlayerHand1; // TODO rename to lstUserHand after debugging
    private ListView<Card> lstPlayerHand2; // TODO remove after debugging
    private ListView<Card> lstPlayerHand3; // TODO remove after debugging
    private ListView<Card> lstPlayerHand4; // TODO remove after debugging

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
        chkViewDarkMode.setOnAction(e -> {
            game.setViewingDarkMode(chkViewDarkMode.isSelected());
            refreshListViews();
            lstPlayerHand1.refresh();
            lstPlayerHand2.refresh();
            if (numPlayers >= 3) {
                lstPlayerHand3.refresh();
            }
            if (numPlayers >= 4) {
                lstPlayerHand4.refresh();
            }
        });

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

        btnSortDarkColor.setOnAction(e -> {
            lstPlayerHand1.getItems().sort(new CardDarkColorComparator());
            lstPlayerHand2.getItems().sort(new CardDarkColorComparator());
            if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardDarkColorComparator());
            if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardDarkColorComparator());
        });

        btnSortLightColor.setOnAction(e -> {
            lstPlayerHand1.getItems().sort(new CardLightColorComparator());
            lstPlayerHand2.getItems().sort(new CardLightColorComparator());
            if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardLightColorComparator());
            if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardLightColorComparator());
        });

        btnSortDarkValue.setOnAction(e -> {
            lstPlayerHand1.getItems().sort(new CardDarkValueComparator());
            lstPlayerHand2.getItems().sort(new CardDarkValueComparator());
            if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardDarkValueComparator());
            if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardDarkValueComparator());
        });

        btnSortLightValue.setOnAction(e -> {
            lstPlayerHand1.getItems().sort(new CardLightValueComparator());
            lstPlayerHand2.getItems().sort(new CardLightValueComparator());
            if (numPlayers >= 3) lstPlayerHand3.getItems().sort(new CardLightValueComparator());
            if (numPlayers >= 4) lstPlayerHand4.getItems().sort(new CardLightValueComparator());
        });

    }

    public GameInnerPane(int numOfRounds, int numOfPlayers, GamePane outerPane) {
        super();
        this.setPrefSize(1000, 500);

        this.outerPane = outerPane;
        this.numPlayers = numOfPlayers;
        game = new Game(numOfRounds, numOfPlayers, outerPane);

        btnDraw = new Button("Draw");
        btnPlayCard = new Button("Play");

        chkViewDarkMode = new CheckBox("Dark Mode");
        chkViewDarkMode.setSelected(false);
        chkViewDarkMode.setAllowIndeterminate(false);
        chkViewDarkMode.selectedProperty().bindBidirectional(game.getViewingDarkModeProperty());

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

        txtPlayerScores = new TextArea();
        txtPlayerScores.setEditable(false);

        lblWhoseTurn = new Label("Whose turn: Player " + game.getCurrentPlayer().getPlayerNum());
        lblRoundNum = new Label("Round " + game.getRoundNumber() + " of " + game.getNumberOfRounds());

        towerPane = new TowerPane(game);

        btnSortDarkColor = new Button("Sort Dark Color");
        btnSortDarkValue = new Button("Sort Dark Value");
        btnSortLightColor = new Button("Sort Light Color");
        btnSortLightValue = new Button("Sort Light Value");

        initializeNodeActions();

        VBox vboxDarkSortButtons = new VBox();
        vboxDarkSortButtons.getChildren().addAll(btnSortDarkColor, btnSortDarkValue);

        VBox vboxLightSortButtons = new VBox();
        vboxLightSortButtons.getChildren().addAll(btnSortLightColor, btnSortLightValue);

        HBox hboxControlsPlayer1 = new HBox();
        hboxControlsPlayer1.getChildren().addAll(btnDraw, btnPlayCard, vboxLightSortButtons, vboxDarkSortButtons,
                chkViewDarkMode);

        VBox vboxPlayer1 = new VBox();
        vboxPlayer1.getChildren().addAll(new Label("Player 1 Hand"), lstPlayerHand1);

        VBox vboxPlayer2 = new VBox();
        vboxPlayer1.getChildren().addAll(new Label("Player 2 Hand"), lstPlayerHand2);

        VBox vboxPlayer3 = new VBox();
        if (numPlayers >= 3) vboxPlayer1.getChildren().addAll(new Label("Player 3 Hand"), lstPlayerHand3);

        VBox vboxPlayer4 = new VBox();
        if (numPlayers >= 4) vboxPlayer1.getChildren().addAll(new Label("Player 4 Hand"), lstPlayerHand4);

        VBox vboxOrganizeHandLayout = new VBox();
        HBox hboxOrganizeHandLayout1 = new HBox();
        HBox hboxOrganizeHandLayout2 = new HBox();
        hboxOrganizeHandLayout1.getChildren().addAll(vboxPlayer1, vboxPlayer2);
        if (numPlayers >= 3) {hboxOrganizeHandLayout2.getChildren().addAll(vboxPlayer3);}
        else if (numPlayers >= 4) {hboxOrganizeHandLayout2.getChildren().addAll(vboxPlayer3, vboxPlayer4);}
        vboxOrganizeHandLayout.getChildren().addAll(hboxOrganizeHandLayout1, hboxOrganizeHandLayout2);

        VBox vboxPlayArea = new VBox();
        vboxPlayArea.getChildren().addAll(new Label("Play Area"), towerPane);

        VBox miscBox = new VBox();
        miscBox.getChildren().addAll(lblRoundNum, lblWhoseTurn, txtPlayerScores);

        VBox vboxDeck = new VBox();
        vboxDeck.getChildren().addAll(new Label("Deck Listing"), lstDeck);

        VBox vboxBurn = new VBox();
        vboxBurn.getChildren().addAll(new Label("Burn Pile"), lstBurn);

        HBox hboxDeckInfo = new HBox();
        hboxDeckInfo.getChildren().addAll(vboxDeck, vboxBurn);

        this.setBottom(hboxControlsPlayer1);
        this.setCenter(vboxPlayArea);
        this.setLeft(vboxOrganizeHandLayout);
        this.setRight(hboxDeckInfo);
        this.setTop(miscBox);

        refreshPane();
    }
}
