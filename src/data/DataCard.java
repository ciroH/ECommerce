package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Card;

public class DataCard {

	public boolean add(Card card) throws SQLException {
		PreparedStatement addPstmt = null;
		
		try {

			addPstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO card (number,securitycode,expdate) VALUES (?,?,?)");
			addPstmt.setString(1,card.getNumber());
			addPstmt.setInt(2, card.getSecurityCode());
			addPstmt.setDate(3, card.getDate());
			
			addPstmt.executeUpdate();
		
	} catch (SQLException e) {
		throw e;
	} finally {
		
		try {
			if (addPstmt!=null) {
				addPstmt.close();
			}
			DbConnector.getInstance().releaseConn();
				
		} catch (Exception e2) {
			throw e2;
		}
	}
		return true;
	
	}
	
	public Card getCard(Card searchedCard) throws SQLException {
		Card card = new Card();
		PreparedStatement getCardStatement = null;
		ResultSet rs = null;
		try {
			getCardStatement = DbConnector.getInstance().getConn().prepareStatement("select id,number,securitycode,expdate from card where number=? and securitycode=? ");
			getCardStatement.setString(1, searchedCard.getNumber());
			getCardStatement.setInt(2, searchedCard.getSecurityCode());
			
			rs = getCardStatement.executeQuery();
			if (rs!=null && rs.next()) {
				card.setId(rs.getInt("id"));
				card.setNumber(rs.getString("number"));
				card.setSecurityCode(rs.getInt("securitycode"));
				card.setDate(rs.getDate("expdate"));
				//card.setLogo(rs.getString("logo"));
				
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (getCardStatement!=null) {
					getCardStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				throw e2;
			}
		}
	return card;
	}
	
	/*	public LinkedList<Card> search(Card ) {	
	//for verifying if the card doesn't exist already **for this user** (searching for the card, then his id, then checking transdetail)
	
}
*/	
	
}
