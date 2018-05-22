/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstProduits;

import Services.ProduitServices;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static sun.net.www.http.HttpClient.New;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLConsulterController implements Initializable {
   private int LeId;
    @FXML
    public ImageView imgview;
    @FXML
    private AnchorPane ancho;
    @FXML
    private FontAwesomeIconView retour;

    public int getLeId() {
        return LeId;
    }

    public void setLeId(int LeId) {
        this.LeId = LeId;
    }
    
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
   
    @FXML
    private Label nom;
    @FXML
    private Label type;
    @FXML
    private Label description;
    @FXML
    private Label quantite;
    @FXML
    private Label prix;
    @FXML
    private Label region;
    @FXML
    private FontAwesomeIconView supp;
    @FXML
    private FontAwesomeIconView mod;

    ProduitServices ps=new ProduitServices();
    
    public Label getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region.setText(region);
    }
    
    

    public Label getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public Label getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public Label getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite.setText(""+quantite);
    }

    public Label getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix.setText(""+prix);
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       
    }    


    
    
   
    private void openFile() {
        FileInputStream input;
        try {
           
            input = new FileInputStream(url);
            Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            imgview.setImage(img_produit);
        } catch (IOException ex) {
            
        }
    }

    @FXML
    private void retourner(MouseEvent event) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerer.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstProduits.FXMLGererController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstProduits.FXMLGererController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(MouseEvent event) {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLModifier.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstProduits.FXMLModifierController interf = loader.getController();
            interf.setDescription(description.getText());
            interf.setLeId(LeId);
            interf.setNom(nom.getText());
            interf.setPrix(Integer.parseInt(prix.getText()));
            interf.setQuantite(Integer.parseInt(quantite.getText()));
            interf.setRegion(region.getText());
            interf.setType(type.getText());
            interf.setImgview(url);
            interf.setUrl(url);
            
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstProduits.FXMLModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(MouseEvent event) throws SQLException {
         Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
                a1.setTitle("Suppression");
                a1.setHeaderText(null);
                a1.setContentText("Etes vous vraiment sûr de vouloir supprimer ce produit");
  Optional<ButtonType> button = a1.showAndWait();
  if (button.get() == ButtonType.OK) {
                         
        ps.DeleteProduit(LeId);
       FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerer.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstProduits.FXMLGererController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstProduits.FXMLGererController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    }
     
                  
    }
    
  
    
    
    
    
    
    
    
    
    
}
