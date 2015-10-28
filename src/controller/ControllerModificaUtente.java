package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;

public class ControllerModificaUtente  implements Initializable 
{
	/**
	 * 
	 * Classe che contiene le funzioni necessarie a modificare l'account dell'utente loggato.
	 * Sono presenti tutti i vari elementi che si trovano nell'interfaccia grafica,
	 * le funzioni back necessaria a tornare alla pagina precedente e
	 * modOnDb che si occupa di mandare una richiesta al server contenente i campi 
	 * prelevati dall'interfaccia grafica. 
	 *
	 */
	
	@FXML
	TextField name, surname, tel, email, username;
	
	@FXML
	PasswordField password;
	
	@FXML
	Text error;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setText(ControllerLogin.userlog.getNome());
		surname.setText(ControllerLogin.userlog.getCognome());
		username.setText(ControllerLogin.userlog.getUsername());
		password.setText(ControllerLogin.userlog.getPassword());
		tel.setText(ControllerLogin.userlog.getTelefono());
		email.setText(ControllerLogin.userlog.getEmail());
		
		
	}
	
	public void back()
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/settingsmenu.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public void modOnDb(){
		String nome, cognome, telefono, mail, user, pass;
		nome = name.getText();
		cognome = surname.getText();
		telefono = tel.getText();
		mail = email.getText();
		user = username.getText();
		pass = password.getText();
		
		Richieste registration = new Richieste(nome,cognome,user,pass,telefono,mail,"modUser",ControllerLogin.userlog.getId());
		
		try {
			RubricaTelefonica.versoServer.writeObject(registration);
			boolean ok = (boolean)RubricaTelefonica.dalServer.readObject();
			if(!ok)
			{
				error.setVisible(true);
				error.setText("errore nella modifica");
			}
			else
			{
				error.setVisible(true);
				error.setText("modifica effettuata con successo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
