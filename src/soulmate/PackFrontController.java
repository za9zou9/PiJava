/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Entities.Ligne;
import Entities.Pack;
import Entities.Produit;
import Entities.User;
import Services.CrudPack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PackFrontController implements Initializable {

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
        List<Pack> listeE = es.selectPack();
        

     
        for(int i=0;i<listeE.size();i++){
           
            Hyperlink titre=new Hyperlink();
           Pack e=listeE.get(i);
           
           
           titre.setText("Pack");
           titre.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("detailPack.fxml"));
                Parent root;
                
                   try {
                       
                       
                       root=loader.load();
                       DetailPackController dc=loader.getController();
                       User ussr = User.getInstance();
         
                        
                            List<Produit> listeP = es.Produit(e.getIdPack()); 
                           Produit p1=listeP.get(0); 
                           Produit p2=listeP.get(1);
                           Produit p3=listeP.get(2);
                            dc.setSalle(p1.getNom());
                          dc.setMusique(p2.getNom());
                       dc.setFleurs(p3.getNom());
                     
                       if(ussr.getPoint()>5){
                      dc.setPrix(e.getPrixPack()-e.getPrixPack()*20/100);
                       }
                       else {
                        dc.setPrix(e.getPrixPack());
                       }
                      dc.setId(""+e.getIdPack());
                     dc.setImgview(e.getImage());
                     
           
                       Scene scene=ancho.getScene();
                       scene.setRoot(root);
                      
                   } catch (IOException ex) {
                       Logger.getLogger(PackFrontController.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(PackFrontController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           
           }
           
           
           );
           
     ImageView img=new ImageView();
     
     FileInputStream input;
            try {
                input = new FileInputStream(listeE.get(i).getImage());
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
    private void accueil(MouseEvent event) throws IOException {
        
        
          Stage stage = (Stage) acceuil.getScene().getWindow();
            stage.close();
        VBox parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/Acceuil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
        
    }
           
    }    
    
