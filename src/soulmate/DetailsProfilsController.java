/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Controllers.AcceuilController;
import Entities.Pack;
import Entities.User;
import Services.CrudPack;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DetailsProfilsController implements Initializable  {

    @FXML
    private ImageView imagePack;
    @FXML
    private Label username;
    @FXML
    private Label age;
    @FXML
    private Label sexe;
    @FXML
    private Label etat;
    @FXML
    private Button AjouterMesAmis;
    @FXML
    private Button chat;
    
    private ArrayList<Thread> threads;
    @FXML
    private ImageView acceuil;

    public Label getUsername() {
      
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Label getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age.setText(age) ;
    }

    public Label getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe.setText(sexe);
    }

    public Label getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat.setText(etat);
    }

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event)  throws SQLException {
        
        
     CrudPack p = new CrudPack();       

     User u = p.RechercheId(this.getUsername().getText()); 
       
        
    
         int idAmis = u.getId();
         String usernames = u.getUsername();
         User ussr = User.getInstance();
         
         int point = ussr.getPoint() + 5 ;
         
         ussr.setPoint(point);
         p.AjouterAmis(idAmis,ussr.getId(),usernames);
        p.ajouterPoint(point, ussr.getId());
        
        }

    @FXML
      public void chat() throws Exception {
        
    ActionEvent event = new ActionEvent(); 
    
    
    CrudPack p = new CrudPack();       

     User u = p.RechercheId(this.getUsername().getText()); 
       
    System.out.println(u.getSexe());
    
    User ussr = User.getInstance();
        
    
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
    private void acceuilapp(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/soulmate/profils.fxml"));
        Parent root = loader.load();
        ProfilsController c = loader.getController();
        acceuil.getScene().setRoot(root);
    }
  
        
        
    }
    

