/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commande;
import Entities.Produit;
import Services.ProduitServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AfficherProduitAdminController implements Initializable {
     private ObservableList<Produit> data=FXCollections.observableArrayList();
  public ObservableList<Commande> data2=FXCollections.observableArrayList();

    @FXML
    private Label acceuil;
    @FXML
    private Label produits;
    @FXML
    private Label afficherProduits;
    @FXML
    private Label ajouterProduits;
    @FXML
    private Label confirmerProduit;
    @FXML
    private Label packs;
    @FXML
    private Label afficherPacks;
    @FXML
    private Label ajouterPacks;
    @FXML
    private Label acceuil111;
    @FXML
    private Label afficherEvenements;
    @FXML
    private Label AjouterEvenements;
    @FXML
    private TableView<Produit> catalogue;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> quantite;
    @FXML
    private TableColumn<?, ?> nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
          
            affichProd();
           
            catalogue.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    private void affichProd() throws SQLException{
        ProduitServices sp = new ProduitServices();

    List<Produit> datavalue = sp.selectProduitsAll2();
  
   data = FXCollections.observableArrayList(datavalue);
    image.setCellValueFactory(new PropertyValueFactory<>("image"));
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    
 
   catalogue.setItems(data);
  
        
    }
    public Produit p;


    private void onselectLigne(MouseEvent event) {
         p = catalogue.getSelectionModel().getSelectedItem();
       if (p != null){
          nom.setText(p.getNom());
          prix.setText(Integer.toString(p.getPrix()));
         
          int id=p.getIdProduit();
           System.out.println(""+id);
           
            }
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/backoffice.fxml"));
        Parent root = loader.load();
        BackofficeController c = loader.getController();
        acceuil.getScene().setRoot(root);
        
    }


    
}
