package com.java.FileManager;

import javafx.stage.Window;

import java.io.File;

public class DirectoryChooser {
    public static File showDialog(Window ownerWindow) {
        javafx.stage.DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
        return directoryChooser.showDialog(ownerWindow);
    }
}