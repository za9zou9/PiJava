/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;

import Entities.Evenement;
import Services.EvenementServices;
import com.sun.prism.impl.Disposer.Record;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;







/**
 *
 * @author skan
 */
public class DeleteButton extends TableCell<Record, Boolean> {
    final Button supp = new Button("   -");

    public DeleteButton(ObservableList<Evenement> evtList) {

        supp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                EvenementServices ge = new EvenementServices();
                try {
                    
                 Evenement currentEvt = (Evenement) DeleteButton.this.getTableView().getItems().get(DeleteButton.this.getIndex());
                    Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
                a1.setTitle("Suppression");
                a1.setHeaderText(null);
                a1.setContentText("Etes vous vraiment sûr de vouloir supprimer cet événement");
  Optional<ButtonType> button = a1.showAndWait();
  if (button.get() == ButtonType.OK) {
                       evtList.remove(currentEvt);
                   ge.DeleteEvenement(currentEvt.getIdEvenement()); 
                    }
                  
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
            setGraphic(supp);
        }
    }









}
