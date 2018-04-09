/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventsUsers;

import Entities.Evenement;
import Entities.Partcipation;
import Entities.User;
import static EventsUsers.FXMLDetailsEventController.id;
import Services.EvenementServices;
import Services.ParticipationService;
import Services.UserServices;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.ScrollPane;
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
 * @author skan
 */
public class FXMListeDesEventsController implements Initializable {

    @FXML
    private AnchorPane ancho;
    @FXML
    private ScrollPane scrol;
    @FXML
    private VBox vb;
    @FXML
    private FontAwesomeIconView retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EvenementServices es=new EvenementServices();
        List<Evenement> listeE = es.selectEvenemntAvenir();
        
        for(int i=0;i<listeE.size();i++){
           Hyperlink titre=new Hyperlink();
           Evenement e=listeE.get(i);
           titre.setText(e.getLieu());
           titre.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("FXMLDetailsEvent.fxml"));
                Parent root;
                   try {
                       root=loader.load();
                       FXMLDetailsEventController dc=loader.getController();
                       dc.setDescription(e.getDescription());
                       dc.setDate((Date) e.getDate());
                       dc.setImgview(e.getImage());
                       dc.setLieu(e.getLieu());
                       dc.setId(e.getIdEvenement());
                        boolean trouv=false;
        Partcipation pr=new Partcipation();
        ParticipationService parts=new ParticipationService(); 
      List<Partcipation> liste=parts.selectParticipants(e.getIdEvenement());
      ObservableList<User> OL=FXCollections.observableArrayList();
     OL=Organiser(e.getIdEvenement());
     ObservableList<String> OLNames=FXCollections.observableArrayList();
     for(int k=0;k<OL.size();k++){OLNames.add("Pseudo: "+OL.get(k).getUsername()+"   Age: "+OL.get(k).getAge()+"   Sexe: "+OL.get(k).getSexe());}
     dc.listPart.setItems(OLNames);
     
        for(int i=0;i<liste.size();i++){
            
            if (liste.get(i).getId()==6) {pr=liste.get(i); trouv=true;}
        }
        if (trouv==true)    dc.participer.setDisable(true);
        else dc.annuler.setDisable(true);
                       Scene scene=ancho.getScene();
                       scene.setRoot(root);
                   } catch (IOException ex) {
                       Logger.getLogger(FXMListeDesEventsController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    
    public ObservableList<User> Organiser(int idE){
        ParticipationService se=new ParticipationService();
UserServices us=new UserServices();

  List<Partcipation> liste=(ArrayList<Partcipation>) se.selectParticipants(idE); 
 List<Integer> lstUsersIDS=new ArrayList<Integer>(); 
        for(int i=0;i<liste.size();i++){lstUsersIDS.add(liste.get(i).getId());} 
      lstUsersIDS.forEach(System.out::println);
        List<User> lstUsers=us.selectUserById(lstUsersIDS);
        ObservableList<User> listU=FXCollections.observableArrayList(lstUsers);
       
        return listU;
    }

    @FXML
    private void retourner(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInterfaceAcceuil.fxml"));
        try {
            Parent root;
            root = loader.load();
            FXMLInterfaceAcceuilController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLInterfaceAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
