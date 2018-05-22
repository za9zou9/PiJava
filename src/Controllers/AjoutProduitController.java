/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Produit;
import Services.ProduitServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField nomproduit;
    @FXML
    private TextField prix;
    @FXML
    private TextField nb_enstock;
    @FXML
    private TextArea description;
    @FXML
    private Button bouton;
    @FXML
    private Button btnimage;
    @FXML
    private TextArea image_prod;
    @FXML
    private VBox parent;
    @FXML
    private Label acceuil;
    @FXML
    private Label acceuil1;
    @FXML
    private Label services;
    @FXML
    private Label acceuil11;
    @FXML
    private Label services2;
    @FXML
    private Label services11;
    @FXML
    private Label acceuil111;
    @FXML
    private Label services21;
    @FXML
    private Label services111;
    @FXML
    private Label services1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterproduit(ActionEvent event) throws IOException {
          ProduitServices fs = new ProduitServices();
        
        Produit p = new Produit( nomproduit.getText(),Integer.parseInt(prix.getText()),description.getText(),Integer.parseInt(nb_enstock.getText()),image_prod.getText() );
        fs.AjouterProduit(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherProduitAdmin.fxml"));
        Parent root = loader.load();
        AfficherProduitAdminController c = loader.getController();
   
        nomproduit.getScene().setRoot(root);
    }

    @FXML
    private void imagechooser(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
            
         btnimage.setOnAction(e-> {
             File choix = fileChooser.showOpenDialog(null);
             if (choix != null) {
                 image_prod.setText(choix.getName());
              } else {System.out.println("Image introuvable");}
    
    });
    }
    
}
