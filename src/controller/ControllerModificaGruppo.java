package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import rubricaTelefonia.RubricaTelefonica;

public class ControllerModificaGruppo 
{
	/**
	 * 
	 * Classe che contiene le funzioni necessarie ad modificare un gruppo in rubrica.
	 * Sono presenti tutti i vari elementi che si trovano nell'interfaccia grafica,
	 * le funzioni back necessaria a tornare alla pagina precedente e
	 * modGroupOnDb che si occupa di mandare una richiesta al server contenente i campi 
	 * prelevati dall'interfaccia grafica. 
	 *
	 */
	@FXML
	TextField name;
	
	public void back()
	{
		try 
		{
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
	
	public void modGroupOnDb()
	{
		String nome;
		nome = name.getText();
		
		System.out.println("REGISTRO NEL DATABASE");
		System.out.println(nome);
	}
}
