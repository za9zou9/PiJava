/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;


import Entities.Partcipation;
import Entities.User;
import EventsUsers.FXMLDetailsEventController;
import Services.EvenementServices;
import Services.ParticipationService;
import Services.UserServices;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLModifierController implements Initializable {
    private int id;
    
    public static final String ACCOUNT_SID = "AC6aad8a0d0849dc11c01625b0574f7263";
    public static final String AUTH_TOKEN = "8d2943c240f49b604475ab8ca270378c";
    @FXML
    private FontAwesomeIconView home;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    private String url;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField lieu;
    @FXML
    private TextArea description;
    @FXML
    private Button choix;
    @FXML
    private ImageView image;
    @FXML
    private Button valider;
    @FXML
    private AnchorPane ancho;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JFXDatePicker getDate() {
        return date;
    }

    public void setDate(JFXDatePicker date) {
        this.date = date;
    }

    public JFXTextField getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu.setText(lieu);
    }

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now()) || item.isEqual(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        date.setDayCellFactory(dayCellFactory);
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLException, Exception {
      ParticipationService see=new ParticipationService();
UserServices us=new UserServices();   
    EvenementServices se=new EvenementServices();
   
   
    
   if ((date.getValue()==null)||(description.getText().equals(""))||(lieu.getText().equals(""))||(url==null))
    {Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText("champ(s) vide(s)");
 a.showAndWait();}
   else{
     Date dates=Date.valueOf(date.getValue());

    List<Partcipation> liste=(ArrayList<Partcipation>) see.selectParticipants(id);
 List<Integer> lstUsersIDS=new ArrayList<Integer>(); 
        for(int i=0;i<liste.size();i++){lstUsersIDS.add(liste.get(i).getId());} 
        List<User> lstUsers=us.selectUserById(lstUsersIDS);
 for(int j=0;j<lstUsers.size();j++){
  
 
   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message msg = Message.creator(new PhoneNumber("+216"+lstUsers.get(j).getTel()), new PhoneNumber("+19712056031"), "Nous vous informons que l'événement auquel vous participez qui a lieu a "+lieu.getText()+" a été modifié !! Consulter MySoulmate pour vérifier ").create();
   
 }
    
    se.UpdateEvenement(id, dates, lieu.getText(), description.getText(), url);
     Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Message");
                a1.setHeaderText(null);
                a1.setContentText("Evènement modifié avec succès");
 a1.showAndWait();
 
 
 
 
 NewFXMain n=new NewFXMain();
                  Stage stage=new Stage();
                  
                  n.start(stage); }
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

    @FXML
    private void choisir(ActionEvent event) {
         final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(ancho.getScene().getWindow());
        if (file != null) {
            openFile(file);
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

