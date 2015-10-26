package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.Richieste;
import request.Risposte;
import rubricaTelefonia.RubricaTelefonica;

public class ControllerRegistrazione {
	
	@FXML
	TextField name, surname, tel, email, username;
	
	@FXML
	PasswordField password;
	
	@FXML
	Text error;
	
	public void back()
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/login.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void regOnDb(){
		String nome, cognome, telefono, mail, user, pass;
		nome = name.getText();
		cognome = surname.getText();
		telefono = tel.getText();
		mail = email.getText();
		user = username.getText();
		pass = password.getText();
		
		System.out.println("REGISTRO NEL DATABASE");
		Richieste registration = new Richieste(nome,cognome,user,pass,telefono,mail,"registrazione",0);
		
		try {
			RubricaTelefonica.versoServer.writeObject(registration);
			boolean ok = (boolean)RubricaTelefonica.dalServer.readObject();
			if(ok)
			{
				error.setVisible(true);
				error.setText("username o email già esistenti");
			}
			else
			{
				error.setVisible(true);
				error.setText("user inserito con successo");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
