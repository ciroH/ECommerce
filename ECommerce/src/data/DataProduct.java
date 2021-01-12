package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Product;


public class DataProduct {
	public LinkedList<Product> getAll() {	//logic will group the returned List later into categories (NULL equals 'other' category)
		LinkedList<Product> productList = new LinkedList<>();
		Product item;
		PreparedStatement getAllStatement = null;
		ResultSet rs = null;
		try {
			getAllStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where stock > 0 order by id desc");
			rs = getAllStatement.executeQuery();
			
			while (rs!=null && rs.next()) {
				item = new Product();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setOldPrice(rs.getInt("oldprice"));
				item.setStock(rs.getInt("stock"));
				item.setCategory(rs.getString("category"));
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
		public LinkedList<Product> getCategory(String category) {	
		LinkedList<Product> productList = new LinkedList<>();
		Product item;
		PreparedStatement categoryStatement = null;
		ResultSet rs = null;
		try {
			categoryStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where category = ? ");
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
				item.setCategory(rs.getString("category"));
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
				searchStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where stock > 0 AND name LIKE ? order by id desc");
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
					item.setCategory(rs.getString("category"));
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
		
		public boolean add(Product product) {
			PreparedStatement addPstmt = null;
			if (!(search(product.getName()).isEmpty())) {	//felt that the "LIKE" in the SQL syntax of the method search was enought; items called "mouse" or other generic short names shouln't be used when adding new products
				return false;			//false means that there's a product already on the database with the same name or a similar one
			}
			try {

					addPstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO product (name,description,price,stock,category) VALUES (?,?,?,?,?)");
					addPstmt.setString(1, product.getName());
					addPstmt.setString(2,product.getDescription());
					addPstmt.setFloat(3,product.getPrice());
					addPstmt.setInt(4,product.getStock());
					addPstmt.setString(5, product.getCategory());
					//add photos
					addPstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				// devolver error de conexión a la db como un throwException al jsp de "insertar producto"
			} finally {
				
				try {
					if (addPstmt!=null) {
						addPstmt.close();
					}
					DbConnector.getInstance().releaseConn();
						
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
				return true;
			
		}
		
		public boolean delete(Product product) {	//if it returns true, the product is deleted from the linkedList that's on the servlet too, instead of doing another select. after that, the updated table is shown on the jsp
			PreparedStatement addPstmt = null;
			try {

					addPstmt = DbConnector.getInstance().getConn().prepareStatement("DELETE FROM product WHERE id = ?");
					addPstmt.setInt(1, product.getId());

					addPstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				// devolver error de conexión a la db como un throwException al jsp de "insertar producto"
			} finally {
				
				try {
					if (addPstmt!=null) {
						addPstmt.close();
					}
					DbConnector.getInstance().releaseConn();
						
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
				return true;	
					}
		public boolean modify(Product product) {
			PreparedStatement modPstmt = null;
			try {
				modPstmt =DbConnector.getInstance().getConn().prepareStatement("UPDATE product name=?, description=?, category=?, price=?, oldprice=?, stock=?	WHERE id  = ?");
														//usar una función en logic (se debe pasar por esa capa en caso de ejecutar un modify)
														//que mueva el valor de price a oldPrice si al comparar Product.price con el valor del field de price no hay un match.
				modPstmt.setString(1, product.getName());
				modPstmt.setString(2, product.getDescription());
				modPstmt.setString(3, product.getCategory());
				modPstmt.setFloat(4, product.getPrice());
				modPstmt.setFloat(5, product.getOldPrice());
				modPstmt.setInt(6, product.getStock());
				modPstmt.executeUpdate();

			}catch (Exception e) {
				e.printStackTrace();
					//print "vuelva a intentarlo" talvez mientras el admin miraba la tabla en el browser otro admin borro de la misma db el Product
			} finally {
				try {
					if (modPstmt !=null) {
						modPstmt.close();
					}
					DbConnector.getInstance().releaseConn();
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
			}
			return true;		
		}
		
}	//for selecting categories later, use SELECT DISTINCT category FROM product;
