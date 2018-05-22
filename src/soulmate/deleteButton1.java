/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Entities.MesAmis;

import Services.CrudPack;
import com.sun.prism.impl.Disposer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author lenovo
 */
public class deleteButton1 extends TableCell<Disposer.Record, Boolean> {
    
     final Button supp = new Button("  e=fe= -");
     
      public deleteButton1(ObservableList<MesAmis> evtList) {

        supp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                CrudPack ge = new CrudPack();
                try {
                    System.out.println(evtList);
                    MesAmis amis = (MesAmis) deleteButton1.this.getTableView().getItems().get(deleteButton1.this.getIndex());
                    
                   evtList.remove(amis);
                  ge.DeleteAmis(amis.getIdMesAmis());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    
}
