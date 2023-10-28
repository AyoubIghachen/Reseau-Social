package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LoginPackage.ConnectionDB;
import ProfilPackage.Commentaire;
import ProfilPackage.Publication;


public class DaoPublication {

	public static List<Publication> SelectListPublicationByUsername(String Username){
		List<Publication> ListPublication = new ArrayList<Publication>();
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="SELECT * FROM publication WHERE Username = ? ORDER BY DateTime DESC";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				List<String> ListAimer = DaoAimer.SelectListAimerByID_Publication(rs.getString("ID_Publication"));
				List<Commentaire> ListCommentaire = DaoCommentaire.SelectListCommentaireByID_Publication(rs.getString("ID_Publication"));

				//**** List Publication:
				Publication publication = new Publication();
				publication.setID_Publication(rs.getString("ID_Publication"));
				publication.setUsername(rs.getString("Username"));
				publication.setNom(rs.getString("Nom"));
				publication.setDateTime(rs.getString("DateTime"));
				publication.setTexte(rs.getString("Texte"));
				publication.setPhoto(rs.getString("Photo"));
				publication.setListAimer(ListAimer);
				publication.setListCommentaire(ListCommentaire);
				publication.setPhotoProfil(rs.getString("PhotoProfil"));
				ListPublication.add(publication);
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ListPublication;
	}


	public static void InsertPublication(String ID_Publication, String user, String Nom, String DateTime, String Commentaire, String imageName, String PhotoProfil) {
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="INSERT INTO publication (ID_Publication,Username,Nom,DateTime,Texte,Photo,PhotoProfil) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, ID_Publication);
			statement.setString(2, user);
			statement.setString(3, Nom);
			statement.setString(4, DateTime);
			statement.setString(5, Commentaire);
			statement.setString(6, imageName);
			statement.setString(7, PhotoProfil);

			statement.executeUpdate();
			statement.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}


	public static List<Publication> SelectListPublication_OrderBy_Last(){
		List<Publication> ListPublication = new ArrayList<Publication>();
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="SELECT p1.* FROM `publication` p1 WHERE (p1.DateTime = (SELECT MAX(p2.DateTime) FROM `publication` p2 WHERE p1.Username = p2.Username)) ORDER BY DateTime DESC";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				List<String> ListAimer = DaoAimer.SelectListAimerByID_Publication(rs.getString("ID_Publication"));
				List<Commentaire> ListCommentaire = DaoCommentaire.SelectListCommentaireByID_Publication(rs.getString("ID_Publication"));

				//**** List Publication:
				Publication publication = new Publication();
				publication.setID_Publication(rs.getString("ID_Publication"));
				publication.setUsername(rs.getString("Username"));
				publication.setNom(rs.getString("Nom"));
				publication.setDateTime(rs.getString("DateTime"));
				publication.setTexte(rs.getString("Texte"));
				publication.setPhoto(rs.getString("Photo"));
				publication.setListAimer(ListAimer);
				publication.setListCommentaire(ListCommentaire);
				publication.setPhotoProfil(rs.getString("PhotoProfil"));
				ListPublication.add(publication);
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ListPublication;
	}
	
	
	public static List<Publication> SelectListPublication_OrderBy_Populaire(){
		List<Publication> ListPublication = new ArrayList<Publication>();
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="SELECT publication.*, COUNT(DISTINCT Aimer.Username) AS likes_count, COUNT(DISTINCT Commentaire.ID_Commentaire) AS comments_count FROM publication LEFT JOIN Aimer ON Publication.ID_Publication = Aimer.ID_Publication LEFT JOIN Commentaire ON Publication.ID_Publication = Commentaire.ID_Publication GROUP BY Publication.ID_Publication,publication.Username ORDER BY likes_count DESC, comments_count DESC";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				List<String> ListAimer = DaoAimer.SelectListAimerByID_Publication(rs.getString("ID_Publication"));
				List<Commentaire> ListCommentaire = DaoCommentaire.SelectListCommentaireByID_Publication(rs.getString("ID_Publication"));

				//**** List Publication:
				Publication publication = new Publication();
				publication.setID_Publication(rs.getString("ID_Publication"));
				publication.setUsername(rs.getString("Username"));
				publication.setNom(rs.getString("Nom"));
				publication.setDateTime(rs.getString("DateTime"));
				publication.setTexte(rs.getString("Texte"));
				publication.setPhoto(rs.getString("Photo"));
				publication.setListAimer(ListAimer);
				publication.setListCommentaire(ListCommentaire);
				publication.setPhotoProfil(rs.getString("PhotoProfil"));
				ListPublication.add(publication);
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ListPublication;
	}
	
	
	public static List<Publication> SelectListPublication_OrderBy_Last_Populaire(){
		List<Publication> ListPublication = new ArrayList<Publication>();
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="SELECT p.*, COUNT(DISTINCT a.username) AS likes_count, COUNT(DISTINCT c.id_Commentaire) AS comments_count FROM Publication p LEFT JOIN Aimer a ON p.ID_Publication = a.ID_Publication LEFT JOIN Commentaire c ON p.ID_Publication = c.ID_Publication WHERE (p.Username, p.DateTime) IN (SELECT Username, MAX(DateTime) FROM Publication GROUP BY Username) GROUP BY p.Id_Publication ORDER BY likes_count DESC, comments_count DESC";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				List<String> ListAimer = DaoAimer.SelectListAimerByID_Publication(rs.getString("ID_Publication"));
				List<Commentaire> ListCommentaire = DaoCommentaire.SelectListCommentaireByID_Publication(rs.getString("ID_Publication"));

				//**** List Publication:
				Publication publication = new Publication();
				publication.setID_Publication(rs.getString("ID_Publication"));
				publication.setUsername(rs.getString("Username"));
				publication.setNom(rs.getString("Nom"));
				publication.setDateTime(rs.getString("DateTime"));
				publication.setTexte(rs.getString("Texte"));
				publication.setPhoto(rs.getString("Photo"));
				publication.setListAimer(ListAimer);
				publication.setListCommentaire(ListCommentaire);
				publication.setPhotoProfil(rs.getString("PhotoProfil"));
				ListPublication.add(publication);
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ListPublication;
	}
	
	
	public static List<Publication> SelectListPublication_Follower(String Username){
		List<Publication> ListPublication = new ArrayList<Publication>();
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="SELECT publication.* FROM publication JOIN suivre ON publication.Username = suivre.Username WHERE suivre.Follower = ? ORDER BY DateTime DESC;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				List<String> ListAimer = DaoAimer.SelectListAimerByID_Publication(rs.getString("ID_Publication"));
				List<Commentaire> ListCommentaire = DaoCommentaire.SelectListCommentaireByID_Publication(rs.getString("ID_Publication"));

				//**** List Publication:
				Publication publication = new Publication();
				publication.setID_Publication(rs.getString("ID_Publication"));
				publication.setUsername(rs.getString("Username"));
				publication.setNom(rs.getString("Nom"));
				publication.setDateTime(rs.getString("DateTime"));
				publication.setTexte(rs.getString("Texte"));
				publication.setPhoto(rs.getString("Photo"));
				publication.setListAimer(ListAimer);
				publication.setListCommentaire(ListCommentaire);
				publication.setPhotoProfil(rs.getString("PhotoProfil"));
				ListPublication.add(publication);
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ListPublication;
	}
	
	
}
