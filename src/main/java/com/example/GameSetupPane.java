package com.example;

import com.example.CustomRadioButtonDisplay.Position;
import com.example.Deck.DeckType;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameSetupPane extends StackPane {
    private boolean doneInitializing = false;

    private Button btnHome;
    private Button btnStartGame;

    private CustomRadioButtonDisplay leftDisplay;
    private CustomRadioButtonDisplay rightDisplay;

    private VBox bottomDisplay;

    private CustomCheckBox chkMusic;
    private CustomCheckBox chkSoundEffects;

    private TextField txtEnterPlayerName;
    private ComboBox<Deck.DeckType> cboGameDeck;

    private DeckType getDeckType() {
        return cboGameDeck.getSelectionModel().getSelectedItem();
    }

    private String getUserName() {
        int textLength = Math.min(12, txtEnterPlayerName.getText().length());
        String playerName = txtEnterPlayerName.getText(0, textLength);
        if (playerName.compareTo("") == 0) {
            playerName = new NameGenerator().getRandomName(1);
        }
        return playerName;
    }

    private boolean getMusicSelected() {
        return chkMusic.getIsChecked();
    }

    private boolean getSoundEffectsSelected() {
        return chkSoundEffects.getIsChecked();
    }

    public GameSetupPane(Stage stage) {
        super();
        

        BorderPane innerPane = new BorderPane();

        btnHome = new Button("Back");
        btnHome.setMinSize(getWidth() / 2, getHeight() / 4);
        btnHome.setMaxSize(getWidth() / 2, getHeight() / 4);
        
        btnHome.setOnAction(e -> {
            this.getChildren().clear();
            stage.setScene(new Scene(new HomeScreenPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });
        btnHome.setFont(new Font("Perpetua Bold Italic", Math.max(btnHome.getMinHeight() * 0.325, 1)));
        btnHome.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2)))); ////Caused an error for some reason
        //btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        btnHome.setStyle("-fx-background-color: #808080");
        btnHome.setTextFill(Color.WHITESMOKE);
        btnHome.setOnMouseEntered(e -> {
            btnHome.setStyle("-fx-background-color: #202020");
        });
        btnHome.setOnMouseExited(e -> {
            btnHome.setStyle("-fx-background-color: #808080");
        });


        btnStartGame = new Button("Start");
        btnStartGame.setMinSize(getWidth() / 2, getHeight() / 4);
        btnStartGame.setMaxSize(getWidth() / 2, getHeight() / 4);

        btnStartGame.setFont(new Font("Perpetua Bold Italic", Math.max(btnStartGame.getMinHeight() * 0.325, 1)));
        btnStartGame.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        //btnStartGame.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0)))); //Caused an error for some reason
        btnStartGame.setStyle("-fx-background-color: #808080");
        btnStartGame.setTextFill(Color.WHITESMOKE);
        btnStartGame.setOnMouseEntered(e -> {
            btnStartGame.setStyle("-fx-background-color: #202020");
        });
        btnStartGame.setOnMouseExited(e -> {
            btnStartGame.setStyle("-fx-background-color: #808080");
        });

        btnStartGame.setOnAction(e -> {
            this.getChildren().clear();
            int numPlayers;
            int numRounds;

            switch (leftDisplay.getSelection()) {
                case TOP:
                    numPlayers = 2;
                    break;
                case CENTER:
                    numPlayers = 3;
                    break;
                case BOTTOM:
                    numPlayers = 4;
                    break;
                default:
                    numPlayers = 4;
                    break;
            }

            switch (rightDisplay.getSelection()) {
                case TOP:
                    numRounds = 1;
                    break;
                case CENTER:
                    numRounds = 3;
                    break;
                case BOTTOM:
                    numRounds = 5;
                    break;
                default:
                    numRounds = 3;
                    break;
            }

            GamePane gamePane = new GamePane(numRounds, numPlayers, stage, getMusicSelected(), getSoundEffectsSelected(), getDeckType(), getUserName());
            stage.setScene(new Scene(gamePane, stage.getScene().getWidth(), stage.getScene().getHeight()));
            gamePane.refreshGameTable();
            gamePane.startGame();
        });



        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(btnStartGame, btnHome);
        buttonsBox.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));





        leftDisplay = new CustomRadioButtonDisplay("Players", "2 Players", "3 Players", "4 Players", Position.BOTTOM);
        rightDisplay = new CustomRadioButtonDisplay("Rounds", "1 Round", "3 Rounds", "5 Rounds", Position.CENTER);

        bottomDisplay = new VBox();
        bottomDisplay.setAlignment(Pos.CENTER);

        chkMusic = new CustomCheckBox(true, "Background Music");
        chkSoundEffects = new CustomCheckBox(true, "Sound Effects");

        chkMusic.setMinHeight(30);
        chkMusic.setMaxHeight(30);

        chkSoundEffects.setMinHeight(30);
        chkSoundEffects.setMaxHeight(30);

        

        HBox hboxPlayerName = new HBox();
        Text txtPlayerName = new Text("Player Name: ");
        txtPlayerName.setFill(Color.WHITESMOKE);
        txtPlayerName.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        txtPlayerName.setFont(new Font("Perpetua Bold Italic", 25));

        txtEnterPlayerName = new TextField("Jordan");
        txtEnterPlayerName.setMinWidth(150);
        txtEnterPlayerName.setMaxWidth(150);
        txtEnterPlayerName.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        txtEnterPlayerName.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        txtEnterPlayerName.setFont(new Font("Perpetua Bold Italic", 18));
        txtEnterPlayerName.setStyle("-fx-text-inner-color: #FFFFFF");
        
        hboxPlayerName.setAlignment(Pos.CENTER_LEFT);
        hboxPlayerName.getChildren().addAll(txtPlayerName, txtEnterPlayerName);

        HBox hboxGameDeck = new HBox(17);
        Text txtGameDeck = new Text("Game Deck: ");
        txtGameDeck.setFill(Color.WHITESMOKE);
        txtGameDeck.setEffect(new DropShadow(0.2 * 1000 * 0.125, Color.BLACK));
        txtGameDeck.setFont(new Font("Perpetua Bold Italic", 25));


        ObservableList<Deck.DeckType> deckTypesList = FXCollections.observableArrayList(DeckType.Standard, DeckType.Classic);

        cboGameDeck = new ComboBox<Deck.DeckType>();
        cboGameDeck.getItems().addAll(deckTypesList);
        cboGameDeck.getSelectionModel().select(0);
        cboGameDeck.setCellFactory(new TextCellFactory());
        cboGameDeck.setMinWidth(150);
        cboGameDeck.setMaxWidth(150);
        cboGameDeck.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        cboGameDeck.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));

        
        cboGameDeck.setButtonCell(new ListCell<DeckType>(){

            @Override
            protected void updateItem(DeckType type, boolean empty) {
                super.updateItem(type, empty); 

                this.setTextFill(Color.WHITESMOKE);
                this.setFont(new Font("Perpetua Bold Italic", 18));

                if(empty || type == null){
                    setText(null);
                } else {
                    setText(type.toString());
                }
            }
    
        });

        hboxGameDeck.setAlignment(Pos.CENTER_LEFT);
        hboxGameDeck.getChildren().addAll(txtGameDeck, cboGameDeck);

        VBox vboxCheckBoxes = new VBox(10);
        vboxCheckBoxes.setAlignment(Pos.CENTER);
        vboxCheckBoxes.getChildren().addAll(chkMusic, chkSoundEffects, hboxPlayerName, hboxGameDeck);

        HBox hboxCheckBoxes = new HBox();
        hboxCheckBoxes.setAlignment(Pos.CENTER);
        hboxCheckBoxes.getChildren().addAll(vboxCheckBoxes);

        bottomDisplay.getChildren().addAll(hboxCheckBoxes);
        bottomDisplay.setMinHeight(200);
        bottomDisplay.setMaxHeight(200);

        VBox vboxBottomBox = new VBox();
        vboxBottomBox.getChildren().addAll(bottomDisplay, buttonsBox);

        innerPane.setLeft(leftDisplay);
        innerPane.setRight(rightDisplay);
        innerPane.setBottom(vboxBottomBox);

        this.setBackground(new Background(new BackgroundFill(Color.rgb(64,64,64), new CornerRadii(0.0), new Insets(0,0,0,0))));

        this.getChildren().addAll(innerPane);
        System.gc();
        doneInitializing = true;
    }

    @Override
    public void setWidth(double width) { 
        super.setWidth(width);

        btnStartGame.setMinWidth(width / 2);
        btnStartGame.setMaxWidth(width / 2);

        btnHome.setMinWidth(width / 2);
        btnHome.setMaxWidth(width / 2);

        leftDisplay.setMinWidth(width / 2);
        leftDisplay.setMaxWidth(width / 2);

        rightDisplay.setMinWidth(width / 2);
        rightDisplay.setMaxWidth(width / 2);

        bottomDisplay.setMinWidth(width);
        bottomDisplay.setMaxWidth(width);
    }

    @Override
    public void setHeight(double height) { 
        double minHeight = Math.max(height, 250);
        super.setHeight(minHeight);

        minHeight = minHeight - 200;

        btnStartGame.setMinHeight(minHeight / 3.0);
        btnStartGame.setMaxHeight(minHeight / 4.0);
        btnStartGame.setFont(new Font("Perpetua Bold Italic", Math.max(btnStartGame.getMinHeight() * 0.325, 1)));

        btnHome.setMinHeight(minHeight / 3.0);
        btnHome.setMaxHeight(minHeight / 3.0);
        btnHome.setFont(new Font("Perpetua Bold Italic", Math.max(btnHome.getMinHeight() * 0.325, 1)));

        leftDisplay.setMinHeight(minHeight * 2.0 / 3.0);
        leftDisplay.setMaxHeight(minHeight * 2.0 / 3.0);

        rightDisplay.setMinHeight(minHeight * 2.0 / 3.0);
        rightDisplay.setMaxHeight(minHeight * 2.0 / 3.0);
 
    }
    
}
