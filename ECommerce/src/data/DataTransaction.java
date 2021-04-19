package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import entities.Transaction;

public class DataTransaction {

 public	boolean saveTransaction(Transaction transaction){
		PreparedStatement savePstmt = null;
		try {
				savePstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO transaction (serverdate,total) VALUES (?,?) ");
				savePstmt.setDate(1,transaction.getServerDate() );
				savePstmt.setFloat(2, transaction.getTotal());
				savePstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (savePstmt!=null) {
					savePstmt.close();
				}
				DbConnector.getInstance().releaseConn();
					
			} catch (Exception e2) {
				e2.printStackTrace();
				return(false);
			}
		}
			return true;
		}
	public boolean saveDetails(Transaction transaction) {
		PreparedStatement detailsPstmt = null;
		
		try {

				detailsPstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO transdetails (id_user,id_address,id_card,itemdetail) VALUES (?,?,?,?)");
				detailsPstmt.setInt(1, transaction.getIdUser() );
				detailsPstmt.setInt(2, transaction.getIdAddress());
				detailsPstmt.setInt(3, transaction.getIdCard());
				detailsPstmt.setString(4, transaction.getDetail());
				detailsPstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			
			try {
				if (detailsPstmt!=null) {
					detailsPstmt.close();
				}
				DbConnector.getInstance().releaseConn();
					
			} catch (Exception e2) {
				e2.printStackTrace();
				return(false);
			}
		}
			return true;
				
	}
	
	
	
	
}
