package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import LoginPackage.ConnectionDB;


public class DaoSuivre {
	
	public static boolean isFollowing(String Username, String Follower) {
		boolean isFollowing = false;
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			
			String sql ="SELECT * FROM suivre WHERE Username = ? AND Follower = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			statement.setString(2, Follower);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				isFollowing = true;
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isFollowing;
	}
	
	
	public static void UpdateFollowState(String userId, String Follower, boolean followState) {
		Connection connection = null;
		if(followState) {//Delete relation
			try {	
				connection = ConnectionDB.getConnection();
				System.out.println("Connected to MySQL server");
				String sql ="DELETE FROM suivre WHERE Username = ? AND Follower = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, userId);
				statement.setString(2, Follower);

				statement.executeUpdate();
				statement.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}else {//Insert relation
			try {	
				connection = ConnectionDB.getConnection();
				System.out.println("Connected to MySQL server");
				String sql ="INSERT INTO suivre (Username,Follower) VALUES (?,?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, userId);
				statement.setString(2, Follower);

				statement.executeUpdate();
				statement.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}