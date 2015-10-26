package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.Richieste;
import request.Risposte;
import rubricaTelefonia.RubricaTelefonica;
import user.Utente;

public class ControllerLogin {
	//Pulsante "LOGIN"
	
	public static Utente userlog;
	
	@FXML
	TextField username;
	
	@FXML
	PasswordField password;
	
	@FXML
	Button loginbutton;
	
	@FXML
	Text loginerror, registrationtext;
	
		@FXML
		public void login(ActionEvent event)
		{
			System.out.println("ESEGUO LOGIN");
			loginerror.setVisible(false);
			String user = username.getText();
			String pass = password.getText();
			
			Richieste login = new Richieste(user, pass,"login");

			try
			{
				RubricaTelefonica.versoServer.writeObject(login);
				userlog = (Utente)RubricaTelefonica.dalServer.readObject();
				if(userlog.getLogin())
				{
					System.out.println("Login effettuato con successo!! ID: "+userlog.getId());
					loginerror.setVisible(false);
					
					try {
						Parent root = FXMLLoader.load(getClass().getResource("../graphics/menu.fxml"));
						Scene scene1 = new Scene(root);
			        	RubricaTelefonica.Stage.setScene(scene1);
			        	RubricaTelefonica.Stage.show();
					} 
					
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else{
					loginerror.setVisible(true);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void registration(){
			System.out.println("VAI A REGISTRAZIONE");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../graphics/registration.fxml"));
				Scene scene1 = new Scene(root);
	        	RubricaTelefonica.Stage.setScene(scene1);
	        	RubricaTelefonica.Stage.show();
			} 
			
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
}
