package soulmate;

import Entities.User;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClientApplication extends Application{
	private ArrayList<Thread> threads;
	public static void main(String[] args){
		launch();
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		for (Thread thread: threads){
			thread.interrupt();
		}
	}

	
	public void start2() throws Exception {
		// TODO Auto-generated method stub
                Stage primaryStage =new Stage() ; 
		threads = new ArrayList<Thread>();
		primaryStage.setTitle("Host&Guest ");
                Client client;
                User M = User.getInstance();                
                String nom = "mondher";
                client = new Client("localhost", 9000,"mondher");
		primaryStage.setScene(makeChatUI(client));
		primaryStage.show();
	}



	public Scene makeChatUI(Client client) {
            Thread clientThread = new Thread(client);
            System.out.println("2");
            clientThread.setDaemon(true);
            clientThread.start();
            threads.add(clientThread);
            GridPane rootPane = new GridPane();
            rootPane.setPadding(new Insets(20));
            rootPane.setAlignment(Pos.CENTER);
            rootPane.setHgap(10);
            rootPane.setVgap(10);
            ListView<String> chatListView = new ListView<String>();
            chatListView.setItems(client.chatLog);
            TextField chatTextField = new TextField();
            chatTextField.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // TODO Auto-generated method stub
                    client.writeToServer(chatTextField.getText());
                    chatTextField.clear();
                }
            });
            rootPane.add(chatListView, 0, 0);
            rootPane.add(chatTextField, 0, 1);
            return new Scene(rootPane, 400, 400);

	}

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
