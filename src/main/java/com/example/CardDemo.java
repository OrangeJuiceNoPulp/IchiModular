package com.example;

import java.util.Random;

import javafx.application.Application;

import javafx.scene.Scene;
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

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(testPane, 1250, 750);
    primaryStage.setTitle("CardTowerDemo"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
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