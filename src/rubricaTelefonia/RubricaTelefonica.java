package rubricaTelefonia;

//Importo i package necessari
import java.net.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class RubricaTelefonica extends Application
{
	
	/**
	 * Classe principale del programma. Contiene il void main, che si occupa di inizializzare
	 * una socket con indirizzo locale 127.0.0.1 e porta 6001, e di invocare il metodo start
	 * che fa partire la prima scena, quella del login. Inizializza inoltre due variabili
	 * che si occupano dello streaming DA e VERSO il server.
	 * 
	 */
	
	public static Socket clientSocket;
	public static ObjectOutputStream versoServer;
	public static ObjectInputStream dalServer;
	
	static Scene scene;
	public static Stage Stage;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		try 
        {
        	Parent root = FXMLLoader.load(getClass().getResource("../graphics/login.fxml"));
        	scene = new Scene(root);
        	Stage = primaryStage;
        	Stage.setScene(scene);
        	Stage.show();
        } 
        catch (Exception e) 
        {
        	System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    } 
    
    public static void main (String[] args) throws Exception {
    	
    	try
    	  {
    	   clientSocket = new Socket ("127.0.0.1",4040);
    	   versoServer = new ObjectOutputStream(clientSocket.getOutputStream());
    	   dalServer = new ObjectInputStream(clientSocket.getInputStream());
    	  }
    	  catch(Exception e)
    	  {

    	        }
		Application.launch(RubricaTelefonica.class, (java.lang.String[])null);	

    }

}
