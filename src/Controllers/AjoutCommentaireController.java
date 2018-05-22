/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import Services.CommentaireServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AjoutCommentaireController implements Initializable {

    @FXML
    private TextArea commentaire;
    @FXML
    private Button listeCommentaire;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterCommentaire(ActionEvent event) throws IOException, SQLException {
        
       CommentaireServices fs = new CommentaireServices();
        
        Commentaire c = new Commentaire(commentaire.getText());
        fs.insertCommentaire(c);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/afficherCommentaire.fxml"));
        Parent root = loader.load();
        AfficherCommentaireController x = loader.getController();
   
        commentaire.getScene().setRoot(root);
    }

    @FXML
    private void Menu(MouseEvent event) {
    }

    @FXML
    private void planif(MouseEvent event) {
    }
    
}
