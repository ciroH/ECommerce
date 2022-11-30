package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataProduct;
import entities.Product;

/**
 * Servlet implementation class SearchCategory
 */
@WebServlet("/SearchCategory")
public class SearchCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataProduct dp;
       

    public SearchCategory() {
        super();
        dp = new DataProduct();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		try {
			LinkedList<Product> productList = dp.getByCategory(category);
			request.setAttribute("categoryProducts", productList);
		} catch (SQLException e) {
			request.setAttribute("warning",e.getSQLState() + " : " + e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
