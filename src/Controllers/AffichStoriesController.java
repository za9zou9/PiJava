/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commentaire;
import Entities.Produit;
import Entities.Stories;
import Entities.User;
import Services.CommentaireServices;
import Services.ProduitServices;
import Services.StoriesServices;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AffichStoriesController implements Initializable {
    
    private int LeId;
    @FXML
    private TextField coms;

    public int getLeId() {
        return LeId;
    }

    public void setLeId(int LeId) {
        this.LeId = LeId;
    }
    
    
 private ObservableList<Stories> data=FXCollections.observableArrayList();
@FXML
private AnchorPane ancho;
    @FXML
    private Label titre;
    @FXML
    public TableColumn<Commentaire, String> desc;
    public TableColumn<Commentaire, Integer> personne;
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
    @FXML
    public TableView<Commentaire> matable;
    
    String url;
    @FXML
    private Label description;
    @FXML
    private ImageView img;
    @FXML
    private Label comment;
    @FXML
    private TextField commentaire;

    public Label getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   
    public void setDescription(String description) {
        this.description.setText(description);
    }
    
    
    
    
    public void setImgview(String url) throws IOException {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.img.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLTestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(FXMLTestController.class.getName()).log(Level.SEVERE, null, io);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        matable.setEditable(true);
    }    

    @FXML
    private void Menu(MouseEvent event) {
    }

    @FXML
    private void planif(MouseEvent event) {
    }

    @FXML
    private void AjouterCom(MouseEvent event) throws SQLException, IOException {
        StoriesServices ss=new StoriesServices();
    List<Stories> datavalue = ss.selectStoriesAll();
 data = FXCollections.observableArrayList(datavalue);
         CommentaireServices fs = new CommentaireServices();
       Commentaire c = new Commentaire(commentaire.getText());
       for(int i=0;i<data.size();i++){
           {
               if(data.get(i).getIdStorie()==LeId){
                   User ussr=User.getInstance();
                   c.setId(ussr.getId());
        c.setIdStorie(data.get(i).getIdStorie());
        fs.insertCommentaire(c);
       System.out.println(""+c.getIdStorie());}}
       }
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        
            
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/Acceuil.fxml"));
        Parent root = loader.load();
        AcceuilController c = loader.getController();
        acceuil.getScene().setRoot(root);
    }

   @FXML
    private void onSelectLigne(javafx.scene.input.MouseEvent event) {
         Commentaire p = matable.getSelectionModel().getSelectedItem();
      
       if (p != null){
      coms.setText(p.getDescription()); 
            }
    }


   private Commentaire z ;
    @FXML
    private void modif(MouseEvent event) {
           z = matable.getSelectionModel().getSelectedItem();
          z.setDescription(coms.getText()); 
    CommentaireServices sp = new CommentaireServices();
        
        sp.UpdateCommentaire(z,z.getIdCommentaire());
    }

    @FXML
    private void supprim(MouseEvent event) throws SQLException {
            z = matable.getSelectionModel().getSelectedItem();
         z.setDescription(coms.getText()); 
      CommentaireServices sp = new CommentaireServices();
        sp.DeleteCommentaire(z.getIdCommentaire());
        matable.getItems().removeAll(matable.getSelectionModel().getSelectedItem());
        matable.getSelectionModel().select(null);
    }
    }
    










