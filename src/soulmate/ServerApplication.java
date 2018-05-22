package soulmate;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ServerApplication extends Application {
	public static ArrayList<Thread> threads;
	public static void main(String[] args){
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		threads = new ArrayList<Thread>();
		primaryStage.setTitle("JavaFX Chat Server");
                GridPane rootPane = new GridPane();
		rootPane.setPadding(new Insets(20));
		rootPane.setVgap(10);
		rootPane.setHgap(10);
		rootPane.setAlignment(Pos.CENTER);
		Server server = new Server(9000);
		Thread serverThread = (new Thread(server));
		serverThread.setName("Server Thread");
		serverThread.setDaemon(true);
		serverThread.start();
		threads.add(serverThread);
                primaryStage.setScene(makeServerUI(server));
		primaryStage.show();
		
	}

	public Scene makeServerUI(Server server){
		/* Make the root pane and set properties */
		GridPane rootPane = new GridPane();
		rootPane.setAlignment(Pos.CENTER);
		rootPane.setPadding(new Insets(20));
		rootPane.setHgap(10);
		rootPane.setVgap(10);
		
		/* Make the server log ListView */
		Label logLabel = new Label("Server Log");
		ListView<String> logView = new ListView<String>();
		ObservableList<String> logList = server.serverLog;
		logView.setItems(logList);
		
		/* Make the client list ListView */
		Label clientLabel = new Label("Clients Connected");
		ListView<String> clientView = new ListView<String>();
		ObservableList<String> clientList = server.clientNames;
		clientView.setItems(clientList);
		
		/* Add the view to the pane */
		rootPane.add(logLabel, 0, 0);
		rootPane.add(logView, 0, 1);
		rootPane.add(clientLabel, 0, 2);
		rootPane.add(clientView, 0, 3);
		
		/* Make scene and return it,
		 * Scene has constructor (Parent, Width, Height)
		 *  */
		return new Scene(rootPane, 400, 600);
	}
}
