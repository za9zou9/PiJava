/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.dataSource;
import Entities.Commentaire;
import Entities.Produit;
import Entities.Stories;
import Services.CommentaireServices;
import Services.ProduitServices;
import Services.StoriesServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AfficherCommentaireController implements Initializable {

    @FXML
    private TableColumn<?, ?> comm;
     public ObservableList<Stories> data2=FXCollections.observableArrayList();

  private ObservableList<Commentaire> data=FXCollections.observableArrayList();
    private ObservableList<Commentaire> datax=FXCollections.observableArrayList();
    @FXML
    private TableView<Commentaire> commentaire;
    @FXML
    private Button modif;
    @FXML
    private TextField modifcom;
    @FXML
    private Button supprimer;
   
    private Connection con =dataSource.getInstance().getConnection();
    private Statement state;
    @FXML
    private TableColumn<?, ?> comm2;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label veterinaires;
    @FXML
    private Label evenements;
    @FXML
    private Label evenements1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            listeCommentaire();
            commentaire.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private Commentaire z ;
    @FXML
    public void OnSelectLigne() throws SQLException{
        z = commentaire.getSelectionModel().getSelectedItem();
        if (z != null){
          modifcom.setText(z.getDescription());
         
        
        
        }
        
    }
    public void listeCommentaire() throws SQLException{
                    StoriesServices ss=new StoriesServices();
        List data1 = ss.selectStoriesAll();
              data2 = FXCollections.observableArrayList(data1);
    CommentaireServices sp = new CommentaireServices();
     System.out.println(""+z.getIdStorie());
   List<Commentaire> datavalue = sp.selectCommentairesAll(1);
   data = FXCollections.observableArrayList(datavalue);
    comm.setCellValueFactory(new PropertyValueFactory<>("description"));
commentaire.setItems(data);
    }
   

    @FXML
    private void modifierCommentaire(ActionEvent event) throws SQLException {
       z.setDescription(modifcom.getText()); 
    CommentaireServices sp = new CommentaireServices();
        
        sp.UpdateCommentaire(z,z.getIdCommentaire());
   
       }

    @FXML
    private void supprimerCommentaire(ActionEvent event) throws SQLException {
         z.setDescription(modifcom.getText()); 
      CommentaireServices sp = new CommentaireServices();
        sp.DeleteCommentaire(z.getIdCommentaire());
        commentaire.getItems().removeAll(commentaire.getSelectionModel().getSelectedItem());
        commentaire.getSelectionModel().select(null);
    }


    @FXML
    private void Menu(MouseEvent event) {
    }

    @FXML
    private void planif(MouseEvent event) {
    }
}

   