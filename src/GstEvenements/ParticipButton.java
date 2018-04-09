/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;

import Entities.Evenement;
import Entities.Partcipation;
import Entities.User;
import Services.EvenementServices;
import Services.ParticipationService;
import Services.UserServices;
import com.sun.prism.impl.Disposer.Record;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;







/**
 *
 * @author skan
 */
public class ParticipButton extends TableCell<Record, Boolean> {
    final Button part = new Button("Participants");
private int interm;
    public ParticipButton(ObservableList<Evenement> evtList) {

        part.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
            public void handle(ActionEvent t) {
                EvenementServices ge = new EvenementServices();
                ParticipationService se=new ParticipationService();
UserServices us=new UserServices();
                try {
                   
                    Evenement currentEvt = (Evenement) ParticipButton.this.getTableView().getItems().get(ParticipButton.this.getIndex());
    
                    
                    List<Partcipation> liste=(ArrayList<Partcipation>) se.selectParticipants(currentEvt.getIdEvenement()); 
 List<Integer> lstUsersIDS=new ArrayList<Integer>(); 
        for(int i=0;i<liste.size();i++){lstUsersIDS.add(liste.get(i).getId());} 
        List<User> lstUsers=us.selectUserById(lstUsersIDS);
        ObservableList<User> listU=FXCollections.observableArrayList(lstUsers);
                    
                    
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLParticipants.fxml"));
            
                Parent root;
                root = loader.load();
                FXMLParticipantsController nav = loader.getController();
                
                nav.setId(currentEvt.getIdEvenement());
               
               nav.matable.setItems(listU);
            nav.pseudo.setCellValueFactory(
                new PropertyValueFactory<User, String>("username"));    
           
            nav.nom.setCellValueFactory(
                new PropertyValueFactory<User, String>("nom"));
            
            nav.prenom.setCellValueFactory(
                new PropertyValueFactory<User, String>("prenom"));
            
            nav.email.setCellValueFactory(
                new PropertyValueFactory<User, String>("email")); 
            
             nav.date.setCellValueFactory(
                new PropertyValueFactory<User, Date>("dateDeNaissance")); 
             
             nav.etat.setCellValueFactory(
                new PropertyValueFactory<User, String>("etat")); 
             
             nav.num.setCellValueFactory(
                new PropertyValueFactory<User, String>("tel")); 
            
            Stage stage=new Stage();
            Scene scene = new Scene(root);
      
  stage.setScene(scene);
        stage.show();
           
                    
                   
                } catch (Exception ex) {
                     Logger.getLogger(FXMLParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    
@Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(part);
        }
    }



public ObservableList<User> Organiser(){
        ParticipationService se=new ParticipationService();
UserServices us=new UserServices();

  List<Partcipation> liste=(ArrayList<Partcipation>) se.selectParticipants(interm); 
 List<Integer> lstUsersIDS=new ArrayList<Integer>(); 
        for(int i=0;i<liste.size();i++){lstUsersIDS.add(liste.get(i).getId());} 
      lstUsersIDS.forEach(System.out::println);
        List<User> lstUsers=us.selectUserById(lstUsersIDS);
        ObservableList<User> listU=FXCollections.observableArrayList(lstUsers);
       
        return listU;
    }





}
