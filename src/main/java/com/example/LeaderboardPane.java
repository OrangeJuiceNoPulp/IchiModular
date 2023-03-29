package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
//import javafx.scene.control.ScrollPane;
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

public class LeaderboardPane extends BorderPane {

    private Button btnHome;

    public LeaderboardPane(Stage stage) {
        super();

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

        VBox vboxScores = new VBox();

        Text txtComingSoon = new Text("Leaderboard Coming Soon!");
        txtComingSoon.setFont(new Font("Perpetua Bold Italic", 60));
        txtComingSoon.setFill(Color.WHITESMOKE);



        vboxScores.getChildren().addAll(txtComingSoon);
        vboxScores.setAlignment(Pos.CENTER);

        //textPane.setContent(vboxScores);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        this.setCenter(vboxScores);
        this.setBottom(btnHome);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);

        btnHome.setMinWidth(width);
        btnHome.setPrefWidth(width);
        btnHome.setMaxWidth(width);
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        
        btnHome.setMinHeight(height / 8);
        btnHome.setMaxHeight(height / 8);
        btnHome.setFont(new Font("Perpetua Bold Italic", Math.max(btnHome.getMinHeight() * 0.325, 18)));
    }
}
