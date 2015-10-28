package controller;

import java.io.IOException;
import xmlparser.XmlWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;
import user.Utente;
/**
 * 
 * Classe che contiene le funzioni necessarie gestire il menu impostazioni.
 * Sono presenti tutti i vari elementi che si trovano nell'interfaccia grafica,
 * le funzioni back necessaria a tornare alla pagina precedente, modProfile che è la funzione 
 * che punta alla modifica del profilo personale dell'utente, delProfile che lo elimina e
 * expAddressBook che esporta rubrica in un file. 
 *
 */
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
		
		Richieste xmlrubrica = new Richieste(ControllerLogin.userlog.getId(), "xmlrubrica");
		
		
		//XMLPARSER
		try {
			RubricaTelefonica.versoServer.writeObject(xmlrubrica);
			boolean ok = (boolean) RubricaTelefonica.dalServer.readObject();
			if(ok)
			{
				System.out.println("ESPORTAZIONE CONTATTI XML EFFETTUATA");
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
