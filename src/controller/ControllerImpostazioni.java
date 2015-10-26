package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;
import user.Utente;

public class ControllerImpostazioni
{
	
	@FXML
	Text loginerror;
	
	public void back()
	{
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
	
	public void modProfile()
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/modprofileuser.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void expAddressBook()
	{
		Richieste esportarubrica = new Richieste(ControllerLogin.userlog.getId(), "esportarubrica");
		try
		{
			RubricaTelefonica.versoServer.writeObject(esportarubrica);
			boolean ok = (boolean) RubricaTelefonica.dalServer.readObject();
			if(ok)
			{
				System.out.println("ESPORTAZIONE CONTATTI RUBRICA EFFETTUATA");
				loginerror.setVisible(true);
				
			}
			else
			{
				loginerror.setVisible(false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void delUser()
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/deluser.fxml"));
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
