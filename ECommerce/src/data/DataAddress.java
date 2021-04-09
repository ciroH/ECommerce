package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Address;
import entities.Product;

public class DataAddress {

	public boolean add(Address address) {
		PreparedStatement addPstmt = null;
		
		try {

			addPstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO address (street,streetnumber,city,state,country,postalcode) VALUES (?,?,?,?,?,?)");
			addPstmt.setString(1,address.getStreet());
			addPstmt.setString(2,address.getStreetNumber());
			addPstmt.setString(3,address.getCity());
			addPstmt.setString(4,address.getState());
			addPstmt.setString(5,address.getCountry());
			addPstmt.setString(6,address.getPostalCode());

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

	
/*	public LinkedList<Address> search(Address ) {	
		//for verifying if the address doesn't exist already **for this user** (searching for the address, then his id, then checking transdetail)
		
	}
*/	
	
	
}