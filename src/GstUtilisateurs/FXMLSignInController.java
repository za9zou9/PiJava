/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstUtilisateurs;

import Entities.Commercant;
import Services.CommercantServices;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLSignInController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXTextField mdp;
    @FXML
    private Button inscri;
    @FXML
    private AnchorPane ancho;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) throws SQLException {
       if ((pseudo.getText().equals(""))||(mdp.getText().equals(""))||(nom.getText().equals(""))||(prenom.getText().equals(""))) {Alert cc = new Alert(Alert.AlertType.ERROR);
                cc.setTitle("Erreur");
                cc.setHeaderText(null);
                cc.setContentText("Champ(s) Vide(s)");
 cc.showAndWait();} 
       
       else {Commercant c=new Commercant();
       CommercantServices cs=new CommercantServices();
       c.setPseudo(pseudo.getText());
       c.setNom(nom.getText());
       c.setPrenom(prenom.getText());
       c.setMdp(mdp.getText());
       cs.insertCommercant(c);
       
       Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Confirmation");
                a1.setHeaderText(null);
                a1.setContentText("Inscription effectué avec succès");
 a1.showAndWait();
 
  FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLogin.fxml"));
        try {
            Parent root;
            root = loader.load();
            FXMLoginController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 
 
 
       }
        
    }
    
}
