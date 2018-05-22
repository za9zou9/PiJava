/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstProduits;

import Entities.Commercant;
import Entities.Produit;
import Services.ProduitServices;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLAjoutController implements Initializable {
private String url;
    @FXML
    private AnchorPane ancho;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField prix;
    @FXML
    private Button choix;
    @FXML
    private ImageView image;
    @FXML
    private Button valider;
    @FXML
    private TextArea description;
    @FXML
    private JFXTextField quantite;
    @FXML
    private FontAwesomeIconView retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choisir(ActionEvent event) {
          final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(ancho.getScene().getWindow());
        if (file != null) {
            openFile(file);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        Produit p= new Produit();
        ProduitServices ps=new ProduitServices();
        Commercant commm=Commercant.getInstance();
         if ((description.getText().equals(""))||(nom.getText().equals(""))||(url==null)||(quantite.getText().equals(""))||(prix.getText().equals(""))||(type.getText().equals(""))||(region.getText().equals("")))
    {Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText("champ(s) vide(s)");
 a.showAndWait();}
         
         else{
        p.setConfirmation(0);
        p.setDescription(description.getText());
        p.setImage(url);
        p.setNom(nom.getText());
        p.setRegion(region.getText());
        p.setType(type.getText());
       p.setIdCommercant(commm.getIdCommercant());
       System.out.println(commm.getIdCommercant());
       try
{
     int var = Integer.parseInt(quantite.getText());
      p.setQuantite(var);
      int var2 = Integer.parseInt(prix.getText());
        p.setPrix(var2);
         ps.insertProduit(p);
           Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Message");
                a1.setHeaderText(null);
                a1.setContentText("Produit créé avec succès");
 a1.showAndWait();
 
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
 
}catch(NumberFormatException  e)
{Alert a2 = new Alert(Alert.AlertType.ERROR);
                a2.setTitle("Erreur");
                a2.setHeaderText(null);
                a2.setContentText("Champ doit prendre un nombre");
 a2.showAndWait();
    }
        
        
       
        
        
        
       
        
       
    } }
    
private void openFile(File file) {
        FileInputStream input;
        try {
            File dest = new File("" + file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            url = dest.toPath().toString();
            System.out.println("Image enregistrée avec succés");
            System.out.println(url);
            input = new FileInputStream(url);
            Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            image.setImage(img_produit);
        } catch (IOException ex) {
            System.err.println("Erreur d'enregistrement d'image");
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choisir une image");
        fileChooser.setInitialDirectory(
                new File("C:\\wamp\\www\\MySoulmates\\web\\images")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
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

    
    
    
   
    
    
    
    
    
    
    
}

