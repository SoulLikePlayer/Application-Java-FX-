package org.example.demo.TaskGestionnaire;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GestionnaireDeTaches extends Application {

    private ObservableList<String> taches;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);

        taches = FXCollections.observableArrayList();
        ListView<String> listeTaches = new ListView<>(taches);

        TextField champSaisie = new TextField();
        champSaisie.setPromptText("Entrez une tâche");

        Button boutonAjouter = new Button("Ajouter Tâche");
        boutonAjouter.setOnAction(e -> {
            String tache = champSaisie.getText();
            if (!tache.isEmpty()) {
                taches.add(tache);
                champSaisie.clear();
            }
        });

        Button boutonSupprimer = new Button("Supprimer Tâche");
        boutonSupprimer.setOnAction(e -> {
            int indiceSelectionne = listeTaches.getSelectionModel().getSelectedIndex();
            if (indiceSelectionne != -1) {
                taches.remove(indiceSelectionne);
            }
        });

        HBox boiteSaisie = new HBox(10);
        boiteSaisie.setPadding(new Insets(10));
        boiteSaisie.getChildren().addAll(champSaisie, boutonAjouter, boutonSupprimer);

        root.setCenter(listeTaches);
        root.setBottom(boiteSaisie);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestionnaire de Tâches");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
