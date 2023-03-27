package com.example;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InstructionsPane extends BorderPane {

    private Button btnHome;

    public InstructionsPane(Stage stage) {
        super();

        ScrollPane textPane = new ScrollPane();
        btnHome = new Button("Back");

        btnHome.setFont(new Font("Perpetua Bold Italic", 25));
        btnHome.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
        btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(5), new Insets(0))));
        btnHome.setTextFill(Color.WHITESMOKE);
        btnHome.setOnMouseEntered(e -> {
            btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), new CornerRadii(0), new Insets(0))));
        });
        btnHome.setOnMouseExited(e -> {
            btnHome.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), new CornerRadii(0), new Insets(0))));
        });

        btnHome.setOnAction(e -> {
            try {
                stage.setScene(new Scene(new HomeScreenPane(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        VBox vboxInstructions = new VBox();

        String strHeader = "  Instructions:\n";

        String strObjectiveTitle = "\tObjective of the game:";
        String strObjective = "\t\to Obtain the most points during each round and have the most points at the end of the game.\n";
        String strGameOptionsTitle = "\tGame options:";
        String strGameOptions = "\t\to The game will consist of a set number of rounds which the user chooses:\n\t\t\t- Best of 1\n\t\t\t- Best of 3\n\t\t\t- Best of 5";
        String strGameOptions2 = "\t\to The game will be played by the user and 1 to 3 computer units (with varying difficulties?):\n\t\t\t- 1v1 (2 players) \n\t\t\t- 1v1v1 (3 players)\n\t\t\t- 1v1v1v1 (4 players)";
        String strGameOptions3 = "\t\to The player with the most points at the end of the game will be the overall winner.\n";
        String strLeaderboardTitle = "\tLeaderboard:";
        String strLeaderboard = "\t\to There will be a leaderboard divided into 3 pages (2/3/4 Players). Each page will display 3 columns for the high scores (Best of 1/3/5). The top 10 scores\n\t\t    for each category are displayed.\n";
        String strSetupTitle = "\tSet-up:";
        String strSetup = "\t\to Each player is dealt 10 cards. A player is randomly selected to go first. A card is flipped from the top of the deck and put in the first discard pile which is\n\t\t    called the castle. The default order of play is clockwise.\n";
        String strPlayTitle = "\tPlay:";
        String strPlay = "\t\to During their turn, the player must play a card from their hand. The played card is set on top of the selected discard pile (the castle or a tower). In order for\n\t\t    a card to be played, it must match the color or value (or absolute value, negative 5 can be played on positive 5) of the card at the top of the discard pile,\n\t\t    unless it is a wild card or semi-wild card (semi-wild cards must match the value of the card at the top of the discard pile to be played unless the top card is\n\t\t    a wild or semi-wild card). If a card cannot be played during the player's turn or if the player chooses to draw cards, they must draw cards until they get a\n\t\t    card which is possible for them to play. The player can choose to either keep this card or play it.\n";
        String strSpecialGameEventsTitle = "\tSpecial game events:";
        String strSpecialGameEvents = "\t\to A player has more than 100 cards:\n\t\t\t- The player with more than 100 cards returns their hand, except for two random cards, to the deck. The player loses their next turn and takes a penalty\n\t\t\t   of 5000 points.";
        String strSpecialGameEvents2 = "\t\to Stacking:\n\t\t\t- Certain specialty cards (draw 2, draw 4, push the button 2 times, etc.) can be stacked depending upon their stack group type (draw, push, etc.),\n\t\t\t   resulting in the effect of the cards being added together and passed to the next player. Playing one of these cards starts a stack event. During a stack\n\t\t\t   event, the player continues the stack by playing a card in the same stack group type, regardless of color or value. If a player cannot continue the stack,\n\t\t\t   they may choose to accept the stack or to draw the top card of the deck (with a penalty of 50 points as long as they have more than that amount of\n\t\t\t   points) to try to continue the stack. If they fail to continue the stack, the card they drew goes to their hand along with any cards obtained from the result\n\t\t\t   of the stack. Accepting the stack is the end of the player's turn.";
        String strSpecialGameEvents3 = "\t\to A player gets rid of all their cards:\n\t\t\t- The player who gets rid of all their cards receives points according to the worth of each card remaining in the hands of all the other players. The round\n\t\t\t   then ends.\n";
        String strCardColorsTitle = "\tCard colors:";
        String strCardColors = "\t\to Base colors:\n\t\t\t- Light mode colors:\n\t\t\t\t• Red\n\t\t\t\t• Blue\n\t\t\t\t• Yellow\n\t\t\t\t• Green\n\t\t\t\t• Orange\n\t\t\t\t• Purple\n\t\t\t- Dark mode colors:\n\t\t\t\t• Cyan\n\t\t\t\t• Magenta\n\t\t\t\t• Lime\n\t\t\t\t• Maroon\n\t\t\t\t• Semi-wild\n\t\t\t\t\to Semi-wild card can only be played if they match the value of the previous card or if the card at the top of the discard pile is a wild card or\n\t\t\t\t\t   semi-wild card of any kind. After playing a semi-wild card, the player may specify the next color of play, like with regular wild cards.";
        String strCardColors2 = "\t\to Card color types:\n\t\t\t- All-color:\n\t\t\t\t• all colors, light and dark mode.\n\t\t\t- Most-color:\n\t\t\t\t• all colors except semi-wild\n\t\t\t- Lightmode-color:\n\t\t\t\t• all colors in light mode\n\t\t\t- Darkmode-color:\n\t\t\t\t• all colors in dark mode except for semi-wild\n";
        String strCardTypesTitle = "\tCard types:";
        String strCardTypes = "\t\to Regular cards:\n\t\t\t- Number card:\n\t\t\t\t• An all-color card with a number 0 - 9 on it.";
        String strCardTypes2 = "\t\t\t- Reverse card:\n\t\t\t\t• A most-color card which switches the order of play from clockwise to counterclockwise (or the other way around). In a 2-player game (1v1), this\n\t\t\t\t   card functions as a skip.";
        String strCardTypes3 = "\t\t\t- Skip card:\n\t\t\t\t• A most-color card which causes the next player to lose their turn.";
        String strCardTypes4 = "\t\t\t- Skip-All card::\n\t\t\t\t• A darkmode-color card which causes all of the other players to lose a turn.";
        String strCardTypes5 = "\t\t\t- Flip card:\n\t\t\t\t• A most-color card which causes all the cards (the players’ hands, the deck, the castle, and the towers) to switch from light mode to dark mode\n\t\t\t\t   or from dark mode to light mode.";
        String strCardTypes6 = "\t\t\t- Tower Build card:\n\t\t\t\t• A most-color card. If this card is played at the castle and a player is not yet in control of the castle (main discard pile), then this card will allow them\n\t\t\t\t   to take automatic ownership of it. If the castle is already under control (whether by this player or another), then this card allows the player of this\n\t\t\t\t   card to construct a new tower (or battle for control of an existing tower if the maximum number of towers are already built). A new tower is\n\t\t\t\t   constructed by flipping over the top card from the deck to create a new discard pile. The owner of this new tower is the player who played this card.\n\t\t\t\t   The maximum number of towers which can be in play at once is equal to the number of players. If this card is played at a tower, then a battle for the\n\t\t\t\t   tower commences. The current owner of the castle and the player who played this card both draw a card from the top of the deck. The player who\n\t\t\t\t   draws a card with the highest point value wins the tower. (If there is a tie, then this process is repeated until the ownership of the tower is decided).";
        String strCardTypes7 = "\t\t\t- Tower Destroy Card:\n\t\t\t\t• A most-color card. If this card is played at the castle, then a battle for the castle commences. The current owner of the castle and the player who\n\t\t\t\t   played this card both draw a card from the top of the deck. If the castle is unowned, then all players will draw a card from the top of the deck. The\n\t\t\t\t   player who draws a card with the highest point value wins the castle. (If there is a tie, then this process is repeated until the ownership of the\n\t\t\t\t   castle is decided). If the player who played this card owns the castle and this card is played at the castle, then they may destroy a tower of their\n\t\t\t\t   choice (or none at all). If this card is played at a tower, that tower is destroyed without exception, no matter who owns it.";
        String strCardTypes8 = "\t\t\t- Discard all card:\n\t\t\t\t• A darkmode-color card which allows the player to discard all the cards in their hand of that color. The discard all card will be placed at the top of\n\t\t\t\t   the pile (so the order of discarding does not matter). The player must discard all the cards of that color, discarded cards will not have their action\n\t\t\t\t   take effect.";
        String strCardTypes9 = "\t\t\t- Swap hands card:\n\t\t\t\t• A darkmode-color card which allows the player to swap their hand of cards with the player of their choice.";
        String strCardTypes10 = "\t\t\t- Rotate hands card:\n\t\t\t\t• A most-color card which rotates all the players’ hand of cards in the direction of play.";
        String strCardTypes11 = "\t\t\t- Negative Number card:\n\t\t\t\t• An all-color card which causes the next player to lose the specified number of points. They do not lose their turn.";
        String strCardTypes12 = "\t\t\t- Thief card:\n\t\t\t\t• A darkmode-color card which steals points from the next player. The next player does not lose their turn.";
        String strCardTypes13 = "\t\t\t- Targeted draw 1:\n\t\t\t\t• A lightmode-color card which allows the player to select another player to draw 1 card. The player who draws is not skipped. This card is not\n\t\t\t\t   stackable and does not start a stack event.";
        String strCardTypes14 = "\t\t\t- Targeted draw 2:\n\t\t\t\t• A darkmode-color card which allows the player to select another player to draw 2 cards. The player who draws is not skipped. This card is not\n\t\t\t\t   stackable and does not start a stack event.";
        String strCardTypes15 = "\t\t\t- Draw 2:\n\t\t\t\t• A lightmode-color card which starts a stack event. This card contributes 2 cards to the stack. It is part of the Draw stack group.";
        String strCardTypes16 = "\t\t\t- Draw 5:\n\t\t\t\t• A darkmode-color card which starts a stack event. This card contributes 5 cards to the stack. It is part of the Draw stack group.";
        String strCardTypes17 = "\t\t\t- Press 1:\n\t\t\t\t• A lightmode-color card which starts a stack event. This card contributes 1 press to the stack. It is part of the Press stack group.";
        String strCardTypes18 = "\t\t\t- Press 3:\n\t\t\t\t• A darkmode-color card which starts a stack event. This card contributes 3 presses to the stack. It is part of the Press stack group.";
        String strCardTypes19 = "\t\t\t- Spin 1:\n\t\t\t\t• A lightmode-color card which starts a stack event. This card contributes 1 spin to the stack. It is part of the Spin stack group.";
        String strCardTypes20 = "\t\t\t- Spin 2:\n\t\t\t\t• A darkmode-color card which starts a stack event. This card contributes 2 spins to the stack. It is part of the Spin stack group.";
        String strCardTypes21 = "\t\to Wild cards:\n\t\t\t- Number card:\n\t\t\t\t• An all-color card with a number 0 - 9 on it.";
        String strCardTypes22 = "\t\t\t- Regular wild:\n\t\t\t\t• An all-mode wild card. It allows the player to choose the next color of play.";
        String strCardTypes23 = "\t\t\t- Skip wild:\n\t\t\t\t• An all-mode wild card. It has the same function as a skip card. It also allows the player to choose the next color of play.";
        String strCardTypes24 = "\t\t\t- Swap hands wild:\n\t\t\t\t• A light mode wild card. It has the same function as a swap hands card. It also allows the player to choose the next color of play.";
        String strCardTypes25 = "\t\t\t- Draw 4:\n\t\t\t\t• A light mode wild card which starts a stack event. This card contributes 4 cards to the stack. It allows the player to choose the next color of play\n\t\t\t\t   (unless another card is stacked afterwards). It is part of the Draw stack group.";
        String strCardTypes26 = "\t\t\t- Press 2 wild:\n\t\t\t\t• A light mode wild card which starts a stack event. This card contributes 2 presses to the stack. It allows the player to choose the next color of play\n\t\t\t\t   (unless another card is stacked afterwards). It is part of the Press stack group.";
        String strCardTypes27 = "\t\t\t- Press till lose:\n\t\t\t\t• A dark mode wild card which causes the next player to press the button until cards are shot out. This card is not stackable. It also allows the player\n\t\t\t\t   to choose the next color of play.";
        String strCardTypes28 = "\t\t\t- Everyone Press:\n\t\t\t\t• A dark mode wild card which causes every player to press the button once (including the player who played this card?). This card is not stackable.\n\t\t\t\t   It also allows the player to choose the next color of play.";
        String strCardTypes29 = "\t\t\t- Shield:\n\t\t\t\t• An all-mode wild card which can be played during any stack event to shield a player from the effects of the stack. The stack ends. It also allows the\n\t\t\t\t   player to choose the next color of play.";
        String strCardTypes30 = "\t\t\t- Times 2:\n\t\t\t\t• An all-mode wild card which can be played during any stack event to double the total of the stack. The stack continues with the next player. It also\n\t\t\t\t   allows the player to choose the next color of play (unless another card is stacked afterwards).";
        String strCardTypes31 = "\t\t\t- Spin 2 wild:\n\t\t\t\t• A light mode wild card which starts a stack event. This card contributes 2 spins to the stack. It allows the player to choose the next color of play\n\t\t\t\t   (unless another card is stacked afterwards). It is part of the Spin stack group.";
        String strCardTypes32 = "\t\t\t- Spin 3:\n\t\t\t\t• A dark mode wild card which starts a stack event. This card contributes 3 spins to the stack. It allows the player to choose the next color of play\n\t\t\t\t   (unless another card is stacked afterwards). It is part of the Spin stack group.";
        String strCardTypes33 = "\t\t\t- Draw till match:\n\t\t\t\t• A dark mode wild card which allows the player to choose the next color of play. The next player must draw cards until they get a card of the chosen\n\t\t\t\t   color. Their turn is then skipped.";
        String strCardTypes34 = "\t\t\t- Redistribute hands:\n\t\t\t\t• A dark mode wild card which collects all the players’ hands, shuffles them, and then deals the cards out evenly in the direction of play, such that the\n\t\t\t\t   next player is dealt a card first. It also allows the player to choose the next color of play (after the cards are dealt).\n";
        String strStackGroupsTitle = "\tStack Groups:";
        String strStackGroups = "\t\to Draw:\n\t\t\t- The user who is forced to receive this stack will draw the number of cards specified by the amount of the stack from the deck.";
        String strStackGroups2 = "\t\to Press:\n\t\t\t- The user who is forced to receive this stack will press the button the number of times specified by the amount of the stack. The button has a 1 in 3\n\t\t\t   chance to spit out between 1 and 6 cards.";
        String strStackGroups3 = "\t\to Spin:\n\t\t\t- The user who is forced to receive this stack will spin the wheel of misfortune the number of times specified by the amount of the stack. The wheel of\n\t\t\t   misfortune contains different amounts of points to be lost.\n";
        String strPointsTitle = "\tPoints:";
        String strPoints = "\t\to Light Mode:\n\t\t\t- A number card is worth the value displayed on the card times 5. 20% of the card’s points go to the tower/castle owner as tax and the remainder goes to\n\t\t\t   the player who played the card. If the tower/castle is unowned, all of the points go to the player. The 0 card is worth 50 points and is not taxable.\n\t\t\t   Negative number cards deduct the value of the number on the card times 5.";
        String strPoints2 = "\t\to Dark mode:\n\t\t\t- A number card is worth the value displayed on the card times 5. 60% of the card’s points go to the tower/castle owner as tax and the remainder goes to\n\t\t\t   the player who played the card. If the tower/castle is unowned, all of the points go to the player. The 0 card is worth 75 points and is not taxable.\n\t\t\t   Negative number cards deduct the value of the number on the card times 10. Points earned from the thief card are not taxable.\n";

        Text txtHeader = new Text(strHeader);
        Text txtObjectiveTitle = new Text(strObjectiveTitle);
        Text txtObjective = new Text(strObjective);
        Text txtGameOptionsTitle = new Text(strGameOptionsTitle);
        Text txtGameOptions = new Text(strGameOptions);
        Text txtGameOptions2 = new Text(strGameOptions2);
        Text txtGameOptions3 = new Text(strGameOptions3);
        Text txtLeaderboardTitle = new Text(strLeaderboardTitle);
        Text txtLeaderboard = new Text(strLeaderboard);
        Text txtSetupTitle = new Text(strSetupTitle);
        Text txtSetup = new Text(strSetup);
        Text txtPlayTitle = new Text(strPlayTitle);
        Text txtPlay = new Text(strPlay);
        Text txtSpecialGameEventsTitle = new Text(strSpecialGameEventsTitle);
        Text txtSpecialGameEvents = new Text(strSpecialGameEvents);
        Text txtSpecialGameEvents2 = new Text(strSpecialGameEvents2);
        Text txtSpecialGameEvents3 = new Text(strSpecialGameEvents3);
        Text txtCardColorsTitle = new Text(strCardColorsTitle);
        Text txtCardColors = new Text(strCardColors);
        Text txtCardColors2 = new Text(strCardColors2);
        Text txtCardTypesTitle = new Text(strCardTypesTitle);
        Text txtCardTypes = new Text(strCardTypes);
        Text txtCardTypes2 = new Text(strCardTypes2);
        Text txtCardTypes3 = new Text(strCardTypes3);
        Text txtCardTypes4 = new Text(strCardTypes4);
        Text txtCardTypes5 = new Text(strCardTypes5);
        Text txtCardTypes6 = new Text(strCardTypes6);
        Text txtCardTypes7 = new Text(strCardTypes7);
        Text txtCardTypes8 = new Text(strCardTypes8);
        Text txtCardTypes9 = new Text(strCardTypes9);
        Text txtCardTypes10 = new Text(strCardTypes10);
        Text txtCardTypes11 = new Text(strCardTypes11);
        Text txtCardTypes12 = new Text(strCardTypes12);
        Text txtCardTypes13 = new Text(strCardTypes13);
        Text txtCardTypes14 = new Text(strCardTypes14);
        Text txtCardTypes15 = new Text(strCardTypes15);
        Text txtCardTypes16 = new Text(strCardTypes16);
        Text txtCardTypes17 = new Text(strCardTypes17);
        Text txtCardTypes18 = new Text(strCardTypes18);
        Text txtCardTypes19 = new Text(strCardTypes19);
        Text txtCardTypes20 = new Text(strCardTypes20);
        Text txtCardTypes21 = new Text(strCardTypes21);
        Text txtCardTypes22 = new Text(strCardTypes22);
        Text txtCardTypes23 = new Text(strCardTypes23);
        Text txtCardTypes24 = new Text(strCardTypes24);
        Text txtCardTypes25 = new Text(strCardTypes25);
        Text txtCardTypes26 = new Text(strCardTypes26);
        Text txtCardTypes27 = new Text(strCardTypes27);
        Text txtCardTypes28 = new Text(strCardTypes28);
        Text txtCardTypes29 = new Text(strCardTypes29);
        Text txtCardTypes30 = new Text(strCardTypes30);
        Text txtCardTypes31 = new Text(strCardTypes31);
        Text txtCardTypes32 = new Text(strCardTypes32);
        Text txtCardTypes33 = new Text(strCardTypes33);
        Text txtCardTypes34 = new Text(strCardTypes34);
        Text txtStackGroupsTitle = new Text(strStackGroupsTitle);
        Text txtStackGroups = new Text(strStackGroups);
        Text txtStackGroups2 = new Text(strStackGroups2);
        Text txtStackGroups3 = new Text(strStackGroups3);
        Text txtPointsTitle = new Text(strPointsTitle);
        Text txtPoints = new Text(strPoints);
        Text txtPoints2 = new Text(strPoints2);

        txtHeader.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 28));
        txtHeader.setFill(Color.WHITE);
        txtObjectiveTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtObjectiveTitle.setFill(Color.WHITE);
        txtObjective.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtObjective.setFill(Color.WHITE);
        txtGameOptionsTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtGameOptionsTitle.setFill(Color.WHITE);
        txtGameOptions.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtGameOptions.setFill(Color.WHITE);
        txtGameOptions2.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtGameOptions2.setFill(Color.WHITE);
        txtGameOptions3.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtGameOptions3.setFill(Color.WHITE);
        txtLeaderboardTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtLeaderboardTitle.setFill(Color.WHITE);
        txtLeaderboard.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtLeaderboard.setFill(Color.WHITE);
        txtSetupTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtSetupTitle.setFill(Color.WHITE);
        txtSetup.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtSetup.setFill(Color.WHITE);
        txtPlayTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtPlayTitle.setFill(Color.WHITE);
        txtPlay.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtPlay.setFill(Color.WHITE);
        txtSpecialGameEventsTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtSpecialGameEventsTitle.setFill(Color.WHITE);
        txtSpecialGameEvents.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtSpecialGameEvents.setFill(Color.WHITE);
        txtSpecialGameEvents2.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtSpecialGameEvents2.setFill(Color.WHITE);
        txtSpecialGameEvents3.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtSpecialGameEvents3.setFill(Color.WHITE);
        txtCardColorsTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtCardColorsTitle.setFill(Color.WHITE);
        txtCardColors.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardColors.setFill(Color.WHITE);
        txtCardColors2.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardColors2.setFill(Color.WHITE);
        txtCardTypesTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtCardTypesTitle.setFill(Color.WHITE);
        txtCardTypes.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes.setFill(Color.WHITE);
        txtCardTypes2.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes2.setFill(Color.WHITE);
        txtCardTypes3.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes3.setFill(Color.WHITE);
        txtCardTypes4.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes4.setFill(Color.WHITE);
        txtCardTypes5.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes5.setFill(Color.WHITE);
        txtCardTypes6.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes6.setFill(Color.WHITE);
        txtCardTypes7.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes7.setFill(Color.WHITE);
        txtCardTypes8.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes8.setFill(Color.WHITE);
        txtCardTypes9.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes9.setFill(Color.WHITE);
        txtCardTypes10.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes10.setFill(Color.WHITE);
        txtCardTypes11.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes11.setFill(Color.WHITE);
        txtCardTypes12.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes12.setFill(Color.WHITE);
        txtCardTypes13.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes13.setFill(Color.WHITE);
        txtCardTypes14.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes14.setFill(Color.WHITE);
        txtCardTypes15.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes15.setFill(Color.WHITE);
        txtCardTypes16.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes16.setFill(Color.WHITE);
        txtCardTypes17.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes17.setFill(Color.WHITE);
        txtCardTypes18.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes18.setFill(Color.WHITE);
        txtCardTypes19.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes19.setFill(Color.WHITE);
        txtCardTypes20.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes20.setFill(Color.WHITE);
        txtCardTypes21.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes21.setFill(Color.WHITE);
        txtCardTypes22.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes22.setFill(Color.WHITE);
        txtCardTypes23.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes23.setFill(Color.WHITE);
        txtCardTypes24.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes24.setFill(Color.WHITE);
        txtCardTypes25.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes25.setFill(Color.WHITE);
        txtCardTypes26.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes26.setFill(Color.WHITE);
        txtCardTypes27.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes27.setFill(Color.WHITE);
        txtCardTypes28.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes28.setFill(Color.WHITE);
        txtCardTypes29.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes29.setFill(Color.WHITE);
        txtCardTypes30.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes30.setFill(Color.WHITE);
        txtCardTypes31.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes31.setFill(Color.WHITE);
        txtCardTypes32.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes32.setFill(Color.WHITE);
        txtCardTypes33.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes33.setFill(Color.WHITE);
        txtCardTypes34.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtCardTypes34.setFill(Color.WHITE);
        txtStackGroupsTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtStackGroupsTitle.setFill(Color.WHITE);
        txtStackGroups.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtStackGroups.setFill(Color.WHITE);
        txtStackGroups2.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtStackGroups2.setFill(Color.WHITE);
        txtStackGroups3.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtStackGroups3.setFill(Color.WHITE);
        txtPointsTitle.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        txtPointsTitle.setFill(Color.WHITE);
        txtPoints.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtPoints.setFill(Color.WHITE);
        txtPoints2.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 16));
        txtPoints2.setFill(Color.WHITE);

        vboxInstructions.getChildren().addAll(
                txtHeader,
                txtObjectiveTitle, 
                txtObjective, 
                txtGameOptionsTitle, 
                txtGameOptions, 
                txtGameOptions2,
                txtGameOptions3,
                txtLeaderboardTitle,
                txtLeaderboard,
                txtSetupTitle,
                txtSetup,
                txtPlayTitle,
                txtPlay,
                txtSpecialGameEventsTitle,
                txtSpecialGameEvents,
                txtSpecialGameEvents2,
                txtSpecialGameEvents3,
                txtCardColorsTitle,
                txtCardColors,
                txtCardColors2,
                txtCardTypesTitle,
                txtCardTypes,
                txtCardTypes2,
                txtCardTypes3,
                txtCardTypes4,
                txtCardTypes5,
                txtCardTypes6,
                txtCardTypes7,
                txtCardTypes8,
                txtCardTypes9,
                txtCardTypes10,
                txtCardTypes11,
                txtCardTypes12,
                txtCardTypes13,
                txtCardTypes14,
                txtCardTypes15,
                txtCardTypes16,
                txtCardTypes17,
                txtCardTypes18,
                txtCardTypes19,
                txtCardTypes20,
                txtCardTypes21,
                txtCardTypes22,
                txtCardTypes23,
                txtCardTypes24,
                txtCardTypes25,
                txtCardTypes26,
                txtCardTypes27,
                txtCardTypes28,
                txtCardTypes29,
                txtCardTypes30,
                txtCardTypes31,
                txtCardTypes32,
                txtCardTypes33,
                txtCardTypes34,
                txtStackGroupsTitle,
                txtStackGroups,
                txtStackGroups2,
                txtStackGroups3,
                txtPointsTitle,
                txtPoints,
                txtPoints2
                );

        textPane.setContent(vboxInstructions);
        textPane.setStyle("-fx-background: #404040; -fx-border-color: #404040;");

        this.setCenter(textPane);
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
