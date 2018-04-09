/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;

import Entities.Evenement;
import Entities.Partcipation;
import Entities.User;
import Services.EvenementServices;
import Services.ParticipationService;
import Services.UserServices;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLParticipantsController implements Initializable {
private int id;
    @FXML
    public TableColumn<User, String> prenom;
    @FXML
    public TableColumn<User, Date> date;
    @FXML
    public TableColumn<User, String> email;
    @FXML
    public TableColumn<User, String> num;
    @FXML
    public TableColumn<User, String> etat;
    @FXML
    private FontAwesomeIconView retour;
   
      
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @FXML
    private AnchorPane ancho;

    @FXML
    public TableColumn<User, String> nom;
    @FXML
    public TableColumn<User, String> pseudo;


 
   
    
    @FXML
    public TableView<User> matable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        
       
  

       
 
        
        
    }    

    @FXML
    private void retourner(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerer.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstEvenements.FXMLGererController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstEvenements.FXMLGererController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
