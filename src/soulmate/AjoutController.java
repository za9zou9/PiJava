/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Entities.Ligne;
import Entities.Pack;
import Entities.Produit;
import Services.CrudPack;
import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjoutController implements Initializable {

    private String url ; 
    @FXML
    private TextField prix;
    @FXML
    private Button ajoutpack;
    @FXML
    private Button choixI;
    @FXML
    private ImageView imageV;
    @FXML
    private AnchorPane ancho;
    
       CrudPack s=new CrudPack();
    
     List<Produit> ist = (ArrayList<Produit>)s.Musique();
      List<Produit> salle = (ArrayList<Produit>)s.Salle();
      List<Produit> fleurs= (ArrayList<Produit>)s.Fleurs();
     
        ObservableList<Produit> filteredList = FXCollections.observableArrayList(ist);
   ObservableList<Produit> filteredList1 = FXCollections.observableArrayList(salle);
ObservableList<Produit> filteredList2 = FXCollections.observableArrayList(fleurs);
    @FXML
    private ComboBox<Produit> Mu;
    @FXML
    private ComboBox<Produit> Fl;
    @FXML
    private ComboBox<Produit> S;
    @FXML
    private FontAwesomeIconView home;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       /*  for(int i=0;i<ist.size();i++){
           
             
             Produit p =ist.get(i);
             
             
            
         } */
          Mu.setItems(filteredList); 
            Fl.setItems(filteredList2); 
         S.setItems(filteredList1); 
         
         
        
        // TODO
    }    

    @FXML
    private void AjoutP(ActionEvent event) throws SQLException {
        
        
     CrudPack p = new CrudPack();       

        
        Pack pa  = new Pack();
        
        Ligne l = new Ligne();
               
    
   
    
        pa.setPrixPack(Integer.parseInt(prix.getText()));
        pa.setImage(url);
          
        p.addPack(pa);
        
        l.setIdPack(p.Max());
        
        l.setIdProduit(S.getValue().getIdProduit());
        System.out.println(S.getValue());
        p.addLigne(l);
        
        Ligne l2 = new Ligne();
        
         System.out.println(Mu.getValue().getIdProduit());
        l2.setIdPack(p.Max());
        l2.setIdProduit(Mu.getValue().getIdProduit());
        
        p.addLigne(l2);
        
        
          Ligne l3 = new Ligne();
        
        
        l3.setIdPack(p.Max());
        l3.setIdProduit(Fl.getValue().getIdProduit());
        
        p.addLigne(l3);
        
    }

    @FXML
    private void choisir(ActionEvent event) {
        
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(ancho.getScene().getWindow());
        if (file != null) {
            openFile(file);
        
    }}
 
    
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
            imageV.setImage(img_produit);
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
    private void music(ActionEvent event) {
        
        
        
        
        
    }

    @FXML
    private void homer(MouseEvent event) throws IOException {
         Stage stage = (Stage) home.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/backoffice.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }
    
    
    
    
    
    
}
