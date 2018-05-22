/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import Services.CrudPack;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import soulmate.Client;
import static soulmate.ServerApplication.threads;

/**
 * FXML Controller class
 *
 * @author Imene
 */
public class AcceuilController implements Initializable {

    @FXML
    private VBox parent;
    @FXML
    private Label acceuil;
    @FXML
    private Label veterinaires;
    @FXML
    private Label evenements;
    @FXML
    private Label vete;
    @FXML
    private ImageView pan;
    @FXML
    private Label pani;
    @FXML
    private ImageView ser;
    @FXML
    private ImageView eve;
    @FXML
    private Label even;
    @FXML
    private Label evenements1;
    @FXML
    private Label mariage;
    @FXML
    private Label users;
    @FXML
    private ImageView amis;
    @FXML
    private Label mes;
    @FXML
    private ImageView tchat;
    @FXML
    private FontAwesomeIconView decon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Menu(MouseEvent event) throws IOException {
         Stage stage = (Stage) acceuil.getScene().getWindow();
            stage.close();
        VBox parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/Acceuil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void planifier(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/PlanifierMariage.fxml"));
        Parent root = loader.load();
        PlanifierMariageController c = loader.getController();
        mariage.getScene().setRoot(root);
    }

    @FXML
    private void planif(MouseEvent event) throws IOException {
        
        Stage stage = (Stage) evenements1.getScene().getWindow();
            stage.close();
       AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/PlanifierMariage.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
        
    }

    @FXML
    private void affichStories(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/FXMLTest.fxml"));
        Parent root = loader.load();
        FXMLTestController c = loader.getController();
        mariage.getScene().setRoot(root);
    }

    @FXML
    private void affichSuccessStories(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxInterfaces/FXMLTest.fxml"));
        Parent root = loader.load();
         FXMLTestController c = loader.getController();
        mariage.getScene().setRoot(root);
    }

    @FXML
    private void chanter(MouseEvent event) throws IOException {
        Stage stage = (Stage) evenements.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/EventsUsers/FXMLInterfaceAcceuil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void danser(MouseEvent event) throws IOException {
        Stage stage = (Stage) eve.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/EventsUsers/FXMLInterfaceAcceuil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void usersAll(MouseEvent event) throws IOException {
        
         Stage stage = (Stage) users.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/soulmate/profils.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
        
    }

    @FXML
    private void MesAmis(MouseEvent event) throws IOException {
        
        Stage stage = (Stage) amis.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/soulmate/MonProfil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void mes(MouseEvent event) throws IOException {
        
        Stage stage = (Stage) mes.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/soulmate/MonProfil.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void tchatt(MouseEvent event) throws IOException, SQLException {
    
    CrudPack p = new CrudPack();       
User ussr = User.getInstance();
     User u = p.RechercheId(ussr.getUsername()); 
       
    System.out.println(u.getSexe());
    
    
        
    
         int id = ussr.getId();;
         String usernames = ussr.getUsername()+" " + "( "+ u.getSexe() + " ) " ;
      
       Stage primaryStage =new Stage() ; 
		threads = new ArrayList<Thread>();
		primaryStage.setTitle(" MySoulmate ");
                Client client;
                User M = User.getInstance();                
                
                client = new Client("localhost", 9000,usernames);
		primaryStage.setScene(makeChatUI(client));
		primaryStage.show();
 
    }
      
      
      public Scene makeChatUI(Client client) {
            Thread clientThread = new Thread(client);
            System.out.println("2");
            clientThread.setDaemon(true);
            clientThread.start();
            threads.add(clientThread);
            GridPane rootPane = new GridPane();
            rootPane.setPadding(new Insets(20));
            rootPane.setAlignment(Pos.CENTER);
            rootPane.setHgap(10);
            rootPane.setVgap(10);
            ListView<String> chatListView = new ListView<String>();
            chatListView.setItems(client.chatLog);
            TextField chatTextField = new TextField();
            chatTextField.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // TODO Auto-generated method stub
                    client.writeToServer(chatTextField.getText());
                    chatTextField.clear();
                }
            });
            rootPane.add(chatListView, 0, 0);
            rootPane.add(chatTextField, 0, 1);
            return new Scene(rootPane, 400, 400);

	}

    @FXML
    private void planiff(MouseEvent event) throws IOException {
      
        Stage stage = (Stage) ser.getScene().getWindow();
            stage.close();
       AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/FxInterfaces/PlanifierMariage.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        User ussr=User.getInstance();
        ussr.sedeconnecter();
        Stage stage = (Stage) decon.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/GstUtilisateurs/FXMLogin.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }

    
}
