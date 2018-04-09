/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author skan
 */
public class PIDEV extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
     
        Parent root2 =FXMLLoader.load(getClass().getResource("FXMLAjoutEvent.fxml"));
       
        Scene scene2 = new Scene(root2);
        stage.setScene(scene2);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
