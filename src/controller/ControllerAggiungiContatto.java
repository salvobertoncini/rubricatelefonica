package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;

public class ControllerAggiungiContatto 
{
	@FXML
	TextField name, surname, tel, tel2, email, email2;
	
	@FXML
	TextArea note;
	
	@FXML
	Text error;
	
	public void back(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/addmenu.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void addPeopleOnDb()
	{
		String nome, cognome, mail, mail2, telefono, telefono2, area;
		nome = name.getText();
		cognome = surname.getText();
		telefono = tel.getText();
		telefono2 = tel2.getText();
		mail = email.getText();
		mail2 = email2.getText();
		area = note.getText();
		
		System.out.println("REGISTRO NEL DATABASE");
		int idLoggato=ControllerLogin.userlog.getId();
		Richieste registration = new Richieste(nome,cognome,telefono,telefono2,mail,mail2,area,"aggiungiContatto",idLoggato, null);

		try {
			RubricaTelefonica.versoServer.writeObject(registration);
			boolean ok = (boolean)RubricaTelefonica.dalServer.readObject();
			if(ok)
			{
				error.setVisible(true);
				error.setText("contatto già esistente");
			}
			else
			{
				error.setVisible(true);
				error.setText("contatto inserito con successo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
