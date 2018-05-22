/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstProduits;

import Entities.Commercant;
import Entities.Produit;
import EventsUsers.FXMLDetailsEventController;
import Services.ProduitServices;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLModifierController implements Initializable {
private int LeId;
private String url;

    public int getLeId() {
        return LeId;
    }

    public void setLeId(int LeId) {
        this.LeId = LeId;
    }
    @FXML
    private AnchorPane ancho;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField region;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField quantite;
    @FXML
    private JFXTextField prix;
    @FXML
    private ImageView image;
    @FXML
    private Button choix;
    @FXML
    private Button valider;
    @FXML
    private TextArea description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public JFXTextField getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public JFXTextField getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region.setText(region);
    }

    public JFXTextField getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public JFXTextField getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite.setText(""+quantite);
    }

    public JFXTextField getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix.setText(""+prix);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
    
    

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
    private void modifier(ActionEvent event) throws SQLException, IOException {
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
       try
{
     int var = Integer.parseInt(quantite.getText());
      p.setQuantite(var);
      float var2 = Float.parseFloat(prix.getText());
       
         ps.UpdateProduit(LeId,var2,type.getText(),var, nom.getText(),description.getText(),region.getText(), url);
           Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Message");
                a1.setHeaderText(null);
                a1.setContentText("Produit créé avec succès");
 a1.showAndWait();
 
}catch(NumberFormatException  e)
{Alert a2 = new Alert(Alert.AlertType.ERROR);
                a2.setTitle("Erreur");
                a2.setHeaderText(null);
                a2.setContentText("Champ doit prendre un nombre");
 a2.showAndWait();}
        
        
      } 
   
    }
    
    
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

    private void retourner(MouseEvent event) {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLConsulter.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstProduits.FXMLConsulterController interf = loader.getController();
            interf.setDescription(description.getText());
            interf.setLeId(LeId);
            interf.setNom(nom.getText());
            interf.setPrix(Integer.parseInt(prix.getText()));
            interf.setQuantite(Integer.parseInt(quantite.getText()));
            interf.setRegion(region.getText());
            interf.setType(type.getText());
            interf.setUrl(url);
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstProduits.FXMLConsulterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     public void setImgview(String url) throws IOException {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.image.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(FXMLDetailsEventController.class.getName()).log(Level.SEVERE, null, io);
        }
    }
    
}
