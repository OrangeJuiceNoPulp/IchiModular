package com.example;

import java.util.ArrayList;

import com.example.Leaderboard.PlayerScorePair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
//import javafx.scene.control.ScrollPane;
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
import javafx.stage.Stage;


public class LeaderboardPane extends BorderPane {

    private Button btnHome;

    private Text txtLeaderboardTitle;

    private ComboBox<Integer> cboRounds;
    private ComboBox<Integer> cboPlayers;

    private VBox vboxScores;

    private Leaderboard leaderboard;

    private ArrayList<Text> scoresList;

    private HBox hboxRounds;
    private HBox hboxPlayers;

    private void refreshLeaderBoardTextSize() {
        txtLeaderboardTitle.setFont(new Font("Perpetua Bold Italic", Math.max(0.096 * this.getHeight(), 18)));

        for (Text textScore : scoresList) {
            textScore.setFont(new Font("Perpetua Bold Italic", Math.max(0.036 * this.getHeight(), 18)));
        }
    }

    private void refreshLeaderBoard() {
        int numPlayers = cboPlayers.getSelectionModel().getSelectedItem();
        int numRounds = cboRounds.getSelectionModel().getSelectedItem();
        ArrayList<PlayerScorePair> scorePairs = leaderboard.readFromLeaderboard(numPlayers, numRounds);
        vboxScores.getChildren().clear();
        vboxScores.getChildren().addAll(txtLeaderboardTitle, new Text(""));

        scoresList.clear();

        for (PlayerScorePair pair : scorePairs) {
            Text txtPlayerScore = new Text();
            txtPlayerScore.setFont(new Font("Perpetua Bold Italic", Math.max(0.036 * this.getHeight(), 18)));
            txtPlayerScore.setFill(Color.WHITESMOKE);

            String playerCroppedName = new String(pair.getName());

            int textLength = Math.min(18, playerCroppedName.length());
            playerCroppedName = playerCroppedName.substring(0, textLength);

            if (pair.getName().length() > 18) {
                playerCroppedName = playerCroppedName + "...";
            } 

            txtPlayerScore.setText(playerCroppedName + ": " + pair.getScore());

            scoresList.add(txtPlayerScore);
            vboxScores.getChildren().add(txtPlayerScore);
        }

    }

    public LeaderboardPane(Stage stage) {
        super();

        this.leaderboard = new Leaderboard();
        this.scoresList = new ArrayList<Text>(11);

        //ScrollPane textPane = new ScrollPane();
        btnHome = new Button("Back");
        btnHome.setFont(new Font("Perpetua Bold Italic", Math.max(btnHome.getMinHeight() * 0.325, 18)));
        btnHome.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        //btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(5), new Insets(0))));
        btnHome.setStyle("-fx-background-color: #808080");
        btnHome.setTextFill(Color.WHITESMOKE);
        btnHome.setOnMouseEntered(e -> {
            btnHome.setStyle("-fx-background-color: #202020");
        });
        btnHome.setOnMouseExited(e -> {
            btnHome.setStyle("-fx-background-color: #808080");
        });

        btnHome.setOnAction(e -> {
            this.getChildren().clear();
            stage.setScene(new Scene(new HomeScreenPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });

        vboxScores = new VBox(4);

        txtLeaderboardTitle = new Text("Leaderboard");
        txtLeaderboardTitle.setFont(new Font("Perpetua Bold Italic", 40));
        txtLeaderboardTitle.setFill(Color.WHITESMOKE);

        

        ObservableList<Integer> numRoundsList = FXCollections.observableArrayList(1, 3, 5);
        ObservableList<Integer> numPlayersList = FXCollections.observableArrayList(2, 3, 4);

        cboRounds = new ComboBox<Integer>();
        cboRounds.getItems().addAll(numRoundsList);
        cboRounds.getSelectionModel().select(1);
        cboRounds.setCellFactory(new IntegerCellFactory());
        cboRounds.setMinWidth(150);
        cboRounds.setMaxWidth(150);
        cboRounds.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        cboRounds.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        
        cboRounds.setButtonCell(new ListCell<Integer>(){

            @Override
            protected void updateItem(Integer number, boolean empty) {
                super.updateItem(number, empty); 

                this.setTextFill(Color.WHITESMOKE);
                this.setFont(new Font("Perpetua Bold Italic", 18));

                if(empty || number == null){
                    setText(null);
                } else {
                    setText(number.toString());
                }
            }
    
        });
        cboRounds.setOnAction(e -> {
            refreshLeaderBoard();
        });

        cboPlayers = new ComboBox<Integer>();
        cboPlayers.getItems().addAll(numPlayersList);
        cboPlayers.getSelectionModel().select(2);
        cboPlayers.setCellFactory(new IntegerCellFactory());
        cboPlayers.setMinWidth(150);
        cboPlayers.setMaxWidth(150);
        cboPlayers.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        cboPlayers.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));

        cboPlayers.setButtonCell(new ListCell<Integer>(){

            @Override
            protected void updateItem(Integer number, boolean empty) {
                super.updateItem(number, empty); 

                this.setTextFill(Color.WHITESMOKE);
                this.setFont(new Font("Perpetua Bold Italic", 18));

                if(empty || number == null){
                    setText(null);
                } else {
                    setText(number.toString());
                }
            }
    
        });
        cboPlayers.setOnAction(e -> {
            refreshLeaderBoard();
        });


        VBox vboxPlayers = new VBox();
        vboxPlayers.setAlignment(Pos.CENTER);
        hboxPlayers = new HBox(10);
        hboxPlayers.setAlignment(Pos.CENTER);

        Text txtPlayersLabel = new Text("Number of Players:");
        txtPlayersLabel.setFont(new Font("Perpetua Bold Italic", 28));
        txtPlayersLabel.setFill(Color.WHITESMOKE);
        hboxPlayers.getChildren().addAll(txtPlayersLabel, cboPlayers);
        vboxPlayers.getChildren().addAll(hboxPlayers);

        
        VBox vboxRounds = new VBox();
        vboxRounds.setAlignment(Pos.CENTER);
        hboxRounds = new HBox(10);
        hboxRounds.setAlignment(Pos.CENTER);

        Text txtRoundsLabel = new Text("Number of Rounds:");
        txtRoundsLabel.setFont(new Font("Perpetua Bold Italic", 28));
        txtRoundsLabel.setFill(Color.WHITESMOKE);
        hboxRounds.getChildren().addAll(txtRoundsLabel, cboRounds);
        vboxRounds.getChildren().addAll(hboxRounds);


        HBox hboxNumPlayersRoundsSelect = new HBox();

        hboxNumPlayersRoundsSelect.setAlignment(Pos.CENTER);
        hboxNumPlayersRoundsSelect.getChildren().addAll(vboxPlayers, vboxRounds);
        




        vboxScores.getChildren().addAll(txtLeaderboardTitle);
        vboxScores.setAlignment(Pos.CENTER);

        refreshLeaderBoard();

        //textPane.setContent(vboxScores);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        this.setTop(hboxNumPlayersRoundsSelect);

        BorderPane.setMargin(hboxNumPlayersRoundsSelect, new Insets(25, 0, 0, 0));

        this.setCenter(vboxScores);
        this.setBottom(btnHome);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);

        hboxPlayers.setMinWidth(width * 0.5);
        hboxPlayers.setMaxWidth(width * 0.5);

        hboxRounds.setMinWidth(width * 0.5);
        hboxRounds.setMaxWidth(width * 0.5);

        btnHome.setMinWidth(width);
        btnHome.setPrefWidth(width);
        btnHome.setMaxWidth(width);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        
        refreshLeaderBoardTextSize();

        btnHome.setMinHeight(height / 8);
        btnHome.setMaxHeight(height / 8);
        btnHome.setFont(new Font("Perpetua Bold Italic", Math.max(btnHome.getMinHeight() * 0.325, 18)));
    }
}
