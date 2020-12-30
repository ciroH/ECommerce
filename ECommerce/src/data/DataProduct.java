package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Product;

public class DataProduct {
	public LinkedList<Product> getAll() {	
		LinkedList<Product> productList = new LinkedList<>();
		Product item;
		PreparedStatement getAllStatement = null;
		ResultSet rs = null;
		try {
			getAllStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock from product where stock > 0 order by id desc");
			rs = getAllStatement.executeQuery();
			
			while (rs!=null && rs.next()) {
				item = new Product();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setOldPrice(rs.getInt("oldprice"));
				item.setStock(rs.getInt("stock"));
				//item.setImage
				//item.setCategory
				productList.add(item);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (getAllStatement!=null) {
					getAllStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return productList;
	}
		public LinkedList<Product> getCategory(String category) {	//logic will group the returned List later into categories (NULL equals 'other' category)
		LinkedList<Product> productList = new LinkedList<>();
		Product item;
		PreparedStatement categoryStatement = null;
		ResultSet rs = null;
		try {
			categoryStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock from product where category = ? ");
			categoryStatement.setString(1, category);
			rs = categoryStatement.executeQuery();
			
			while (rs!=null && rs.next()) {
				item = new Product();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setOldPrice(rs.getInt("oldprice"));
				item.setStock(rs.getInt("stock"));
				//item.setImage
				productList.add(item);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (categoryStatement!=null) {
					categoryStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return productList;
	}
		public LinkedList<Product> search(String searchInput) {	
			LinkedList<Product> productList = new LinkedList<>();
			Product item;
			PreparedStatement searchStatement = null;
			ResultSet rs = null;
			try {
				searchStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock from product where stock > 0 AND name LIKE ? order by id desc");
				searchStatement.setString(1,"%"+searchInput+"%");	//preguntarle a Adrián porqué es "%" envez de "'%" y "%'"
				rs = searchStatement.executeQuery();

				while (rs!=null && rs.next()) {
					item = new Product();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setDescription(rs.getString("description"));
					item.setPrice(rs.getInt("price"));
					item.setOldPrice(rs.getInt("oldprice"));
					item.setStock(rs.getInt("stock"));
					//item.setImage
					productList.add(item);
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}finally {
				try {
					if (rs!=null) {
						rs.close();
					}
					if (searchStatement!=null) {
						searchStatement.close();
					}
					DbConnector.getInstance().releaseConn();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		return productList;
		}
		
		
}
