/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;

import Entities.Evenement;
import Services.EvenementServices;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author skan
 */
public class FXMLGererController implements Initializable {

    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, String> lieu;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn modif;
    @FXML
    private TableColumn supp;
    @FXML
    private TableColumn participants;
    
 EvenementServices se=new EvenementServices();
  List<Evenement> liste=(ArrayList<Evenement>) se.selectEvenemntAll();
 
    private ObservableList<Evenement> listE=FXCollections.observableArrayList(liste);
    @FXML
    private TableView<Evenement> matable;
    @FXML
    private JFXTextField rech;
    @FXML
    private AnchorPane ancho;
    @FXML
    private FontAwesomeIconView plus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Recherche(listE,matable,rech);
       
       
   
  
   matable.setItems(listE);
   date.setCellValueFactory(
                new PropertyValueFactory<Evenement, Date>("date"));
   lieu.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("lieu"));
   description.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("description"));
   
   
   supp.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        supp.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> param) {
                
                return new DeleteButton(listE);
            }
        });
   
         participants.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        participants.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> param) {
                
                return new ParticipButton(listE);
            }
        });
        
        modif.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        modif.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> param) {
                
                return new UpdateButton(listE);
            }
        });
   
 
        
        
        
    } 
    
    
    
    public void Recherche(ObservableList<Evenement> evtData, TableView<Evenement> tabEvt, JFXTextField txtRecherche) {
        FilteredList<Evenement> filteredData = new FilteredList<>(evtData, e -> true);
        txtRecherche.setOnKeyReleased(e -> {
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Evenement>) a -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (a.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evenement> sortedEvt = new SortedList<>(filteredData);
            sortedEvt.comparatorProperty().bind(tabEvt.comparatorProperty());
            tabEvt.setItems(sortedEvt);
        });
    }

    @FXML
    private void nouveau(MouseEvent event) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAjout.fxml"));
        try {
            Parent root;
            root = loader.load();
            FXMLAjoutController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    






}
