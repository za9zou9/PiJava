/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsUsers;

import Entities.Partcipation;
import Entities.User;
import GstEvenements.FXMLAjoutController;
import GstProduits.NewFXMainProduits;
import GstUtilisateurs.FXMLoginController;
import Services.ParticipationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLDetailsEventController implements Initializable {
public static int id;
    @FXML
    private Label lieu;
    @FXML
    private FontAwesomeIconView retour;
    @FXML
    private AnchorPane ancho;
    @FXML
    public JFXListView<String> listPart;

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JFXButton getParticiper() {
        return participer;
    }

    public void setParticiper(JFXButton participer) {
        this.participer = participer;
    }

    public JFXButton getAnnuler() {
        return annuler;
    }

    public void setAnnuler(JFXButton annuler) {
        this.annuler = annuler;
    }

    
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private ImageView imgview;
    @FXML
    public JFXButton participer;
    @FXML
    public JFXButton annuler;
ParticipationService parts=new ParticipationService();    
      List<Partcipation> liste=parts.selectParticipants(id);

    public Label getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu.setText(lieu);
    }

      
    public Label getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public Label getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date.setText(""+date);
    }

    public ImageView getImgview() {
        return imgview;
    }

    public void setImgview(String url) throws IOException {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imgview.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(FXMLDetailsEventController.class.getName()).log(Level.SEVERE, null, io);
        }
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      
       
        
    
    }    

    @FXML
    private void participation(ActionEvent event) throws SQLException {
        User ussr=User.getInstance();
            
            ParticipationService part=new ParticipationService();
        part.insertParticipation(ussr.getId(), id);
         Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Message");
                a1.setHeaderText(null);
                a1.setContentText("Vous participez désormais à cet évènement");
 a1.showAndWait();
         participer.setDisable(true);
         annuler.setDisable(false);
        
            
        
        
        
         
    }

    @FXML
    private void annulation(ActionEvent event) throws SQLException {
         ParticipationService part=new ParticipationService();
         int r=supp();
         part.DeleteParticipation(r);
          Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Message");
                a1.setHeaderText(null);
                a1.setContentText("Vous ne participez pls à cet évènement");
 a1.showAndWait();
         participer.setDisable(false);
         annuler.setDisable(true);
         
    }
    
    public int supp(){
         Partcipation pr=new Partcipation();
        
          User ussr=User.getInstance();
            
            ParticipationService part=new ParticipationService();
        List<Partcipation> liste=part.selectParticipants(id);
        for(int i=0;i<liste.size();i++){
            if (liste.get(i).getId()==ussr.getId()) pr=liste.get(i);
        }
        
         
        
    return pr.getReponse();

    }

    @FXML
    private void retourner(MouseEvent event) {
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

    
           
           
    
    
    
   
    
}
