package com.java.FileManager;

import javafx.stage.Window;

import java.io.File;

public class FileChooser {
    public static File showOpenDialog(Window ownerWindow) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        return fileChooser.showOpenDialog(ownerWindow);
    }
}




