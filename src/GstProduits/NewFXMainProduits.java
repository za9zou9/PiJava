/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstProduits;


import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author skan
 */
public class NewFXMainProduits extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLGerer.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
   
    public void startCreate(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLAjout.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

     public void startConsulter(Stage stage,int id,String nom,String description,String type,String region,int quantite,int prix,String url) throws IOException {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLConsulter.fxml"));
          Parent root = loader.load();
            
        Scene scene = new Scene(root);
        FXMLConsulterController f= loader.getController();
        
       f.setNom(nom);
       f.setDescription(description);
       f.setPrix(prix);
       f.setType(type);
       f.setQuantite(quantite);
       f.setRegion(region);
       f.setLeId(id);
        f.setUrl(url);
        stage.setScene(scene);
        stage.show();
    }

     
      public void startStat(Stage stage) throws IOException {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLStatistiques.fxml"));
          Parent root = loader.load();
            
        Scene scene = new Scene(root);
        FXMLStatistiquesController f= loader.getController();
        
      
        
        stage.setScene(scene);
        stage.show();
    }

    public void startModifier(Stage stage,int id,String nom,String description,String type,String region,int quantite,float prix) throws IOException {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLModifier.fxml"));
          Parent root = loader.load();
            
        Scene scene = new Scene(root);
       FXMLModifierController f= loader.getController();
        
      f.setNom(nom);
       f.setDescription(description);
       f.setPrix(prix);
       f.setType(type);
       f.setQuantite(quantite);
       f.setRegion(region);
       f.setLeId(id);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
