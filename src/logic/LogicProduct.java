package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataProduct;
import entities.Product;

public class LogicProduct {
	DataProduct dp = new DataProduct();
	
	public LinkedList<Product> showAll() throws SQLException{
		
		LinkedList<Product> productList = new LinkedList<>();
		productList = dp.getAll();
		return productList;
	}
	
	public boolean add(Product product) throws SQLException {
		
		return dp.add(product);	
	}
	
	public boolean delete(Product product) throws SQLException {
		
		return dp.delete(product);
	}
	public Product idSearch(int id) throws SQLException {
		
		return dp.searchById(id);
	}
	
	public boolean modify(Product product) throws SQLException {
		
		return dp.modify(product);
	}
}
