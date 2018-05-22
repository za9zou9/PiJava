package soulmate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**************************************************
@FXML
    void Ajouter(ActionEvent event) throws ParseException, IOException {
Evenement e;
        EventService es;
        java.sql.Date date;
        date = java.sql.Date.valueOf(DpDate.getValue());
        String img=imageNameOld;
        if(imageNameNew!=null) { img=imageNameNew; }
        
        e= new Evenement(TxLieu.getText(), TxTitre.getText(), CbVille.getValue(), TxDescription.getText(), Float.parseFloat(TxPrix.getText()) ,Integer.parseInt(TxNbrPlace.getText()), Integer.parseInt(TxDuree.getText()), CbDifficulte.getValue(), 0,date,id,img);
        es = new EventService();
        es.edit(e);
        
         Parent p1 = FXMLLoader.load(getClass().getResource("EventDisplay.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();
    }

*****************************************************/
public class Client implements Runnable {
	/* The Socket of the Client */
	private Socket clientSocket;
	private BufferedReader serverToClientReader;
	private PrintWriter clientToServerWriter;
	private String name;
	public ObservableList<String> chatLog;

	public Client(String hostName, int portNumber, String name) throws UnknownHostException, IOException {
		
			/* Try to establish a connection to the server */
			clientSocket = new Socket(hostName, portNumber);
			/* Instantiate writers and readers to the socket */
			serverToClientReader = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			clientToServerWriter = new PrintWriter(
					clientSocket.getOutputStream(), true);
			chatLog = FXCollections.observableArrayList();
			/* Send name data to the server */
			this.name = name;
			clientToServerWriter.println(name);

		
	}

	public void writeToServer(String input) {
		clientToServerWriter.println(name + " : " + input);
	}

	public void run() {
		/* Infinite loop to update the chat log from the server */
		while (true) {
			try {

				final String inputFromServer = serverToClientReader.readLine();
				Platform.runLater(new Runnable() {
					public void run() {
						chatLog.add(inputFromServer);
					}
				});

			} catch (SocketException e) {
				Platform.runLater(new Runnable() {
					public void run() {
						chatLog.add("Error in server");
					}

				});
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
