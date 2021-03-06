package db;

import java.sql.*;

public class DbConnection 
{
	/**
	 * Classe che si occupa della connessione col Database.
	 * Contiene una funzione Connetti che, una volta stabiliti di dati della connessione,
	 * permette grazie alla libreria esterna importata mysql-connector-java.bin.jar
	 * di interfacciarsi col database, e una funzione Disconnetti.
	 */
	static public Statement cmd;
	static public Connection connessione;
	static String db_name="rubricatelefonica";
	static String user_name="admin";
	static String user_pass="admin";
	static String driver= "com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost/";
	
	public static void Connetti()
	{
		try
		{
			Class.forName(driver);
			connessione = DriverManager.getConnection(url + db_name,user_name,user_pass);
			cmd = connessione.createStatement();
		}
		catch(SQLException ex1)
		{
			ex1.printStackTrace();
		}
		catch(ClassNotFoundException ex1)
		{
			ex1.printStackTrace();
		}
	}

	public static void disconnetti() {
		try {
			connessione.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	

}


