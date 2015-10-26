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
