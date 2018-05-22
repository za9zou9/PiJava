/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;

import Entities.Evenement;
import Entities.User;
import Services.EvenementServices;
import Services.UserServices;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLAjoutController implements Initializable {
private String url;
    @FXML
    private Button valider;
    @FXML
    private JFXTextField lieu;
    @FXML
    private JFXDatePicker date;
    @FXML
    private TextArea description;
    @FXML
    private Button image;
    @FXML
    private AnchorPane ancho;
    @FXML
    private ImageView imgView;
    @FXML
    private FontAwesomeIconView retour;
    private TextField lon;
    private TextField lat;
    @FXML
    private FontAwesomeIconView home;

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
    private void Ajouter(ActionEvent event) throws ParseException, SQLException, Exception {
         Evenement e=new Evenement();
    EvenementServices se=new EvenementServices();
    UserServices su=new UserServices();
    List<User> LU=su.selectUsersAll();
    
    
     
    if ((date.getValue()==null)||(description.getText().equals(""))||(lieu.getText().equals(""))||(url==null))
    {Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText("champ(s) vide(s)");
 a.showAndWait();}
    else{
      Date dates=Date.valueOf(date.getValue());
     
    e.setDate(dates);
    e.setDescription(description.getText());
    e.setLieu(lieu.getText());
    e.setImage(url);
    
    
    se.insertEvenement(e);
    
    Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Message");
                a1.setHeaderText(null);
                a1.setContentText("Evènement créé avec succès");
 a1.showAndWait();
    
    String contenu="Nous vous informons qu un nouvel évènement est lancé, il se déroulera le "+e.getDate()+"  à "+e.getLieu()+" , Sa description est la suivante:  "+e.getDescription()+"";
    String to="";
       
    for(int i=0;i<LU.size();i++){
    if (LU.get(i).getEtat().equals("celib")) envoyerMail(LU.get(i).getEmail(),contenu);
    }
    
    
    
    
    NewFXMain n=new NewFXMain();
                  Stage stage=new Stage();
                  
                  n.start(stage);  }
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
            imgView.setImage(img_produit);
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

    
    
    
    
     public void envoyerMail(String adrMail,String contenu) throws AddressException, MessagingException{
    String host="smtp.gmail.com";
  String user="mysoulmate.freeminds@gmail.com";
  String pass="TeflouMonta";
  String from="mysoulmate.freeminds@gmail.com";
 
 String subject="Nouvel Evènement";

 
  boolean sessionDebug=false;
  
  Properties props=System.getProperties();
  
  props.put("mail.smtp.starttls", "true");
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.port", "587");
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.starttls.required", "true");
  
  java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
  Session mailSession =Session.getDefaultInstance(props,null);
  Message msg=new MimeMessage(mailSession);
  msg.setFrom(new InternetAddress(from));
  InternetAddress[] address={new InternetAddress(adrMail)};
  msg.setRecipients(Message.RecipientType.TO, address);
  msg.setSubject(subject); msg.setSentDate(new java.util.Date());
  msg.setText(contenu);
  
  Transport transport=mailSession.getTransport("smtp");
  transport.connect(host, user, pass);
  transport.sendMessage(msg, msg.getAllRecipients());
  transport.close();
  
  
    }

    @FXML
    private void retourner(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerer.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstEvenements.FXMLGererController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstEvenements.FXMLGererController.class.getName()).log(Level.SEVERE, null, ex);
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
