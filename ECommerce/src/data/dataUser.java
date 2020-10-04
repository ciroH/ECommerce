package data;

import entities.*;
import java.sql.*;

public class dataUser {
	
	public User getOnLogin(User userRec) {
		User user = null;
		PreparedStatement loginStatement = null;
		ResultSet rs = null;
		try {
			loginStatement = DbConnector.getInstance().getConn().prepareStatement("select id,mail,password,name from user where mail=? and password=?");
			loginStatement.setString(1, userRec.getMail());
			loginStatement.setString(2, userRec.getPassword());
			
			rs = loginStatement.executeQuery();
			if (rs!=null && rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (loginStatement!=null) {
					loginStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return user;
	}
	
	
}
