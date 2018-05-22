/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Entities.Pack;
import Services.CrudPack;
import com.sun.prism.impl.Disposer.Record;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author lenovo
 */
public class deleteButton extends TableCell<Record, Boolean> {
    final Button supp = new Button("   -");

    public deleteButton(ObservableList<Pack> evtList) {

        supp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                CrudPack ge = new CrudPack();
                try {
                    System.out.println(evtList);
                    Pack currentEvt = (Pack) deleteButton.this.getTableView().getItems().get(deleteButton.this.getIndex());
                    
                   evtList.remove(currentEvt);
                  ge.DeletePack(currentEvt.getIdPack());
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
