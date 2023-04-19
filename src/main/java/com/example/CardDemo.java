package com.example;

// import java.util.Random;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CardDemo extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws Exception {
    // Create a pane and set its properties
    //GamePane pane = new GamePane(3, 4);
    
    // LightColorChooserStage wildTest1 = new LightColorChooserStage(new Random());
    // wildTest1.show();

    // DarkColorChooserStage wildTest2 = new DarkColorChooserStage(new Random());
    // wildTest2.show();

    // Stage testStage = new Stage();
    HomeScreenPane testPane = new HomeScreenPane(primaryStage);
    // testStage.setScene(new Scene(new HomeScreenPane(testStage)));
    // testStage.show();

    primaryStage.getIcons().add(new Image(this.getClass().getResource("cardBackSquare.png").toExternalForm()));

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(testPane, 1250, 750);
    primaryStage.setTitle("Ichi"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.setMinHeight(500);
    primaryStage.setMinWidth(900);
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 

/*
 * References (used for inspiration): 

https://www.letsplayuno.com/

https://www.unorules.org/how-many-cards-in-uno/
https://www.adamthole.com/uno-attack-hack/
https://www.ultraboardgames.com/uno/cards.php
https://www.geekyhobbies.com/uno-games-the-complete-list-of-all-themed-decks-spinoffs/

Coding References:

https://openjfx.io/
https://openjfx.io/javadoc/19/
https://docs.oracle.com/en/java/javase/17/docs/api/
https://liveexample.pearsoncmg.com/html/KeyEventDemo.html
https://liveexample.pearsoncmg.com/html/ShowEllipse.html
http://android-er.blogspot.com/2015/01/drag-and-drop-items-between-listview.html
https://www.youtube.com/watch?v=f7KGXUrAH0g
https://www.youtube.com/watch?v=O6FR_K-5GKM
https://www.baeldung.com/javafx-listview-display-custom-items
https://www.youtube.com/watch?v=9YWYxj6LAUE
https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
https://www.geeksforgeeks.org/javafx-popup-class/
https://www.youtube.com/watch?v=YN6_lOH5ZG4
https://www.youtube.com/watch?v=cqskg3DYH8g
https://www.youtube.com/watch?v=UdGiuDDi7Rg
https://www.youtube.com/watch?v=JNHKrG4IoOs
https://www.youtube.com/watch?v=In7xBl0-I68

Additional credit given to previous class projects by Jason Kauppila (with linked GitHub repositories). (Referenced for understanding how to make some UI elements in JavaFX work properly.)
https://github.com/OrangeJuiceNoPulp/OrangeJuiceMusicApp
https://github.com/OrangeJuiceNoPulp/ExtendedTicTacToe 
https://github.com/OrangeJuiceNoPulp/UnitConverterGUI

Additional credits also given to a previous class project by Stefan Coica, which was named FourKingdomsChess. (Referenced for understanding how to make some UI elements in JavaFX work properly.)

 */