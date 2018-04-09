/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GstEvenements;

import java.io.IOException;
import java.sql.Date;
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
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLGerer.fxml"));
       
        Scene scene2 = new Scene(root2);
        stage.setScene(scene2);
        stage.show();
    }
    
    public void startPart(Stage stage,Parent root) throws Exception {
       
       
       
        
    }
    public void startUpdate(Stage stage,int id,String lieu,String description,Date date,String image) throws Exception {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLModifier.fxml"));
          Parent root = loader.load();
            
        Scene scene = new Scene(root);
        FXMLModifierController f= loader.getController();
        f.setId(id);
        f.setLieu(lieu);
        f.setDescription(description);
        f.setUrl(image);
        f.setImgview(image);
        
       
        
        stage.setScene(scene);
        stage.show();
    }
    public void startCreate(Stage stage) throws Exception {
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAjout.fxml"));
       
        Scene scene = new Scene(root);
        
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
