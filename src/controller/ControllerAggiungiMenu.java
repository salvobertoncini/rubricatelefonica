package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import rubricaTelefonia.RubricaTelefonica;
/**
 * 
 * Classe che contiene le funzioni necessarie che puntano alle funzioni di 
 * aggiunta di un gruppo o di un contatto.
 * Sono presenti tutti i vari elementi che si trovano nell'interfaccia grafica,
 * le funzioni back necessaria a tornare alla pagina precedente,
 * addPeopleForm che si occupa di raggiungere l'interfaccia grafica deputata ad
 * aggiungere un contatto in rubrica e addGroupForm che si occupa invece di raggiungere
 * l'interfaccia grafica deputata ad aggiungere un gruppo. 
 *
 */
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
