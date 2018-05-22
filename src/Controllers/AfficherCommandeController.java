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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AfficherCommandeController implements Initializable {
 private ObservableList<Commande> data=FXCollections.observableArrayList();
   public ObservableList<Produit> data2=FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> quantite;
    @FXML
    private TableView<Commande> panier;
    @FXML
    private Button changerQte;
    @FXML
    private Button Annuler;
    @FXML
    private TextField qte;
    @FXML
    private Button calcul;
    @FXML
    private TextField calculprix;
 public ObservableList<Commande> data3=FXCollections.observableArrayList();
    @FXML
    private TextField Prix1;
    @FXML
    private VBox rendu;
    @FXML
    private ImageView retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          try {
           
            listeCommande();
            panier.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
         Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(AfficherCommandeController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    
   public void listeCommande() throws SQLException, DocumentException, BadElementException, IOException{
               
    CommandeServices sp = new CommandeServices();

    List<Commande> datavalue = sp.selectCommandeAll();
   data = FXCollections.observableArrayList(datavalue);
           nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
   
  panier.setItems(data);
  sp.selectCommande1();
   }  
public Commande com;
public Produit pan;
public Produit produ;
public Commande co;
    @FXML
    private void onselectLigne(MouseEvent event) {
          com= panier.getSelectionModel().getSelectedItem();
          ProduitServices ps=new ProduitServices();
        List datavalue = ps.selectProduitsAll2();
              data2 = FXCollections.observableArrayList(datavalue);
               for(int i=0;i<data2.size();i++)
           {
           if(data2.get(i).getIdProduit()==com.getIdProduit()){
             
               System.out.println(""+data2.get(i).getIdProduit());
                System.out.println(""+com.getIdProduit());
           Prix1.setText(Integer.toString(data2.get(i).getPrix()));
           }
    
      if (com != null){
        qte.setText(Integer.toString(com.getQuantite()));  
         
       
       }  }
          
    }
    @FXML
    private void Changerquantite(ActionEvent event) {
          ProduitServices ps=new ProduitServices();
        List datavalue = ps.selectProduitsAll2();
              data2 = FXCollections.observableArrayList(datavalue);
                CommandeServices fs = new CommandeServices();
           int com2 =Integer.parseInt(qte.getText());
               for(int i=0;i<data2.size();i++)
           {
           if(data2.get(i).getIdProduit()==com.getIdProduit()){
             int qt=data2.get(i).getQuantite();
               com.setQuantite(com2); 
               fs.UpdateCommande(com, com.getIdProduit());
               int qte1=qt-com.getQuantite();
               data2.get(i).setQuantite(qte1);
               ps.UpdateProduit(data2.get(i), data2.get(i).getIdProduit());
     
           }
 }}

    @FXML
    private void annulerCommande(ActionEvent event) throws SQLException {
       
           int com2 =Integer.parseInt(qte.getText());
     com.setQuantite(com2); 
      CommandeServices cs = new CommandeServices();
        cs.DeleteCommande(com.getIdProduit());
        panier.getItems().removeAll(panier.getSelectionModel().getSelectedItem());
        panier.getSelectionModel().select(null);
    }

    @FXML
    private void CalculPrixTotal(ActionEvent event) {
         
        CommandeServices cs = new CommandeServices();
        List clist=cs.selectCommandeP();
         data3 = FXCollections.observableArrayList(clist);
        int com2 =Integer.parseInt(qte.getText());
      int prixtot=Integer.parseInt(Prix1.getText());
        
        
       calculprix.setText(Integer.toString(com2*prixtot));
  
    }

    @FXML
    private void clic(MouseEvent event) throws IOException {
        
            
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherProduit.fxml"));
        Parent root = loader.load();
        AfficherProduitController c = loader.getController();
        retour.getScene().setRoot(root);
    }
}
