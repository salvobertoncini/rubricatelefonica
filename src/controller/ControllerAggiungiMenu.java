package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import rubricaTelefonia.RubricaTelefonica;

public class ControllerAggiungiMenu {
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
	
	public void addPeopleForm()
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/addpeople.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public void addGroupForm()
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/addgroup.fxml"));
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
