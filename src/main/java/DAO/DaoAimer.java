package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LoginPackage.ConnectionDB;


public class DaoAimer {
	
	public static List<String> SelectListAimerByID_Publication( String ID_Publication) throws SQLException{
		List<String> ListAimer = new ArrayList<String>();
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");

			//**** Aimer:
			String sql_aimer ="SELECT * FROM aimer WHERE ID_Publication = ?";
			PreparedStatement statement_aimer = connection.prepareStatement(sql_aimer);
			statement_aimer.setString(1, ID_Publication);
			ResultSet rs_aimer = statement_aimer.executeQuery();
			while(rs_aimer.next()) {
				ListAimer.add(rs_aimer.getString("Nom"));
			}
			rs_aimer.close();
			statement_aimer.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ListAimer;
	}
	
	
	public static void InsertAimer(String ID_Aimer, String ID_Publication, String user, String Nom) {
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="INSERT INTO Aimer (ID_Aimer,ID_Publication,Username,Nom) VALUES (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, ID_Aimer);
			statement.setString(2, ID_Publication);
			statement.setString(3, user);
			statement.setString(4, Nom);

			statement.executeUpdate();
			statement.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static boolean AlreadyLiked(String ID_Publication,String user) {
		boolean Like = false;
		Connection connection = null;
		try {
			connection = ConnectionDB.getConnection();
            System.out.println("Connected to MySQL server");
            String sql ="SELECT * FROM Aimer WHERE ID_Publication = ? AND Username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ID_Publication);
            statement.setString(2, user);
	        ResultSet rs = statement.executeQuery();
	        while(rs.next()) {
	    	   Like = true;
	        }
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Like;
	}
	
	
}
