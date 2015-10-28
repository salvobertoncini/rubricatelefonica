package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;
import user.Utente;
/**
 * 
 * Classe che contiene le funzioni necessarie ad aggiungere un gruppo in rubrica.
 * Sono presenti tutti i vari elementi che si trovano nell'interfaccia grafica,
 * le funzioni back necessaria a tornare alla pagina precedente e
 * addGroupOnDb che si occupa di mandare una richiesta al server contenente i campi 
 * prelevati dall'interfaccia grafica. 
 *
 */
public class ControllerAggiungiGruppo {
	
	@FXML
	TextField name;
	
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
	
	public void addGroupOnDb()
	{
		String nome;
		nome = name.getText();
		
		System.out.println("REGISTRO NEL DATABASE");
		Richieste addGroup = new Richieste(nome,"aggiungiGruppo",ControllerLogin.userlog.getId());
		
		try {
			RubricaTelefonica.versoServer.writeObject(addGroup);
			boolean ok = (boolean)RubricaTelefonica.dalServer.readObject();
			if(ok)
			{
				error.setVisible(true);
				error.setText("gruppo già esistente");
			}
			else
			{
				error.setVisible(true);
				error.setText("gruppo inserito con successo");
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
