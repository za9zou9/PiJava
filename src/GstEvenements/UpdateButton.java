/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;

import Entities.Evenement;
import Services.EvenementServices;
import com.sun.prism.impl.Disposer;
import java.sql.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

/**
 *
 * @author skan
 */
public class UpdateButton extends TableCell<Disposer.Record, Boolean>  {
    
    final Button Modifier = new Button("Modifier");


public UpdateButton(ObservableList<Evenement> evtList) {

        Modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                EvenementServices ge = new EvenementServices();
                try {
                   
                    Evenement currentEvt = (Evenement) UpdateButton.this.getTableView().getItems().get(UpdateButton.this.getIndex());
                    FXMLModifierController fxm=new FXMLModifierController();
                    
                    
                    
                    
                    
                    
                    
                    NewFXMain n=new NewFXMain();
                    
                  Stage stage=new Stage();
                  
                  n.startUpdate(stage,currentEvt.getIdEvenement(),currentEvt.getLieu(),currentEvt.getDescription(), (Date) currentEvt.getDate(),currentEvt.getImage());
                
                   
                } catch (Exception e) {
                    System.out.println(e.getMessage());
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
            setGraphic(Modifier);
        }
    }









}


    

