/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsUsers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLInterfaceAcceuilController implements Initializable {

    @FXML
    private AnchorPane ancho;
    @FXML
    private JFXButton prochain;
    @FXML
    private JFXButton prec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void next(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMListeDesEvents.fxml"));
        try {
            Parent root;
            root = loader.load();
            FXMListeDesEventsController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMListeDesEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void past(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMListeEventsPast.fxml"));
        try {
            Parent root;
            root = loader.load();
            FXMListeEventsPastController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMListeEventsPastController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
