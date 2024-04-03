package org.example.demo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BeginPath extends Exception {
    private final String cheminDeDebut;

    BeginPath() {
        Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
        this.cheminDeDebut = desktopPath.toString() + File.separator;
    }

    public String getCheminDeDebut() {
        return cheminDeDebut;
    }
}