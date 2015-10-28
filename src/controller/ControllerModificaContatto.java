package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;

public class ControllerModificaContatto implements Initializable
{
	/**
	 * 
	 * Classe che contiene le funzioni necessarie ad modificare un contatto in rubrica.
	 * Sono presenti tutti i vari elementi che si trovano nell'interfaccia grafica,
	 * le funzioni back necessaria a tornare alla pagina precedente e
	 * modPeopleOnDb che si occupa di mandare una richiesta al server contenente i campi 
	 * prelevati dall'interfaccia grafica. 
	 *
	 */
	@FXML
	TextField name, surname, tel, tel2, email, email2;
	
	@FXML
	TextArea note;
	
	@FXML
	Text error;
	@FXML
	ComboBox comboboxgruppi;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setText(ControllerMenu.utenteSelezionato.getNome());
		surname.setText(ControllerMenu.utenteSelezionato.getCognome());
		tel.setText(ControllerMenu.utenteSelezionato.getTelefono());
		tel2.setText(ControllerMenu.utenteSelezionato.getTelefono2());
		email.setText(ControllerMenu.utenteSelezionato.getEmail());
		email2.setText(ControllerMenu.utenteSelezionato.getEmail2());
		note.setText(ControllerMenu.utenteSelezionato.getNote());
		
		Richieste riempicombobox = new Richieste("riempicombobox", ControllerLogin.userlog.getId());
		try {
			RubricaTelefonica.versoServer.writeObject(riempicombobox);
			ArrayList<String> ok = (ArrayList<String>)RubricaTelefonica.dalServer.readObject();
			for (int k=0;k<ok.size();k++)
				comboboxgruppi.getItems().addAll(ok.get(k));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void back(){
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
	
	public void modPeopleOnDb()
	{
		String nome, cognome, mail, mail2, telefono, telefono2, area;
		nome = name.getText();
		cognome = surname.getText();
		telefono = tel.getText();
		telefono2 = tel2.getText();
		mail = email.getText();
		mail2 = email2.getText();
		area = note.getText();
		String temp=(String) comboboxgruppi.getValue();
		
		Richieste modContact = new Richieste(nome,cognome,telefono,telefono2,mail,mail2,area,"modContact",ControllerMenu.utenteSelezionato.getId(), temp);
		
		try {
			RubricaTelefonica.versoServer.writeObject(modContact);
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

	
	public void delPeopleOnDb(){
		String nome, cognome, mail, mail2, telefono, telefono2, area;
		nome = name.getText();
		cognome = surname.getText();
		telefono = tel.getText();
		telefono2 = tel2.getText();
		mail = email.getText();
		mail2 = email2.getText();
		area = note.getText();
		Richieste deleteContact = new Richieste(nome,cognome,telefono,telefono2,mail,mail2,area,"deleteContact",ControllerMenu.utenteSelezionato.getId(), null);
		try {
			RubricaTelefonica.versoServer.writeObject(deleteContact);
			boolean ok = (boolean)RubricaTelefonica.dalServer.readObject();
			if(!ok)
			{
				error.setVisible(true);
				error.setText("errore nell'eliminazione");
			}
			else
			{
				error.setVisible(true);
				error.setText("eliminazione effettuata con successo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
