package com.example.unused;

import java.util.Scanner;

import javafx.scene.control.TextArea;

public class TestConcept {
    private Scanner fileReader;

    public TestConcept() {
        TextArea textArea = new TextArea();
        fileReader = new Scanner(this.getClass().getResourceAsStream("instructions.txt"));
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine() + "\n";
            textArea.appendText(line);
        }
        fileReader.close();

        textArea.setWrapText(true);
        
    }
}
