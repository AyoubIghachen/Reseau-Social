package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LoginPackage.ConnectionDB;
import ProfilPackage.Commentaire;


public class DaoCommentaire {
	
	public static List<Commentaire> SelectListCommentaireByID_Publication( String ID_Publication) throws SQLException{
		List<Commentaire> ListCommentaire = new ArrayList<Commentaire>();
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
            System.out.println("Connected to MySQL server");
            String sql_commentaire ="SELECT * FROM commentaire WHERE ID_Publication = ?";
            PreparedStatement statement_commentaire = connection.prepareStatement(sql_commentaire);
            statement_commentaire.setString(1, ID_Publication);
            ResultSet rs_commentaire = statement_commentaire.executeQuery();
            while(rs_commentaire.next()) {
            	Commentaire commentaire = new Commentaire();
            	commentaire.setID_Commentaire(rs_commentaire.getString("ID_Commentaire"));
            	commentaire.setID_Publication(rs_commentaire.getString("ID_Publication"));
            	commentaire.setUsername(rs_commentaire.getString("Username"));
            	commentaire.setNom(rs_commentaire.getString("Nom"));
            	commentaire.setCommentaire(rs_commentaire.getString("Commentaire"));
            	commentaire.setDateTime(rs_commentaire.getString("DateTime"));
            	ListCommentaire.add(commentaire);
            }
            rs_commentaire.close();
            statement_commentaire.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
        return ListCommentaire;
	}

	
	public static void InsertCommentaire(String ID_Commentaire, String ID_Publication, String user, String Nom, String Commentaire, String DateTime) {
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="INSERT INTO Commentaire (ID_Commentaire,ID_Publication,Username,Nom,Commentaire,DateTime) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, ID_Commentaire);
			statement.setString(2, ID_Publication);
			statement.setString(3, user);
			statement.setString(4, Nom);
			statement.setString(5, Commentaire);
			statement.setString(6, DateTime);

			statement.executeUpdate();
			statement.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
}
