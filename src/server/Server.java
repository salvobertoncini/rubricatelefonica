package server;
//Importo i package
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

import db.DbConnection;
import gruppo.Gruppo;
import com.mysql.jdbc.ResultSet;
import controller.ControllerLogin;
import request.Richieste;
import request.Risposte;
import rubricaTelefonia.RubricaTelefonica;
import user.Utente;

import java.io.*;

   //Creazione di una classe per il Multrithreading
   class ServerThread extends Thread 
   {
     Socket socket;
     ObjectInputStream dalClient;
     ObjectOutputStream versoClient;
     
     ServerThread (Socket socket) 
     {
         this.socket = socket;
     }

     //esecuzione del Thread sul Socket
     public void run()
     {
    	 try
    	   {
    	    dalClient = new ObjectInputStream(socket.getInputStream());
    	    versoClient = new ObjectOutputStream(socket.getOutputStream());
    	   
    	    while(true)
    	    {
    	    	try 
    	    	{
					Richieste r = (Richieste) dalClient.readObject();
					System.out.println(r.getUsername());
					System.out.println(r.getPassword());
					System.out.println(r.getTipoRichiesta());
					if(r.getTipoRichiesta().equals("login"))
					{
						String username = r.getUsername();
						String password = r.getPassword();
						String query = "SELECT * FROM user WHERE username_user =\""+username+"\" AND password_user=\""+password+"\"";
						String dbUsername, dbPassword, dbNome, dbCognome, dbTelefono, dbEmail;
						int dbId;
					       try {
					    	DbConnection.Connetti();
					    	DbConnection.cmd.executeQuery(query);
							ResultSet rs = (ResultSet) DbConnection.cmd.getResultSet();
							if(rs.next()){
					            dbUsername = rs.getString("username_user"); System.out.println("User: "+dbUsername);
					            dbPassword = rs.getString("password_user"); System.out.println("Password: "+dbPassword);
					            dbNome = rs.getString("name_user"); 
					            dbCognome = rs.getString("surname_user");
					            dbTelefono = rs.getString("telnumber_user");
					            dbEmail = rs.getString("email_user");
					            dbId = rs.getInt("id_user");
						      
					            if(dbPassword.equals(password)){
									Utente utente = new Utente(dbNome, dbCognome, dbUsername, dbPassword, dbTelefono, dbEmail, dbId, true);
									versoClient.writeObject(utente);
					            }
					            
					            
					        }
							else
				            {
				            	Utente utente = new Utente(false);
								versoClient.writeObject(utente);

				            }
					    } catch (SQLException e) {
					    	
							e.printStackTrace();
							}
					    finally{
					    	DbConnection.disconnetti();
					    }
					    
					}
					
					else if(r.getTipoRichiesta().equals("initListViewNameGroup")){
						int idRubrica=0;
						ArrayList<Utente> al = new ArrayList<Utente>();
						int id;
						int id_gruppo=0;
						String nome, cognome, telefono, telefono2, email, email2, note;
						String query2 = "SELECT id_addressbook FROM addressbook WHERE id_user="+r.getIdLoggato();
						System.out.println(query2);
						DbConnection.Connetti();
						String query4="SELECT id_group FROM `group` WHERE name_group='"+r.getOggettoRicerca()+"'";
						System.out.println(query4);
						DbConnection.cmd.executeQuery(query4);
						ResultSet rs4 = (ResultSet) DbConnection.cmd.getResultSet();
						if(rs4.next())
						{
							id_gruppo=rs4.getInt("id_group");
						}
						DbConnection.cmd.executeQuery(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						
						if(rs2.next())
						{
							idRubrica=rs2.getInt("id_addressbook");
							
							String query="SELECT people.id_people as idpeople,people.name_people,people.surname_people,people.telnumber1_people,people.telnumber2_people,people.email1_people,people.email2_people,people.note_people FROM people,contact WHERE people.id_people=contact.id_people AND contact.id_addressbook="+idRubrica+" AND contact.id_group="+id_gruppo;
							System.out.println(query);
							DbConnection.cmd.executeQuery(query);
							ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
							Utente u;
							while(rs3.next())
							{
								id=rs3.getInt("idpeople");
								nome=rs3.getString("name_people");
								cognome=rs3.getString("surname_people");
								telefono=rs3.getString("telnumber1_people");
								telefono2=rs3.getString("telnumber2_people");
								email=rs3.getString("email1_people");
								email2=rs3.getString("email2_people");
								note=rs3.getString("note_people");
								
								u = new Utente(nome, cognome, telefono, telefono2, email, email2, note, id);
								if(u.getId()==1){}
								else{
								al.add(u);
								}
							}
							
							versoClient.writeObject(al);
						}
						else
						{
							versoClient.writeObject(al);
						}							

					}
					else if(r.getTipoRichiesta().equals("aggiungiGruppo")){
						
						int idLoggato=r.getIdLoggato();
						String query="SELECT id_addressbook FROM addressbook WHERE addressbook.id_user="+idLoggato;
						String query1="SELECT * FROM `group` WHERE name_group=\""+r.getNome()+"\"";
						String query2="INSERT INTO `group`  (id_group, name_group) VALUES (null, \""+r.getNome()+"\")";
						boolean gruppoAggiunto=false;
						int id_addressbook=0;
						int id_gruppo=0;
						
							DbConnection.Connetti();
							DbConnection.cmd.executeQuery(query);
							ResultSet rs0 = (ResultSet) DbConnection.cmd.getResultSet();
							
							if((rs0.next())){
								id_addressbook=rs0.getInt("id_addressbook");
							}	

					    	DbConnection.cmd.executeQuery(query1);
							ResultSet rs1 = (ResultSet) DbConnection.cmd.getResultSet();
							
							if((rs1.next())){
								gruppoAggiunto=true;
				            	versoClient.writeObject(gruppoAggiunto);

							}	
							else{
								DbConnection.cmd.executeUpdate(query2);
								ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
								String query4="SELECT id_group FROM `group` WHERE name_group='"+r.getNome()+"'";
								DbConnection.cmd.executeQuery(query4);
								ResultSet rs5 = (ResultSet) DbConnection.cmd.getResultSet();
								if(rs5.next())
								{
									id_gruppo=rs5.getInt("id_group");
									String query3="INSERT INTO contact (id_contact,id_people,id_addressbook,id_group)VALUES(null,1,"+id_addressbook+","+id_gruppo+")";
									DbConnection.cmd.executeUpdate(query3);

								}

								versoClient.writeObject(gruppoAggiunto);

							}


					}
					else if(r.getTipoRichiesta().equals("registrazione")){
						String query1="SELECT * FROM `user` WHERE username_user=\""+r.getUsername()+"\" AND email_user=\""+r.getEmail()+"\"";
						String query2="INSERT INTO `user`  (id_user, name_user, surname_user, username_user, password_user, telnumber_user, email_user) VALUES (null, \""+r.getNome()+"\", \""+r.getCognome()+"\", \""+r.getUsername()+"\", \""+r.getPassword()+"\", \""+r.getTelefono()+"\", \""+r.getEmail()+"\")";
						String query3="SELECT `id_user` FROM `user` WHERE username_user='"+r.getUsername()+"'";
						boolean userAggiunto=false;
						int id_user =0;
						
							DbConnection.Connetti();
					    	System.out.println(query1);
					    	System.out.println(DbConnection.cmd);

					    	DbConnection.cmd.executeQuery(query1);
							ResultSet rs1 = (ResultSet) DbConnection.cmd.getResultSet();
							
							if((rs1.next())){
								userAggiunto=true;
				            	versoClient.writeObject(userAggiunto);

							}	
							else{
								DbConnection.cmd.executeUpdate(query2);
								ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
				            	versoClient.writeObject(userAggiunto);
				            	DbConnection.cmd.executeQuery(query3);
								ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
								if(rs3.next()){
									id_user=rs3.getInt("id_user");
									String query4="INSERT INTO addressbook (id_addressbook,id_user) VALUES (null,'"+id_user+"')";
					            	DbConnection.cmd.executeUpdate(query4);
					            	String query7="SELECT id_addressbook FROM addressbook WHERE addressbook.id_user="+id_user+"";
					            	DbConnection.cmd.executeQuery(query7);
					            	ResultSet rs7 = (ResultSet) DbConnection.cmd.getResultSet();
									if(rs7.next()){
										String idRubrica=rs7.getString("id_addressbook");
										String query5="INSERT INTO contact (id_contact, id_people, id_addressbook, id_group) VALUES (null, 1,"+idRubrica+" ,1)";
						            	String query6="INSERT INTO contact (id_contact, id_people, id_addressbook, id_group) VALUES (null, 1,"+idRubrica+" ,2)";
						            	DbConnection.cmd.executeUpdate(query5);
						            	DbConnection.cmd.executeUpdate(query6);

									
									}
					            		}
								

							}

					}
					else if(r.getTipoRichiesta().equals("modUser")){
						
						String query2="UPDATE `user` SET name_user='"+r.getNome()+"', surname_user='"+r.getCognome()+"', username_user='"+r.getUsername()+"', password_user='"+r.getPassword()+"', telnumber_user='"+r.getTelefono()+"', email_user='"+r.getEmail()+"' WHERE id_user="+r.getIdLoggato();
						System.out.println(query2);
						boolean userAggiunto=false;
						int id_user =0;
					
						DbConnection.Connetti();
						DbConnection.cmd.executeUpdate(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						userAggiunto=true;
		            	versoClient.writeObject(userAggiunto);

					}
					else if(r.getTipoRichiesta().equals("modContact")){
						
						String query2="UPDATE `people` SET name_people='"+r.getNome()+"', surname_people='"+r.getCognome()+"', telnumber1_people='"+r.getTelefono()+"', telnumber2_people='"+r.getTelefono2()+"', email1_people='"+r.getEmail()+"', email2_people='"+r.getEmail2()+"', note_people='"+r.getNote()+"' WHERE id_people="+r.getIdLoggato();
						System.out.println(query2);
						boolean userAggiunto=false;
						int id_user =0;
					
						DbConnection.Connetti();
						DbConnection.cmd.executeUpdate(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						userAggiunto=true;
		            	versoClient.writeObject(userAggiunto);
						
					if(!(r.getValcombobox().equals("sposta contatto in..."))){
						String query10="SELECT id_group FROM `group` WHERE name_group='"+r.getValcombobox()+"'";
						DbConnection.cmd.executeQuery(query10);
						ResultSet rs10 = (ResultSet) DbConnection.cmd.getResultSet();
						if(rs10.next())
						{
							int id_group=rs10.getInt("id_group");
							String query11="UPDATE contact SET id_group="+id_group+" WHERE contact.id_people="+r.getIdLoggato()+"";
							DbConnection.cmd.executeUpdate(query11);

						}
					}
					}
					else if(r.getTipoRichiesta().equals("deleteContact")){
						
						String query2="DELETE FROM `people`WHERE id_people="+r.getIdLoggato();
						System.out.println(query2);
						boolean userAggiunto=false;
						int id_user =0;
					
						DbConnection.Connetti();
						DbConnection.cmd.executeUpdate(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						userAggiunto=true;
		            	versoClient.writeObject(userAggiunto);

					}
						else if(r.getTipoRichiesta().equals("initListView")){
						String query="SELECT id_addressbook FROM addressbook WHERE addressbook.id_user="+r.getIdLoggato()+"";
						System.out.println(query);
						boolean userAggiunto=false;
						int id_addressbook =0;
						String nome_gruppo="";
						ArrayList<Gruppo> listgruppi = new ArrayList<Gruppo>();
					
						DbConnection.Connetti();
						DbConnection.cmd.executeQuery(query);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						if(rs2.next())
						{
							id_addressbook=rs2.getInt("id_addressbook");
							String query2="SELECT DISTINCT `group`.name_group FROM `group`,contact WHERE contact.id_addressbook="+id_addressbook+" AND contact.id_group=`group`.id_group";
							System.out.println(query2);
							DbConnection.cmd.executeQuery(query2);
							ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
							while(rs3.next())
							{
								nome_gruppo=rs3.getString("name_group");
								Gruppo gruppo= new Gruppo(nome_gruppo,false);
								listgruppi.add(gruppo);
								
							}
						}
		            	versoClient.writeObject(listgruppi);

					}

					else if(r.getTipoRichiesta().equals("ricercaContatto")){
					      
					      String oggettoRicerca2=r.getOggettoRicerca();
					      int idLoggato2=r.getIdLoggato();
					      ArrayList<Utente> clientList = new ArrayList<Utente>();
					      int idRubrica=0;
					      ArrayList<Utente> al = new ArrayList<Utente>();
					      int id1;
					      String nome1, cognome1, telefono1, telefono21, email1, email21, note1;
					      String query2 = "SELECT id_addressbook FROM addressbook WHERE id_user="+r.getIdLoggato();
					      System.out.println(query2);
					      DbConnection.Connetti();
					      DbConnection.cmd.executeQuery(query2);
					      ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
					      if(rs2.next())
					      {
					       idRubrica=rs2.getInt("id_addressbook");
					       String queryRicerca="SELECT DISTINCT people.* FROM `people`,contact WHERE (`name_people` LIKE '%"+oggettoRicerca2+"%' OR `surname_people` LIKE '%"+oggettoRicerca2+"%' OR telnumber1_people LIKE '%"+oggettoRicerca2+"%' OR `telnumber2_people` LIKE '%"+oggettoRicerca2+"%' OR `email1_people` LIKE '%"+oggettoRicerca2+"%' OR `email2_people` LIKE '%"+oggettoRicerca2+"%' OR `note_people` LIKE '%"+oggettoRicerca2+"%') AND people.id_people=contact.id_people AND contact.id_addressbook="+idRubrica;
					       System.out.println(queryRicerca);
					       DbConnection.cmd.executeQuery(queryRicerca);
					       ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
					       while(rs3.next())
					       {
					        id1=rs3.getInt("id_people");
					        nome1=rs3.getString("name_people");
					        cognome1=rs3.getString("surname_people");
					        telefono1=rs3.getString("telnumber1_people");
					        telefono21=rs3.getString("telnumber2_people");
					        email1=rs3.getString("email1_people");
					        email21=rs3.getString("email2_people");
					        note1=rs3.getString("note_people");
					       
					       Utente u = new Utente(nome1, cognome1, telefono1, telefono21, email1, email21, note1, id1);
					       al.add(u);
					      }
					      
					      versoClient.writeObject(al);
					      }else
					      {
					       versoClient.writeObject(al);

					      }

					     }
					else if(r.getTipoRichiesta().equals("riempicombobox")){
						int idRubrica=0;
						
					
						ArrayList<String> al = new ArrayList<String>();
					    String query = "SELECT id_addressbook FROM addressbook WHERE id_user="+r.getIdLoggato();
					    System.out.println(query);
					      DbConnection.Connetti();
					      DbConnection.cmd.executeQuery(query);
					      ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
					      if(rs2.next())
					      {
					       idRubrica=rs2.getInt("id_addressbook");
					       String query2="SELECT DISTINCT name_group FROM `group`,contact WHERE `contact`.id_addressbook = "+idRubrica+" AND `group`.id_group = contact.id_group" ;
					       System.out.println(query2);
					       DbConnection.Connetti();
						      DbConnection.cmd.executeQuery(query2);
						      ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
						      while(rs3.next()){
						    	  String nomegruppo = rs3.getString("name_group");
						    	  al.add(nomegruppo);
						      }
				            	versoClient.writeObject(al);
				            	
					      }
					}
					else if(r.getTipoRichiesta().equals("esportarubrica")){
						ArrayList<String> rubricaesportata = new ArrayList<>();
						boolean ok=false;
						int idRubrica=0;
						ArrayList<Utente> al = new ArrayList<Utente>();
						int id;
						String nome, cognome, telefono, telefono2, email, email2, note;
						String query2 = "SELECT id_addressbook FROM addressbook WHERE id_user="+r.getIdLoggato();
						System.out.println(query2);
						DbConnection.Connetti();
						DbConnection.cmd.executeQuery(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						if(rs2.next())
						{
							idRubrica=rs2.getInt("id_addressbook");
							
							String query="SELECT people.id_people as idpeople,people.name_people,people.surname_people,people.telnumber1_people,people.telnumber2_people,people.email1_people,people.email2_people,people.note_people FROM people,contact WHERE people.id_people=contact.id_people AND contact.id_addressbook="+idRubrica;
							System.out.println(query);
							DbConnection.cmd.executeQuery(query);
							ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
							PrintWriter writer;
							while(rs3.next())
							{
								id=rs3.getInt("idpeople");
								nome=rs3.getString("name_people");
								cognome=rs3.getString("surname_people");
								telefono=rs3.getString("telnumber1_people");
								telefono2=rs3.getString("telnumber2_people");
								email=rs3.getString("email1_people");
								email2=rs3.getString("email2_people");
								note=rs3.getString("note_people");
								
								String contattotemp = "NOME: "+nome+" COGNOME: "+cognome+" TELEFONO: "+telefono+" TELEFONO2: "+telefono2+" EMAIL: "+email+" EMAIL2: "+email2+" NOTE: "+note+"\r";
								
								
								if(id==1){}
								else
								{
									ok=true;
									rubricaesportata.add(contattotemp);
								 }
								}
							try 
							{
								writer = new PrintWriter("EsportazioniRubriche/"+r.getIdLoggato()+".txt", "UTF-8");
								for(int s=0; s<rubricaesportata.size();s++)
									writer.println(rubricaesportata.get(s));
								writer.close();
								System.out.println(rubricaesportata);
							}
						     catch (FileNotFoundException | UnsupportedEncodingException e) 
							{
								e.printStackTrace();
						    }
							versoClient.writeObject(ok);
						}
						else
						{
							versoClient.writeObject(ok);
						}
					}
					else if(r.getTipoRichiesta().equals("deleteUser")){
						
						String query2="DELETE FROM `user`WHERE id_user="+r.getIdLoggato();
						System.out.println(query2);
						boolean userEliminato=false;
					
						DbConnection.Connetti();
						DbConnection.cmd.executeUpdate(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						userEliminato=true;
		            	versoClient.writeObject(userEliminato);

					}
					else if(r.getTipoRichiesta().equals("aggiungiContatto")){
						String query1="SELECT * FROM `people` WHERE name_people=\""+r.getNome()+"\" AND surname_people=\""+r.getCognome()+"\"AND telnumber1_people=\""+r.getTelefono()+"\"";
						String query2="INSERT INTO `people` (id_people, name_people, surname_people, telnumber1_people, telnumber2_people, email1_people, email2_people, note_people) VALUES (null, \""+r.getNome()+"\", \""+r.getCognome()+"\", \""+r.getTelefono()+"\", \""+r.getTelefono2()+"\", \""+r.getEmail()+"\", \""+r.getEmail2()+"\", \""+r.getNote()+"\")";
						String query5="SELECT id_people FROM `people` WHERE name_people='"+r.getNome()+"' AND surname_people='"+r.getCognome()+"' AND telnumber1_people='"+r.getTelefono()+"'";
						String query4= "SELECT id_addressbook FROM addressbook WHERE id_user='"+r.getIdLoggato()+"'";
						boolean peopleAggiunto=false;
						int idRubrica=0;
						int idPeople	=0;
						
							DbConnection.Connetti();

					    	DbConnection.cmd.executeQuery(query1);
							ResultSet rs1 = (ResultSet) DbConnection.cmd.getResultSet();
							
							if((rs1.next())){
								peopleAggiunto=true;
				            	versoClient.writeObject(peopleAggiunto);

							}	
							else{
								DbConnection.cmd.executeUpdate(query2);
								ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
								DbConnection.cmd.executeQuery(query5);
								ResultSet rs5 = (ResultSet) DbConnection.cmd.getResultSet();
								if(rs5.next())
								{
									idPeople=rs5.getInt("id_people");
								}

								DbConnection.cmd.executeQuery(query4);
								ResultSet rs4 = (ResultSet) DbConnection.cmd.getResultSet();
								if(rs4.next())
								{
									idRubrica=rs4.getInt("id_addressbook");
									String query3="INSERT INTO contact (id_contact,id_people,id_addressbook,id_group) VALUES (null,"+idPeople+","+idRubrica+",1)";
									DbConnection.cmd.executeUpdate(query3);

								}
				            	versoClient.writeObject(peopleAggiunto);
							}
					}
					else if(r.getTipoRichiesta().equals("initpreferiti"))
					{
						int idRubrica=0;
						ArrayList<Utente> al = new ArrayList<Utente>();
						int id;
						String nome, cognome, telefono, telefono2, email, email2, note;
						String query2 = "SELECT id_addressbook FROM addressbook WHERE id_user="+r.getIdLoggato();
						System.out.println(query2);
						DbConnection.Connetti();
						DbConnection.cmd.executeQuery(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						if(rs2.next())
						{
							idRubrica=rs2.getInt("id_addressbook");
							
							String query="SELECT people.id_people as idpeople,people.name_people,people.surname_people,people.telnumber1_people,people.telnumber2_people,people.email1_people,people.email2_people,people.note_people FROM people,contact WHERE people.id_people=contact.id_people AND contact.id_addressbook="+idRubrica+" AND contact.id_group=2";
							System.out.println(query);
							DbConnection.cmd.executeQuery(query);
							ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
							Utente u;
							while(rs3.next())
							{
								id=rs3.getInt("idpeople");
								nome=rs3.getString("name_people");
								cognome=rs3.getString("surname_people");
								telefono=rs3.getString("telnumber1_people");
								telefono2=rs3.getString("telnumber2_people");
								email=rs3.getString("email1_people");
								email2=rs3.getString("email2_people");
								note=rs3.getString("note_people");
								
								u = new Utente(nome, cognome, telefono, telefono2, email, email2, note, id);
								if(u.getId()==1){}
								else{
								al.add(u);
								}
							}
							
							versoClient.writeObject(al);
						}
						else
						{
							versoClient.writeObject(al);
						}							

					}
					else if(r.getTipoRichiesta().equals("inittabella"))
					{
						int idRubrica=0;
						ArrayList<Utente> al = new ArrayList<Utente>();
						int id;
						String nome, cognome, telefono, telefono2, email, email2, note;
						String query2 = "SELECT id_addressbook FROM addressbook WHERE id_user="+r.getIdLoggato();
						System.out.println(query2);
						DbConnection.Connetti();
						DbConnection.cmd.executeQuery(query2);
						ResultSet rs2 = (ResultSet) DbConnection.cmd.getResultSet();
						if(rs2.next())
						{
							idRubrica=rs2.getInt("id_addressbook");
							
							String query="SELECT people.id_people as idpeople,people.name_people,people.surname_people,people.telnumber1_people,people.telnumber2_people,people.email1_people,people.email2_people,people.note_people FROM people,contact WHERE people.id_people=contact.id_people AND contact.id_addressbook="+idRubrica;
							System.out.println(query);
							DbConnection.cmd.executeQuery(query);
							ResultSet rs3 = (ResultSet) DbConnection.cmd.getResultSet();
							Utente u;
							while(rs3.next())
							{
								id=rs3.getInt("idpeople");
								nome=rs3.getString("name_people");
								cognome=rs3.getString("surname_people");
								telefono=rs3.getString("telnumber1_people");
								telefono2=rs3.getString("telnumber2_people");
								email=rs3.getString("email1_people");
								email2=rs3.getString("email2_people");
								note=rs3.getString("note_people");
								
								u = new Utente(nome, cognome, telefono, telefono2, email, email2, note, id);
								if(u.getId()==1){}
								else{
								al.add(u);
								}
							}
							
							versoClient.writeObject(al);
						}
						else
						{
							versoClient.writeObject(al);
						}							

					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
    	    }
    	    	
    	     
    	   }
    	   catch (IOException e) 
    	   {
    	    	System.out.println("IOException: " + e);
    	   }
    	    
     }
   }

   //Classe Server per attivare la Socket
   public class Server 
   {
	 
	 static Socket sock;
	 static ServerSocket ssock;

     public static void main (String[] args)  throws Exception 
     {
    	 ssock = new ServerSocket(4040);
    	 
    	 while(true)
    	 {
    		 System.out.println("START SERVER: SERVER IN ASCOLTO!!");
    		 sock=ssock.accept();
    		 
    		 ServerThread clientThread = new ServerThread(sock);
    		 clientThread.start();
    	 }
     }
   }
 
/*
RICORDARE DI MODIFICARE SEMPRE IL GRUPPO DI DESTINAZIONE NELLA MODIFICA DI UN CONTATTO 
(ANCHE SE E' LO STESSO)
*/
