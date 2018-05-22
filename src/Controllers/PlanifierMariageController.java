/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class PlanifierMariageController implements Initializable {

    @FXML
    private VBox parent;
    @FXML
    private Label services1;
    @FXML
    private Label services;
    @FXML
    private Label veterinaires;
    @FXML
    private Label evenements;
    @FXML
    private Label acceuil1;
    @FXML
    private Label serv;
    @FXML
    private Label even;
    @FXML
    private Label cat;
    @FXML
    private ImageView Packs;
    @FXML
    private ImageView pan;
    @FXML
    private ImageView catalo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Menu(MouseEvent event) {
    }

    @FXML
    private void planifier(MouseEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherProduit.fxml"));
        Parent root = loader.load();
        AfficherProduitController c = loader.getController();
        cat.getScene().setRoot(root);
    }

    @FXML
    private void affichProd(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherProduit.fxml"));
        Parent root = loader.load();
        AfficherProduitController c = loader.getController();
        cat.getScene().setRoot(root);
    }

    @FXML
    private void pack(MouseEvent event) throws IOException {
        
        
          Stage stage = (Stage) Packs.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/soulmate/PackFront.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void cata(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherProduit.fxml"));
        Parent root = loader.load();
        AfficherProduitController c = loader.getController();
        catalo.getScene().setRoot(root);
        
    }

    @FXML
    private void panier(MouseEvent event) throws IOException {
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherCommande.fxml"));
        Parent root = loader.load();
        AfficherCommandeController c = loader.getController();
        pan.getScene().setRoot(root);
        
    }
    
}
