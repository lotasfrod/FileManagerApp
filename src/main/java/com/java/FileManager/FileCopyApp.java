package com.java.FileManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class FileCopyApp extends Application {
    private ListView<String> fileListView;
    private File selectedDirectory;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Файловый менеджер");

        fileListView = new ListView<>();
        fileListView.setPrefHeight(200);

        Button selectDirectoryButton = new Button("Выбрать директорию");
        selectDirectoryButton.setOnAction(e -> selectDirectory());

        Button copyFileButton = new Button("Скопировать файл");
        copyFileButton.setOnAction(e -> copySelectedFile());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(fileListView, selectDirectoryButton, copyFileButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectedDirectory = DirectoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            displayFilesInDirectory();
        }
    }

    private void displayFilesInDirectory() {
        fileListView.getItems().clear();
        File[] files = selectedDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    long fileSize = file.length();
                    String formattedSize = FileUtils.formatFileSize(fileSize);
                    String listItem = file.getName() + " (" + formattedSize + ")";
                    fileListView.getItems().add(listItem);
                }
            }
        }
    }

    private void copySelectedFile() {
        String selectedItem = fileListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String fileName = selectedItem.split(" ")[0];
            File sourceFile = new File(selectedDirectory, fileName);

            DirectoryChooser directoryChooser = new DirectoryChooser();
            File destinationDirectory = directoryChooser.showDialog(null);
            if (destinationDirectory != null) {
                FileUtils.copyFile(sourceFile, destinationDirectory);
            }
        }
    }
}