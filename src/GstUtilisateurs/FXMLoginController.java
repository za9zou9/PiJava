/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstUtilisateurs;

import Entities.Commercant;
import Entities.User;
import GstEvenements.FXMLAjoutController;
import Services.CommercantServices;
import Services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLoginController implements Initializable {
public static int IdCo;
    @FXML
    private JFXRadioButton user;
    @FXML
    private JFXRadioButton com;
    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXTextField mdp;
    @FXML
    private JFXButton inscri;
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
    private void valider(ActionEvent event) {
        UserServices us=new UserServices();
        CommercantServices cs=new CommercantServices();
        
          if ((pseudo.getText().equals(""))||(mdp.getText().equals(""))) {Alert cc = new Alert(Alert.AlertType.ERROR);
                cc.setTitle("Erreur");
                cc.setHeaderText(null);
                cc.setContentText("Champ(s) Vide(s)");
 cc.showAndWait();}
       
        
        
        
       else if ((com.isSelected()==false)&&(user.isSelected()==false)){Alert b = new Alert(Alert.AlertType.ERROR);
                b.setTitle("Erreur");
                b.setHeaderText(null);
                b.setContentText("Veuillez cocher votre type d'utilisateur");
 b.showAndWait();}
       
         else if ((com.isSelected()==true)&&(user.isSelected()==true)){Alert bc = new Alert(Alert.AlertType.ERROR);
                bc.setTitle("Erreur");
                bc.setHeaderText(null);
                bc.setContentText("Veuillez cocher un seul type d'utilisateur");
 bc.showAndWait();}
        
        
        
        
        
        
        else if (com.isSelected()==true) {
        Commercant c=cs.selectCommercantForLogin(pseudo.getText(), mdp.getText());
     
   
     if (c.getIdCommercant()==null) {Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText("Informations invalides");
 a.showAndWait();}
       else {Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Welcome");
                a1.setHeaderText(null);
                a1.setContentText("Bienvenue dans MySoulmate");
 a1.showAndWait();
       }
        }
        
        
        else if(user.isSelected()==true) {
        User usr=us.selectUsersAllForLogin(pseudo.getText(), mdp.getText());
        
        if (usr.getId()==0) {Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText("Information invalides");
 a.showAndWait();}
       else {Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Welcome");
                a1.setHeaderText(null);
                a1.setContentText("Bienvenue dans MySoulmate");
 a1.showAndWait();
        IdCo=usr.getId();
      }
        }
        
        
        
        
    }

    @FXML
    private void inscription(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSignIn.fxml"));
        try {
            Parent root;
            root = loader.load();
            FXMLSignInController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    


    
    
}
