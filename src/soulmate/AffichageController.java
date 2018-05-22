/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soulmate;

import Entities.Pack;
import Services.CrudPack;
import com.sun.prism.impl.Disposer.Record;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AffichageController implements Initializable {

    @FXML
    private TableView<Pack> table;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<Pack, Integer> prix;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TextField recherche_txt;
    @FXML
    private TableColumn supp;

    CrudPack s=new CrudPack();
    
        List<Pack> ist = (ArrayList<Pack>)s.selectPack();
        ObservableList<Pack> filteredList = FXCollections.observableArrayList(ist);
    @FXML
    private Button ajout;
    @FXML
    private AnchorPane ancho;
    @FXML
    private FontAwesomeIconView home;
        
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(ist.size());
      table.setItems(filteredList);
   prix.setCellValueFactory(
                new PropertyValueFactory<Pack, Integer>("prixPack"));
   
        
        
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
                
                return new deleteButton(filteredList);
            }
        });
        
        recherche_txt.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filtrerSList((String) oldValue, (String) newValue);
                   
     
                } catch (SQLException ex) {
                    Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        
        }
                );
        // TODO
    }  
    
    public void filtrerSList(String oldValue, String newValue) throws SQLException
    {
        CrudPack s=new CrudPack();
        ObservableList<Pack> filteredList = FXCollections.observableArrayList();
        if (recherche_txt.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(filteredList);
        }
        else {

            newValue = newValue.toUpperCase();

            for (Pack  pack : table.getItems()) {

                String filterSoutenance =""+ pack.getPrixPack();

                if (filterSoutenance.toUpperCase().contains(newValue)) {
                    filteredList.add(pack);

                }

            }
            table.setItems(filteredList);

        }
        }

    @FXML
    private void ajouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajout.fxml"));
        try {
            Parent root;
            root = loader.load();
            soulmate.AjoutController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(soulmate.AjoutController.class.getName()).log(Level.SEVERE, null, ex);
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
