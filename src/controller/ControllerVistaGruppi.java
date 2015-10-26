package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gruppo.Gruppo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import request.Richieste;
import rubricaTelefonia.RubricaTelefonica;
import user.Utente;

public class ControllerVistaGruppi implements Initializable {
	
	static Utente utenteSelezionato;
	ObservableList<String> ol;
	ObservableList<Utente> ol1;
	ObservableList<Utente> ol2;

	ArrayList al;

	@FXML
	Text welcomen, welcomenb;
	
	@FXML
	TableView<Utente> tabella = new TableView<Utente>();
	
	@FXML
	TextField searchbar;
	
	@FXML
	TableColumn<Utente, String> nometab = new TableColumn<Utente, String>(), cognometab = new TableColumn<Utente, String>(), telefonotab = new TableColumn<Utente, String>(), telefono2tab = new TableColumn<Utente, String>(), emailtab = new TableColumn<Utente, String>(), email2tab = new TableColumn<Utente, String>(), notetab = new TableColumn<Utente, String>();
	
	@FXML
	ListView listgruppi;
	
	

	
	
	public void prendoGruppo(){
		listgruppi.setOnMouseClicked(new EventHandler<Event>(){

			@Override
			public void handle(Event event) {
				String gruppoSelezionato = (String) listgruppi.getSelectionModel().getSelectedItem();
				System.out.println(gruppoSelezionato);

				Richieste vistaGruppi = new Richieste(gruppoSelezionato, ControllerLogin.userlog.getId(), "initListViewNameGroup");
				try
				{
					RubricaTelefonica.versoServer.writeObject(vistaGruppi);
					
					ArrayList<Utente> al = (ArrayList<Utente>)RubricaTelefonica.dalServer.readObject();
					ol2=FXCollections.observableArrayList(al);

						nometab.setCellValueFactory(new PropertyValueFactory<Utente, String>("nome"));
						cognometab.setCellValueFactory(new PropertyValueFactory<Utente, String>("cognome"));		
						telefonotab.setCellValueFactory(new PropertyValueFactory<Utente, String>("telefono"));		
						telefono2tab.setCellValueFactory(new PropertyValueFactory<Utente, String>("telefono2"));		
						emailtab.setCellValueFactory(new PropertyValueFactory<Utente, String>("email"));		
						email2tab.setCellValueFactory(new PropertyValueFactory<Utente, String>("email2"));	
						notetab.setCellValueFactory(new PropertyValueFactory<Utente, String>("note"));	
						tabella.setItems(ol2);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		});
	}
	
	public void goMenu(){
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
	
	public void init(int i){
		Richieste vistaGruppi = new Richieste(i, "initListView");

		try
		{
			RubricaTelefonica.versoServer.writeObject(vistaGruppi);
			
			ArrayList<Gruppo> al = (ArrayList<Gruppo>)RubricaTelefonica.dalServer.readObject();
			ol=FXCollections.observableArrayList();

			for(int l=0;l<al.size();l++)
			{
				ol.add(al.get(l).getNome());
			}
			listgruppi.setItems(ol);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{		tabella.setEditable(true);

		welcomen.setText("rubrica di "+ControllerLogin.userlog.getNome().toUpperCase()+" "+ControllerLogin.userlog.getCognome().toUpperCase());
		welcomenb.setText("tel. "+ControllerLogin.userlog.getTelefono());
		init(ControllerLogin.userlog.getId());
		
		
	}
	
	public void logout()
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
	
	public void addPeopleForm()
	{
		try 
		{
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
	
	public void settings()
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/settingsmenu.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void search()
	 {

	  String oggettoRicerca=searchbar.getText();
	  int idLoggato=ControllerLogin.userlog.getId();

	  Richieste ricerca = new Richieste(oggettoRicerca,idLoggato,"ricercaContatto");
	  try {
	   RubricaTelefonica.versoServer.writeObject(ricerca);
	   
	   ArrayList<Utente> al = (ArrayList<Utente>)RubricaTelefonica.dalServer.readObject();
	   ol1 = FXCollections.observableArrayList(al);
	   nometab.setCellValueFactory(new PropertyValueFactory<Utente, String>("nome"));
	   cognometab.setCellValueFactory(new PropertyValueFactory<Utente, String>("cognome"));  
	   telefonotab.setCellValueFactory(new PropertyValueFactory<Utente, String>("telefono"));  
	   telefono2tab.setCellValueFactory(new PropertyValueFactory<Utente, String>("telefono2"));  
	   emailtab.setCellValueFactory(new PropertyValueFactory<Utente, String>("email"));  
	   email2tab.setCellValueFactory(new PropertyValueFactory<Utente, String>("email2")); 
	   notetab.setCellValueFactory(new PropertyValueFactory<Utente, String>("note")); 

	   
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  
	 }
	
	public void goPreferiti(){
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/vistapreferiti.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
	}
	
	@FXML
	public void prendoContatto(){
		utenteSelezionato = tabella.getSelectionModel().getSelectedItem();
		System.out.println(utenteSelezionato.getId());
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/modpeople.fxml"));
			Scene scene1 = new Scene(root);
        	RubricaTelefonica.Stage.setScene(scene1);
        	RubricaTelefonica.Stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void visualizzaGruppi(){
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("../graphics/vistagruppi.fxml"));
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
