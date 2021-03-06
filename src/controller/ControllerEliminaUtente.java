package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;
/**
 * 
 * Classe che contiene le funzioni necessarie ad eliminare un utente.
 * Sono presenti tutti i vari elementi che si trovano nell'interfaccia grafica,
 * le funzioni back necessaria a tornare alla pagina precedente e
 * delUser che si occupa di mandare una richiesta al server contenente i campi 
 * prelevati dall'interfaccia grafica. 
 *
 */
public class ControllerEliminaUtente 
{
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
	
	public void delUser(){
		Richieste deleteUser = new Richieste(ControllerLogin.userlog.getId(), "deleteUser");
		try {
			RubricaTelefonica.versoServer.writeObject(deleteUser);
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
