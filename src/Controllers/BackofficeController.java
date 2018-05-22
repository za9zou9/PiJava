/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class BackofficeController implements Initializable {

    @FXML
    private Label acceuil;
    @FXML
    private Label acceuil111;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private Label produits;
    @FXML
    private Label afficherProduits;
    @FXML
    private Label ajouterProduits;
    @FXML
    private Label packs;
    @FXML
    private Label afficherPacks;
    @FXML
    private Label ajouterPacks;
    @FXML
    private Label afficherEvenements;
    @FXML
    private Label AjouterEvenements;
    @FXML
    private Label confirmerProduit;
    @FXML
    private FontAwesomeIconView decon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void ajoutProd(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/ajoutProduit.fxml"));
        Parent root = loader.load();
        AjoutProduitController c = loader.getController();
        ajouterProduits.getScene().setRoot(root);
    }



    @FXML
    private void confirm(MouseEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/ConfirmerProduit.fxml"));
        Parent root = loader.load();
        ConfirmerProduitController c = loader.getController();
        confirmerProduit.getScene().setRoot(root);
    }

    @FXML
    private void afficherPacks(MouseEvent event) throws IOException {
         Stage stage = (Stage) afficherPacks.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/soulmate/affichage.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void ajouterPacks(MouseEvent event) throws IOException {
        Stage stage = (Stage) ajouterPacks.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/soulmate/ajout.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void afficherevenements(MouseEvent event) throws IOException {
         Stage stage = (Stage) afficherEvenements.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/GstEvenements/FXMLGerer.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void ajouterevenements(MouseEvent event) throws IOException {
        Stage stage = (Stage) AjouterEvenements.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/GstEvenements/FXMLAjout.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void affichProdA(MouseEvent event) throws IOException {
        
            
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherProduitAdmin.fxml"));
        Parent root = loader.load();
        AfficherProduitAdminController c = loader.getController();
        afficherProduits.getScene().setRoot(root);
        
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        
       Stage stage = (Stage) decon.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/GstUtilisateurs/FXMLogin.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    
}
