/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Controllers.AcceuilController;
import Entities.Pack;
import Entities.Produit;
import Entities.User;
import Services.CrudPack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ProfilsController implements Initializable {

    @FXML
    private AnchorPane ancho;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox vb;
    @FXML
    private ImageView acceuil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudPack es=new CrudPack();
        User ussr = User.getInstance();
        List<User> listeU = es.UserAll(ussr.getId());
        

     
        for(int i=0;i<listeU.size();i++){
           
            Hyperlink titre=new Hyperlink();
           User u=listeU.get(i);
           
           
           titre.setText(u.getUsername());
           titre.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("DetailsProfils.fxml"));
                Parent root;
                
                   try {
                       
                       
                       root=loader.load();
                       DetailsProfilsController dc=loader.getController();
                       dc.setSexe(""+u.getSexe());
                 dc.setEtat(""+u.getEtat());
                 dc.setAge(""+u.getAge());
                 dc.setUsername(""+u.getUsername());
                 
                       Scene scene=ancho.getScene();
                       scene.setRoot(root);
                      
                   } catch (IOException ex) {
                       Logger.getLogger(PackFrontController.class.getName()).log(Level.SEVERE, null, ex);
                   } 
               }
           
           }
           
           
           );
           
     ImageView img=new ImageView();
     
     FileInputStream input;
            try {
                input = new FileInputStream(listeU.get(i).getImage());
                Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
                img.setImage(img_evt);
            } catch (FileNotFoundException ex) {
            } catch (IOException io) {
            }
            img.setFitHeight(300);
            img.setFitWidth(350);
            titre.setFont(Font.font("verdana", 25));
            VBox vbox = new VBox();
            vbox.getChildren().setAll(titre, img);
            vb.getChildren().add(vbox);
                   
                   
                   
                   
                   
                   
                   
            
        }
           }

    @FXML
    private void acceuilapp(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/Acceuil.fxml"));
        Parent root = loader.load();
        AcceuilController c = loader.getController();
        acceuil.getScene().setRoot(root);
    }
           
    }    
    