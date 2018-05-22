/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commande;
import Entities.Commentaire;
import Entities.Produit;
import Services.CommandeServices;
import Services.CommentaireServices;
import Services.ProduitServices;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AfficherProduitController implements Initializable {
 private ObservableList<Produit> data=FXCollections.observableArrayList();
  public ObservableList<Commande> data2=FXCollections.observableArrayList();
  

    @FXML
    private TableView<Produit> catalogue;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> quantite;
    @FXML
    private Button ajout;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private AnchorPane parent;
    @FXML
    private VBox rendu;
    @FXML
    private ImageView retour;
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
         try {
          
            listeProduit();
           
            catalogue.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void listeProduit() throws SQLException{
                
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

public Produit k;
    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
int w=0;
ProduitServices sp = new ProduitServices();

    int prod=p.getQuantite();
  
            CommandeServices fs = new CommandeServices();
           List datavalue = fs.selectCommandeP();
              data2 = FXCollections.observableArrayList(datavalue);
            int q =Integer.parseInt(quantity.getText() );
                    Commande n = new Commande(q,p.getIdProduit());
                    int pro=p.getIdProduit();
                  
           for(int i=0;i<data2.size();i++)
           {
           if(data2.get(i).getIdProduit()==p.getIdProduit() && prod>0){
          
               w++;
              n.setQuantite(data2.get(i).getQuantite()+q);
              p.setQuantite(prod-q);
            fs.UpdateCommande(n,n.getIdProduit());
            sp.UpdateProduit(p,pro);
            
           }}
       
        if (w==0 && prod>0){
              p.setQuantite(prod-q); 
        fs.ajouterCommande(n);
        sp.UpdateProduit(p,pro);
        
      }
           
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherCommande.fxml"));
        Parent root = loader.load();
        AfficherCommandeController x = loader.getController();
   
        quantity.getScene().setRoot(root);
    }
public Produit p;

    @FXML
    private void OnSelectLigne(javafx.scene.input.MouseEvent event) {
             p = catalogue.getSelectionModel().getSelectedItem();
       if (p != null){
          name.setText(p.getNom());
          price.setText(Integer.toString(p.getPrix()));
          
         
          int id=p.getIdProduit();
           
           
            }
    }

    @FXML
    private void ret(javafx.scene.input.MouseEvent event) throws IOException {
        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/Acceuil.fxml"));
        Parent root = loader.load();
        AcceuilController c = loader.getController();
        retour.getScene().setRoot(root);
    }

}
    
    

