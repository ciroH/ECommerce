package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import logic.LogicProduct;

@WebServlet("/ModifyProduct")
public class ModifyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LogicProduct logic; 
    
    public ModifyProduct() {
        super();
        logic = new LogicProduct();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Illegal Access ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product productToModify = new Product();
		Float originalPrice = 0.0f;

		if (request.getParameter("id") != null) {
		productToModify.setId(Integer.parseInt(request.getParameter("id")));
		try {
			originalPrice = logic.idSearch(productToModify.getId()).getPrice();
		} catch (SQLException e) {
			request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
		  }
		}
		if (request.getParameter("name") != null) {	//to process the form that contained modified product's fields
/* if(Integer.isNull(productToModify.getId())) */ productToModify.setId(Integer.parseInt(request.getParameter("id"))); 
			productToModify.setName(request.getParameter("name"));
			productToModify.setDescription(request.getParameter("description"));
			productToModify.setCategory(request.getParameter("category"));
			productToModify.setStock(Integer.parseInt(request.getParameter("stock")));
			productToModify.setPrice(Float.parseFloat(request.getParameter("price")));
			if(originalPrice != productToModify.getPrice()) { //"if the price that the Product had before using the form 
															  // to modify attributes isn't the same as the one after sending
															  // the form, then move the old price to the variable oldPrice 
															  // and the new price into the variable price"
				productToModify.setOldPrice(originalPrice);		//should i move this to the logic layer?
			}
			
			try {
				if (logic.modify(productToModify)) {//sends the product loaded with the modified data to the DB,
													//returns true for success, or false for data layer exception.
					request.setAttribute("trigger", "clean");
				} else {
					request.setAttribute("trigger", "errorModify");
				}
			} catch (SQLException e) {
				request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
				e.printStackTrace();
			}	
			
	    }else if (request.getParameter("id") != null) {	//if the request has no "name" Attribute, but it
	    												// has an "id" Attribute, it means that the user didn't sent
	    												// the form with the modified product, but rather he sent a request by
	    												// clicking on the modify button of an specific row of the Products table.
			try {
	    	request.setAttribute("originalProduct",logic.idSearch(productToModify.getId())); //<-- to show in ManageProduct the form with the fields already filled with the original values, i have to forward the Product obtained from the DB in order to show it in the form.
			request.setAttribute("trigger", "showModify");	//i'll have to use a third type of attribute for the use case modify, because the attribute "modify" already shows the button to open the modify "form" in each row
			} catch (SQLException e){
				request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
			}
			
	    }else {	//show the "modify" buttons in each row of the Products table, basically forwarding the trigger Attribute "modify"
			request.setAttribute("trigger", "modify");
	    	
	    }
		
		try {
			forwardToManageProduct(request, response);
		} catch (SQLException | ServletException | IOException e) {
			request.setAttribute("warning", e.getMessage());
		}
	}

	
	public void forwardToManageProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		LinkedList<Product> productList = logic.showAll();
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
	}

}