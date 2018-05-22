/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.Commande;
import Entities.Commentaire;
import Entities.Stories;
import Services.CommentaireServices;
import Services.StoriesServices;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * @author Imene
 */
public class FXMLTestController implements Initializable {

    @FXML
    private AnchorPane ancho;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox vb;
    @FXML
    private Label acceuil;
    @FXML
    private Label services;
    @FXML
    private Label veterinaires;
    @FXML
    private Label evenements;
    @FXML
    private Label evenements1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        CommentaireServices coms=new CommentaireServices();
        
        StoriesServices es=new StoriesServices();
        List<Stories> listeE = es.selectStoriesAll();
       
        for(int i=0;i<listeE.size();i++){
           Hyperlink titre=new Hyperlink();
           Stories e=listeE.get(i);
           titre.setText(e.getTitre());
           titre.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                 List<Commentaire> listeComs = new ArrayList<Commentaire>();
                   FXMLLoader loader= new FXMLLoader(getClass().getResource("/FxInterfaces/affichStories.fxml"));
                Parent root;
                   try {
                       root=loader.load();
                       AffichStoriesController dc=loader.getController();
                    
                       listeComs=coms.selectCommentairesAll(e.getIdStorie());
                        ObservableList<Commentaire> LAliste=FXCollections.observableArrayList(listeComs);
                       
                        
                       dc.setDescription(e.getDescription());
                       dc.setTitre(e.getTitre());
                       dc.setImgview(e.getImage());
                       dc.setLeId(e.getIdStorie());
                      dc.matable.setItems(LAliste);
          dc.personne.setCellValueFactory(new PropertyValueFactory<>("MONDHEEER"));
            dc.desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
                       Scene scene=ancho.getScene();
                       scene.setRoot(root);
                   } catch (IOException ex) {
                       Logger.getLogger(FXMLTestController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Menu(MouseEvent event) {
    }

    @FXML
    private void planif(MouseEvent event) {
    }







}