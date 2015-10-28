package gruppo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Gruppo implements Serializable
{
	/**
	 * Classe che contiene i costruttori, i getters e i setters di ogni gruppo di contatti.
	 * Contiene anche gli estremi di streaming al server.
	 */
	private String nome;
	boolean avviso;

	public Gruppo(String nome, boolean avviso) {

		this.nome = nome;
		this.avviso = avviso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getAvviso() {
		return avviso;
	}

	public void setAvviso(boolean avviso) {
		this.avviso = avviso;
	}
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException 
	 {
	   aOutputStream.writeObject(nome);
	   aOutputStream.writeObject(avviso);

	 }
	 private void readObject( ObjectInputStream aInputStream) throws ClassNotFoundException, IOException 
	 {
		 nome = (String)aInputStream.readObject();
		 avviso = (boolean)aInputStream.readObject();
		   
	   
	 }
}
