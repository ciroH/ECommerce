package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Card;

public class DataCard {

	public boolean add(Card card) {
		PreparedStatement addPstmt = null;
		
		try {

			addPstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO card (number,securitycode,expdate) VALUES (?,?,?)");
			addPstmt.setString(1,card.getNumber());
			addPstmt.setInt(2, card.getSecurityCode());
			addPstmt.setDate(3, card.getDate());
			
			addPstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		
		try {
			if (addPstmt!=null) {
				addPstmt.close();
			}
			DbConnector.getInstance().releaseConn();
				
		} catch (Exception e2) {
			e2.printStackTrace();
			//return(false);
		}
	}
		return true;
	
	}
	
	/*	public LinkedList<Card> search(Card ) {	
	//for verifying if the card doesn't exist already **for this user** (searching for the card, then his id, then checking transdetail)
	
}
*/	
	
}
