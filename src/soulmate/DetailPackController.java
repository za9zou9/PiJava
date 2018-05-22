/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Entities.Pack;
import Entities.User;
import Services.CrudPack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DetailPackController implements Initializable {

    @FXML
    private Label prix;
    private ImageView imagePack;
    @FXML
    private Text prix1;
    @FXML
    private Label salle;
    @FXML
    private Label musique;
    @FXML
    private Label fleurs;
    @FXML
    private Label id;
    @FXML
    private Button AjouterMesPack;
    @FXML
    private ImageView imgview;
    @FXML
    private ImageView accueil;

    public Label getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix.setText(""+prix);
    }

    public ImageView getImagePack() {
        return imagePack;
    }

    public void setImagePack(ImageView imagePack) {
        this.imagePack = imagePack;
    }

    public Text getPrix1() {
        return prix1;
    }

    public void setPrix1(Text prix1) {
        this.prix1 = prix1;
    }

    public Label getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle.setText(salle) ;
    }

    public Label getMusique() {
        return musique;
    }

    public void setMusique(String musique) {
        this.musique.setText(musique);
    }

    public Label getFleurs() {
        return fleurs;
    }

    public void setFleurs(String fleurs) {
        this.fleurs.setText(fleurs);
    }

    public Label getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setText(id);
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        
        
        
        
        CrudPack p = new CrudPack();       

     Pack u = p.RechercheIdPack(Integer.parseInt(this.getId().getText())); 
       
        
    int idPack = u.getIdPack();
        
    User ussr = User.getInstance();
    
         p.AjouterMesPack(idPack , ussr.getId());
      
        
        
    }
    
    
    
    public void setImgview(String url) throws IOException {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imgview.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailPackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(DetailPackController.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    @FXML
    private void accueil(MouseEvent event) throws IOException {
        
        
          Stage stage = (Stage) accueil.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/soulmate/PackFront.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
        
        
    }
    
    
}
