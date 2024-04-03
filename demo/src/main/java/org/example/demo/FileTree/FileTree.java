package org.example.demo.FileTree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.HashSet;

public class FileTree extends Application {
    HashSet<String> ListeExtension = new HashSet<String>();
    private TextField champDossier;
    private TreeView<String> arborescence;
    private Label cheminComplet;
    BeginPath chemin = new BeginPath();
    private final String cheminDeBase = chemin.getCheminDeDebut();;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FileTree : Rechercheur de fichier");
        System.out.println(cheminDeBase);

        champDossier = new TextField();
        champDossier.setPromptText("Entrez le nom du dossier et appuyez sur Entrée");

        cheminComplet = new Label();

        champDossier.setOnAction(event -> {
            String nomDossier = champDossier.getText();
            String cheminCompletDossier = cheminDeBase + nomDossier;
            File repertoire = new File(cheminCompletDossier);
            if (repertoire.exists() && repertoire.isDirectory()) {
                TreeItem<String> itemRacine = new TreeItem<>(repertoire.getName());
                itemRacine.setExpanded(true);
                ListeExtension.clear();
                remplirArborescence(repertoire, itemRacine);
                arborescence.setRoot(itemRacine);
            } else {
                champDossier.setText("Nom de dossier invalide");
            }
        });

        arborescence = new TreeView<>();
        arborescence.setOnMouseClicked(event -> {
            TreeItem<String> elementSelectionne = arborescence.getSelectionModel().getSelectedItem();
            if (elementSelectionne != null) {
                StringBuilder constructeurChemin = new StringBuilder();
                while (elementSelectionne != null) {
                    constructeurChemin.insert(0, elementSelectionne.getValue());
                    constructeurChemin.insert(0, File.separator);
                    elementSelectionne = elementSelectionne.getParent();
                }
                cheminComplet.setText("C:" + File.separator + "Users" + File.separator + "Louis" + constructeurChemin.toString());
            }
        });

        VBox vbox = new VBox(champDossier, arborescence, cheminComplet);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void remplirArborescence(File fichier, TreeItem<String> itemParent) {
        if (fichier.isDirectory()) {
            File[] fichiers = fichier.listFiles();
            if (fichiers != null) {
                for (File f : fichiers) {
                    TreeItem<String> item = new TreeItem<>(f.getName());
                    itemParent.getChildren().add(item);
                    if (f.isDirectory()){
                        remplirArborescence(f, item);
                    } else {
                        Extension ex = new Extension(item.getValue());
                        ListeExtension.add(ex.extension);
                        ListeExtension.remove(null);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}