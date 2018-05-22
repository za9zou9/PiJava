/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstUtilisateurs;

import Entities.Commercant;
import Entities.User;
import EventsUsers.FXMListeDesEventsController;
import GstEvenements.FXMLAjoutController;
import Services.CommercantServices;
import Services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLoginController implements Initializable {
public  int IdCo;
    @FXML
    private FontAwesomeIconView valid;
    @FXML
    private Pane m;
    @FXML
    private JFXPasswordField mdp;

    public int getIdCo() {
        return IdCo;
    }

    public void setIdCo(int IdCo) {
        this.IdCo = IdCo;
    }

   


    @FXML
    private JFXRadioButton user;
    @FXML
    private JFXRadioButton com;
    @FXML
    private JFXTextField pseudo;
   
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

    @FXML
    private void valider(MouseEvent event) throws IOException {
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
 IdCo=c.getIdCommercant();
 Commercant.setInstance(c);
  Stage stage = (Stage) valid.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/GstProduits/FXMLGerer.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
 
       }
        }
        
        
        else if(user.isSelected()==true) {
        User usr=us.selectUsersAllForLogin2(pseudo.getText());
    
          String MDP=mdp.getText()+"{"+usr.getSalt()+"}";
            
        if ((pseudo.getText().equals("admin"))&&(mdp.getText().equals("admin")))
        { Alert abc = new Alert(Alert.AlertType.INFORMATION);
                abc.setTitle("Welcome");
                abc.setHeaderText(null);
                abc.setContentText("Partie administration de MySoulmate");
                abc.showAndWait();
         Stage stage = (Stage) valid.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/backoffice.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
        }
            
            
            
       else if (usr.getId()==0) {Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText("Informations invalides");
 a.showAndWait();}
       
        else if ((usr.getId()!=0)&&(usr.getPassword().equals(MDP)==false)) {Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText("Informations invalides");
 a.showAndWait();}
     
       else {Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Welcome");
                a1.setHeaderText(null);
                a1.setContentText("Bienvenue dans MySoulmate");
 a1.showAndWait();
        IdCo=usr.getId();
       User.setInstance(usr);
        
        
        
      Stage stage = (Stage) valid.getScene().getWindow();
            stage.close();
        VBox parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/Acceuil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
        
        
        
        
        
       
        
        
      }
        }
        
        
    }
    


    
    
}
