/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsUsers;

import Entities.Partcipation;
import Services.ParticipationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLDetailsEventPastController implements Initializable {

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
    private void retourner(MouseEvent event) {
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
