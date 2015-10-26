package request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.control.TextField;

public class Richieste implements Serializable
{
	private String valcombobox, oggettoRicerca, nome, cognome, username, password, telefono, telefono2, email, email2,note,tipoRichiesta;
	public String getOggettoRicerca() {
		return oggettoRicerca;
	}

	public void setOggettoRicerca(String oggettoRicerca) {
		this.oggettoRicerca = oggettoRicerca;
	}

	private int idLoggato;
	public Richieste(String nome, String cognome, String telefono, String telefono2, String email, String email2,
			String note, String tipoRichiesta,int idLoggato, String valcombobox) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.telefono2 = telefono2;
		this.email = email;
		this.email2 = email2;
		this.note = note;
		this.tipoRichiesta = tipoRichiesta;
		this.idLoggato = idLoggato;
		this.valcombobox = valcombobox;

	}

	public String getValcombobox() {
		return valcombobox;
	}

	public void setValcombobox(String valcombobox) {
		this.valcombobox = valcombobox;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
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

	public Richieste(String username, String password,String tipoRichiesta) 
	{
		this.username = username;
		this.password = password;
		this.tipoRichiesta = tipoRichiesta;

	}

	public Richieste(String nome,String tipoRichiesta )
	{
		this.tipoRichiesta=tipoRichiesta;
		this.nome=nome;
	}

	

	public Richieste(String nome, String cognome, String username, String password, String telefono, String email,
			String tipoRichiesta,int idLoggato) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.telefono = telefono;
		this.email = email;
		this.tipoRichiesta = tipoRichiesta;
		this.idLoggato = idLoggato;

	}

	public Richieste(int i, String tipoRichiesta) {
		this.idLoggato = i;
		this.tipoRichiesta = tipoRichiesta;
	}

	public Richieste(String tipoRichiesta, int id) {
		this.idLoggato = id;
		this.tipoRichiesta = tipoRichiesta;
	}

	
	public Richieste(String oggettoRicerca, int idLoggato,String tipoRichiesta) {
		  this.oggettoRicerca = oggettoRicerca;
		  this.idLoggato = idLoggato;
		  this.tipoRichiesta=tipoRichiesta;
		 }
	
	public String getTipoRichiesta() {
		return tipoRichiesta;
	}

	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
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
	   aOutputStream.writeObject(tipoRichiesta);
	   aOutputStream.writeObject(idLoggato);
	   aOutputStream.writeObject(oggettoRicerca);
	   aOutputStream.writeObject(valcombobox);




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
		 tipoRichiesta = (String)aInputStream.readObject();   
		 idLoggato = (int)aInputStream.readObject();   
		 oggettoRicerca = (String)aInputStream.readObject();   
		 valcombobox = (String)aInputStream.readObject();   


	 }

	public Richieste(String nome, String tipoRichiesta, int idLoggato) {
		this.nome = nome;
		this.tipoRichiesta = tipoRichiesta;
		this.idLoggato = idLoggato;
	}

	public int getIdLoggato() {
		return idLoggato;
	}

	public void setIdLoggato(int idLoggato) {
		this.idLoggato = idLoggato;
	}
}
