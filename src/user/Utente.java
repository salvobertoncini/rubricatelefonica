package user;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utente implements Serializable{
	private String nome, cognome, telefono,telefono2,email,email2,note, username, password;
	private int id;
	boolean login= false;
	
	public Utente(String nome, String cognome, String telefono, String telefono2, String email, String email2,
			String note, int id) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.telefono2 = telefono2;
		this.email = email;
		this.email2 = email2;
		this.note = note;
		this.id = id;
	}
	public Utente() {
	}
	

	
	public Utente(String nome, String cognome, String username, String password, String telefono, String email,  int id,
			boolean login) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.username = username;
		this.password = password;
		this.id = id;
		this.login = login;
	}
	public Utente(String nome, String cognome, String telefono, int id, boolean login) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.id = id;
		this.login=login;
	}
	public Utente( boolean login) {
		
		this.login=login;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException 
	 {
	   aOutputStream.writeObject(nome);
	   aOutputStream.writeObject(cognome);
	   aOutputStream.writeObject(username);
	   aOutputStream.writeObject(password);
	   aOutputStream.writeObject(telefono);
	   aOutputStream.writeObject(telefono2);
	   aOutputStream.writeObject(email);
	   aOutputStream.writeObject(email2);
	   aOutputStream.writeObject(note);
	   aOutputStream.writeObject(id);
	   aOutputStream.writeObject(login);

	 }

	 private void readObject( ObjectInputStream aInputStream) throws ClassNotFoundException, IOException 
	 {
		 nome = (String)aInputStream.readObject();
		 cognome = (String)aInputStream.readObject();
		 username = (String)aInputStream.readObject();
		 password = (String)aInputStream.readObject();
		 telefono = (String)aInputStream.readObject();
		 telefono2 = (String)aInputStream.readObject();
		 email = (String)aInputStream.readObject();
		 email2 = (String)aInputStream.readObject();
		 note = (String)aInputStream.readObject();
		 id = (int)aInputStream.readObject();
		 login = (boolean)aInputStream.readObject();
		   
	   
	 }
	
}
