/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstProduits;

import Entities.Commercant;
import Entities.Evenement;
import Entities.Partcipation;
import Entities.Produit;
import Entities.User;
import static EventsUsers.FXMLDetailsEventController.id;
import GstEvenements.DeleteButton;
import Services.ParticipationService;
import Services.ProduitServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
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
    private AnchorPane ancho;
    @FXML
    private TableView<Produit> matable;
    @FXML
    private TableColumn<Produit, String> nom;

    ProduitServices se=new ProduitServices();
  
    @FXML
    private TableColumn<Produit, String> type;
    @FXML
    private TableColumn voir;
    @FXML
    private JFXTextField rech;
    @FXML
    private TableView<Produit> matable2;
    @FXML
    private TableColumn<Produit, String> nom2;
    @FXML
    private TableColumn<Produit, String> type2;
    
    
    
    
    
    @FXML
    private JFXTextField rech2;
    @FXML
    private FontAwesomeIconView ajout;
    @FXML
    private FontAwesomeIconView decon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        Commercant commm=Commercant.getInstance();
        
        List<Produit> liste2=(ArrayList<Produit>) se.selectProduitsEnAttente(commm.getIdCommercant());
 
     ObservableList<Produit> listE2=FXCollections.observableArrayList(liste2);
          
        List<Produit> liste=(ArrayList<Produit>) se.selectProduitsConfirmes(commm.getIdCommercant());
 
     ObservableList<Produit> listE=FXCollections.observableArrayList(liste);
            Recherche(listE,matable,rech);
        Recherche2(listE,matable,rech2);
        
        matable.setItems(listE);
         matable2.setItems(listE2);
        
        
        
        
   nom.setCellValueFactory(
                new PropertyValueFactory<Produit, String>("nom"));
   
    type.setCellValueFactory(
                new PropertyValueFactory<Produit, String>("type"));
    
    
   nom2.setCellValueFactory(
                new PropertyValueFactory<Produit, String>("nom"));
   
    type2.setCellValueFactory(
                new PropertyValueFactory<Produit, String>("type"));
    
    
    voir.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        voir.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> param) {
                
                return new ReadButton(listE);
            }
        });
    
    
    
    
   
    
    
    }    
    
    
    
     public void Recherche(ObservableList<Produit> evtData, TableView<Produit> tabEvt, JFXTextField txtRecherche) {
        FilteredList<Produit> filteredData = new FilteredList<>(evtData, e -> true);
        txtRecherche.setOnKeyReleased(e -> {
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Produit>) a -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (a.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedEvt = new SortedList<>(filteredData);
            sortedEvt.comparatorProperty().bind(tabEvt.comparatorProperty());
            tabEvt.setItems(sortedEvt);
        });
    }
     
     
     
      public void Recherche2(ObservableList<Produit> evtData, TableView<Produit> tabEvt, JFXTextField txtRecherche) {
        FilteredList<Produit> filteredData = new FilteredList<>(evtData, e -> true);
        txtRecherche.setOnKeyReleased(e -> {
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Produit>) a -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (a.getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedEvt = new SortedList<>(filteredData);
            sortedEvt.comparatorProperty().bind(tabEvt.comparatorProperty());
            tabEvt.setItems(sortedEvt);
        });
    }

    @FXML
    private void redirection(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLStatistiques.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstProduits.FXMLStatistiquesController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstProduits.FXMLStatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }

    @FXML
    private void nouveau(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAjout.fxml"));
        try {
            Parent root;
            root = loader.load();
            GstProduits.FXMLAjoutController interf = loader.getController();
            Scene scene =ancho.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GstProduits.FXMLAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
     Commercant commm=Commercant.getInstance();
        commm.sedeconnecter();
        Stage stage = (Stage) decon.getScene().getWindow();
            stage.close();
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("/GstUtilisateurs/FXMLogin.fxml"));
                    
              Scene scene = new Scene(parentContent); 
              stage.setScene(scene);
              stage.sizeToScene();
              stage.show();
    }
    

    
    
    
    
    
}
