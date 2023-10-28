package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LoginPackage.ConnectionDB;
import ProfilPackage.ListProfil;
import ProfilPackage.Profil;
import ProfilPackage.Publication;



public class DaoProfil {
	
	
	 public static Profil SelectProfilByUsername(String user) {
		 Profil profil = new Profil();
		 List<Publication> ListPublication = DaoPublication.SelectListPublicationByUsername(user);

		 Connection connection = null;
		 try {
			 connection = ConnectionDB.getConnection();
			 System.out.println("Connected to MySQL server");
			 String sql ="SELECT * FROM Profil WHERE Username = ?";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, user);
			 ResultSet rs = statement.executeQuery();

			 while(rs.next()) {
				 //**** Implementation:
				 profil.setUsername(rs.getString("Username"));
				 profil.setPhotoProfil(rs.getString("PhotoProfil"));
				 profil.setNom(rs.getString("Nom"));
				 profil.setDescription(rs.getString("Description"));
				 profil.setLoisirs(rs.getString("Loisirs"));
				 profil.setVille(rs.getString("Ville"));
				 profil.setListPublication(ListPublication);
			 }
			 rs.close();
			 statement.close();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return profil;
	 }
	 
	 
	 public static void UpdatePhotoProfil(String user, String imageName) {
		 Connection connection = null;
		 // Table Profil
		 try {	
			 connection = ConnectionDB.getConnection();
			 System.out.println("Connected to MySQL server");
			 String sql ="UPDATE profil SET PhotoProfil = ? WHERE Username = ?";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, imageName);
			 statement.setString(2, user);
			 statement.executeUpdate();
			 statement.close();
			 System.out.println("PhotoProfil Updated in profil table.");
		 }catch(SQLException ex) {
			 ex.printStackTrace();
		 }
		 // Table Publication
		 try {	
			 connection = ConnectionDB.getConnection();
			 System.out.println("Connected to MySQL server");
			 String sql ="UPDATE publication SET PhotoProfil = ? WHERE Username = ?";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, imageName);
			 statement.setString(2, user);
			 statement.executeUpdate();
			 statement.close();
			 System.out.println("PhotoProfil Updated in publication table.");
		 }catch(SQLException ex) {
			 ex.printStackTrace();
		 }
	 }


	 public static String SelectNomByUsername(String user) {
		 String Nom = null;
		 Connection connection = null;
		 try {	
			 connection = ConnectionDB.getConnection();
			 System.out.println("Connected to MySQL server");
			 String sql ="SELECT Nom FROM Profil WHERE Username = ?";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, user);
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
				 Nom = rs.getString("Nom");
			 }

			 rs.close();
			 statement.close();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return Nom;
	 }
	 
	 
	 public static ListProfil SelectListProfilByTextSearch(String TextSearch, String Follower) {
		 ListProfil ListProfil = new ListProfil();
		 List<Profil> ListProfilUser = new ArrayList<Profil>();

		 Connection connection = null;
		 try {
			 connection = ConnectionDB.getConnection();
			 System.out.println("Connected to MySQL server");
			 String sql ="SELECT * FROM Profil WHERE UPPER(Nom) LIKE '%"+TextSearch+"%' OR LOWER(Nom) LIKE '%"+TextSearch+"%' OR Nom LIKE '%"+TextSearch+"%'";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 //	statement.setString(1, TextSearch);
			 ResultSet rs = statement.executeQuery();

			 while(rs.next()) {
				 Profil profil = new Profil();
				 //**** Implementation:
				 profil.setUsername(rs.getString("Username"));
				 profil.setPhotoProfil(rs.getString("PhotoProfil"));
				 profil.setNom(rs.getString("Nom"));
				 profil.setDescription(rs.getString("Description"));
				 profil.setLoisirs(rs.getString("Loisirs"));
				 profil.setVille(rs.getString("Ville"));
				 profil.setFollowState(DaoSuivre.isFollowing(rs.getString("Username"),Follower));
				 
				 ListProfilUser.add(profil);
			 }
			 ListProfil.setListProfil(ListProfilUser);

			 rs.close();
			 statement.close();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return ListProfil;
	 }
	 
	 
	 public static String SelectPhotoProfilByUsername(String user) {
		 String PhotoProfil = null;
		 Connection connection = null;
		 try {	
			 connection = ConnectionDB.getConnection();
			 System.out.println("Connected to MySQL server");
			 String sql ="SELECT PhotoProfil FROM Profil WHERE Username = ?";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, user);
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
				 PhotoProfil = rs.getString("PhotoProfil");
			 }

			 rs.close();
			 statement.close();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return PhotoProfil;
	 }
	 
	 
	 public static void UpdateDataProfil_ByUsername(String Username, String Nom, String Description, String Loisirs, String Ville){
		 Connection connection = null;
		 try {	
			 connection = ConnectionDB.getConnection();
			 System.out.println("Connected to MySQL server");
			 String sql ="UPDATE profil SET Nom = ?, Description = ?, Loisirs = ?, Ville = ? WHERE Username = ?";
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, Nom);
			 statement.setString(2, Description);
			 statement.setString(3, Loisirs);
			 statement.setString(4, Ville);
			 statement.setString(5, Username);
			 statement.executeUpdate();
			 statement.close();
			 System.out.println("Profil Updated.");
		 }catch(SQLException ex) {
			 ex.printStackTrace();
		 }
	 }
	 
	 
}
