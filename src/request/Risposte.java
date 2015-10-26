package request;

import java.io.Serializable;

public class Risposte implements Serializable
{
	boolean login=false;
	private String nome, cognome, telefono;
	
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

	public Risposte() {
		super();
	}

	public boolean getLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
}
