package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import LoginPackage.ConnectionDB;


public class DaoUsers {

	public static boolean isRegistred(String Username,String Password) {
		boolean isRegistred = false;
		Connection connection = null;
		try {	
			connection = ConnectionDB.getConnection();
			System.out.println("Connected to MySQL server");
			String sql ="SELECT * FROM users WHERE Username = ? AND Password = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Username);
			statement.setString(2, Password);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				isRegistred = true;
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isRegistred;
	}


}
