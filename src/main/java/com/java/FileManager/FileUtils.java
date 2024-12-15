package com.java.FileManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;

public class FileUtils {
    public static void copyFile(File sourceFile, File destinationDirectory) {
        Path sourcePath = Paths.get(sourceFile.getAbsolutePath());
        Path destinationPath = Paths.get(destinationDirectory.getAbsolutePath(), sourceFile.getName());
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            showSuccessDialog();
        } catch (IOException e) {
            showErrorDialog(e);
        }
    }

    private static void showSuccessDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Успех");
        alert.setHeaderText(null);
        alert.setContentText("Файл скопирован успешно");
        alert.showAndWait();
    }

    private static void showErrorDialog(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка при копировании файла");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public static String formatFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"Б", "КБ", "МБ", "ГБ", "ТБ"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}


