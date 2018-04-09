/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstProduits;

import Entities.Produit;
import EventsUsers.FXMLDetailsEventController;
import EventsUsers.FXMListeDesEventsController;
import Services.EvenementServices;
import com.sun.prism.impl.Disposer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author skan
 */
public class ReadButton extends TableCell<Disposer.Record, Boolean> {
    final Button consulter = new Button("+");
    
   public ReadButton(ObservableList<Produit> evtList) {

        consulter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                EvenementServices ge = new EvenementServices();
                FileInputStream input;
               FXMLLoader loader= new FXMLLoader(getClass().getResource("FXMLConsulter.fxml"));
                Parent root;
                   try {
                       root=loader.load();
                       FXMLConsulterController dc=loader.getController();
                     
                       Produit currentPdt = (Produit) ReadButton.this.getTableView().getItems().get(ReadButton.this.getIndex());
                       
                       dc.setDescription(currentPdt.getDescription());
                       dc.setLeId(currentPdt.getIdProduit());
                       dc.setNom(currentPdt.getNom());
                       dc.setPrix(currentPdt.getPrix());
                       dc.setQuantite(currentPdt.getQuantite());
                       dc.setRegion(currentPdt.getRegion());
                       dc.setType(currentPdt.getType());
                       dc.setUrl(currentPdt.getImage());
                       
                        input = new FileInputStream(currentPdt.getImage());
                Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
                dc.imgview.setImage(img_evt);
           
                       
                        Stage stage=new Stage();
            Scene scene = new Scene(root);
      
  stage.setScene(scene);
        stage.show();
         } catch (FileNotFoundException ex){
                   } catch (IOException ex) {
                       Logger.getLogger(FXMLGererController.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
        });
    }

    
@Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(consulter);
        }
    }









}


    

