/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Entities.MesAmis;
import Entities.Pack;
import Entities.Produit;
import Entities.User;
import Services.CrudPack;
import com.sun.prism.impl.Disposer.Record;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class MonProfilController implements Initializable {

    @FXML
    private AnchorPane ancho;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox amis;
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
        
        
        
CrudPack s=new CrudPack();
    User ussr=User.getInstance();
    
        List<MesAmis> list = (ArrayList<MesAmis>)s.mesAmis(ussr.getId());
     
        
         for(int i=0;i<list.size();i++){
             Hyperlink titre=new Hyperlink();
         titre.setText("Supprimer  " +list.get(i).getPseudo()+" ??");
         MesAmis amiss = list.get(i);
         
            titre.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
              
              
                
                  
                       
                   try {
                        list.remove(amiss);
                       s.DeleteAmis(amiss.getIdMesAmis());
                       
                      
                   } catch (SQLException ex) {
                       Logger.getLogger(MonProfilController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                     
                     
           
                  
                   }
               
               });
           
          
         
         
        ImageView img=new ImageView();
     
     FileInputStream input;
            try {
                User us = new User();
                
                us = s.RechercheU(list.get(i).getPseudo());
                input = new FileInputStream(us.getImage());
                Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
                img.setImage(img_evt);
            } catch (FileNotFoundException ex) {
            } catch (IOException io) {
            } catch (SQLException ex) {
                Logger.getLogger(MonProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            img.setFitHeight(300);
            img.setFitWidth(350);
            titre.setFont(Font.font("verdana", 25));
            VBox vbox = new VBox();
            vbox.getChildren().setAll(titre, img);
            amis.getChildren().add(vbox);

        
      

        }
        

    
   }

    @FXML
    private void Menu(MouseEvent event) {
    }

    @FXML
    private void planif(MouseEvent event) {
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        
         Stage stage = (Stage) acceuil.getScene().getWindow();
            stage.close();
        VBox parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/Acceuil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
        
        
    }
    
    
    
    
    
}
