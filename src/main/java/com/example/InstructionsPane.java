package com.example;

import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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

public class InstructionsPane extends BorderPane {

    private Button btnHome;

    public InstructionsPane(Stage stage) {
        super();

        ScrollPane textPane = new ScrollPane();
        btnHome = new Button("Back");

        btnHome.setOnAction(e -> {
            stage.setScene(new Scene(new HomeScreenPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
        });

        VBox vboxInstructions = new VBox();

        String strHeader = "Instructions:\n";

        String strIntro = "• Objective of the game:\n\to Obtain the most points during each round and have the most points at the end of the game.";
        String strGameOptions = "• Game options:\n\to The game will consist of a set number of rounds which the user chooses:\n\t\t- Best of 1\n\t\t- Best of 3\n\t\t- Best of 5";
        String strGameOptions2 = "\to The game will be played by the user and 1 to 3 computer units (with varying difficulties?):\n\t\t- 1v1 (2 players) \n\t\t- 1v1v1 (3 players)\n\t\t- 1v1v1v1 (4 players)\n\to The player with the most points at the end of the game will be the overall winner.";
        String strLeaderboard = "• Leaderboard:\n\to There will be a leaderboard divided into 3 pages (2/3/4 Players). Each page will display 3 columns for the high scores (Best of 1/3/5). The top 10 scores for each category are displayed.";
        String strSetup = "• Set-up:\n\to Each player is dealt 10 cards. A player is randomly selected to go first. A card is flipped from the top of the deck and put in the first discard pile which is called the castle. The default order of play is clockwise.";
        String strPlay = "•	Play:\n\to During their turn, the player must play a card from their hand. The played card is set on top of the selected discard pile (the castle or a tower). In order for a card to be played, it must match the color or value (or absolute value, negative 5 can be played on positive 5) of the card at the top of the discard pile, unless it is a wild card or semi-wild card (semi-wild cards must match the value of the card at the top of the discard pile to be played unless the top card is a wild or semi-wild card). If a card cannot be played during the player's turn or if the player chooses to draw cards, they must draw cards until they get a card which is possible for them to play. The player can choose to either keep this card or play it.";
        String strSpecialGameEvents = "• Special game events:\n\to A player has more than 100 cards:\n\t\t- The player with more than 100 cards returns their hand, except for two random cards, to the deck. The player loses their next turn and takes a penalty of 5000 points.";
        String strSpecialGameEvents2 = "\to Stacking:\n\t\t- Certain specialty cards (draw 2, draw 4, push the button 2 times, etc.) can be stacked depending upon their stack group type (draw, push, etc.), resulting in the effect of the cards being added together and passed to the next player. Playing one of these cards starts a stack event. During a stack event, the player continues the stack by playing a card in the same stack group type, regardless of color or value. If a player cannot continue the stack, they may choose to accept the stack or to draw the top card of the deck (with a penalty of 50 points as long as they have more than that amount of points) to try to continue the stack. If they fail to continue the stack, the card they drew goes to their hand along with any cards obtained from the result of the stack. Accepting the stack is the end of the player's turn.";
        String strSpecialGameEvents3 = "\to A player gets rid of all their cards:\n\t\t- The player who gets rid of all their cards receives points according to the worth of each card remaining in the hands of all the other players. The round then ends.";
        String strCardTypes = "• Card Types:\n\to Card colors:\n\t\t- Light mode colors:\n\t\t\t• Red\n\t\t\t• Blue\n\t\t\t• Yellow\n\t\t\t• Green\n\t\t\t• Orange\n\t\t\t• Purple\n\t\t- Dark mode colors:\n\t\t\t• Cyan\n\t\t\t• Magenta\n\t\t\t• Lime\n\t\t\t• Maroon\n\t\t\t• Semi-wild\n\t\t\t\to Semi-wild card can only be played if they match the value of the previous card or if the card at the top of the discard pile is a wild card or semi-wild card of any kind. After playing a semi-wild card, the player may specify the next color of play, like with regular wild cards.";




        Text txtHeader = new Text(strHeader);
        Text txtIntro = new Text(strIntro);
        Text txtGameOptions = new Text(strGameOptions);
        Text txtGameOptions2 = new Text(strGameOptions2);
        Text txtLeaderboard = new Text(strLeaderboard);
        Text txtSetup = new Text(strSetup);
        Text txtPlay = new Text(strPlay);
        Text txtSpecialGameEvents = new Text(strSpecialGameEvents);
        Text txtSpecialGameEvents2 = new Text(strSpecialGameEvents2);
        Text txtSpecialGameEvents3 = new Text(strSpecialGameEvents3);
        Text txtCardTypes = new Text(strCardTypes);


        vboxInstructions.getChildren().addAll(
                txtHeader,
                txtIntro,
                txtGameOptions, 
                txtGameOptions2,
                txtLeaderboard,
                txtSetup,
                txtPlay,
                txtSpecialGameEvents,
                txtSpecialGameEvents2,
                txtSpecialGameEvents3,
                txtCardTypes
                );

        textPane.setContent(vboxInstructions);

        TextArea textArea = new TextArea();
        Scanner fileReader = new Scanner(this.getClass().getResourceAsStream("instructions.txt"));
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine() + "\n";
            textArea.appendText(line);
        }
        fileReader.close();
        textArea.setFont(new Font("Perpetua Bold Italic", 18));
        textArea.setStyle("-fx-text-inner-color: #FFFFFF; -fx-control-inner-background: #404040;");
        textArea.setBorder(new Border(new BorderStroke(Color.rgb(64, 64, 64), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0), new Insets(0))));

       
        this.setBackground(new Background(new BackgroundFill(Color.rgb(64, 64, 64), new CornerRadii(0), new Insets(0))));
        textArea.setEditable(false);

        textArea.setWrapText(true);
        this.setCenter(textArea);
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
        btnHome.setPrefHeight(height / 8);
        btnHome.setMaxHeight(height / 8);
    }
}
