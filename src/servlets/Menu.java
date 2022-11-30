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


@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LogicProduct logic;

    public Menu() {
        super();
        logic = new LogicProduct();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Illegal Access");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getAttribute("option")!=null && request.getAttribute("option").equals("products")) {  //maybe replace it later with a switch statement for all the requests (with Attributes loaded) forwarded from other servlets
			LinkedList<Product> productList = new LinkedList<>();
			try {
				productList = logic.showAll();
			} catch (SQLException e) {
				request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
			}
			request.setAttribute("productList", productList);
			request.setAttribute("trigger", "clean");
			request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
		}
		
		switch (request.getParameter("option")) {
		case "index":
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			break;
		case "products":
			LinkedList<Product> productList = new LinkedList<>();
			try {
				productList = logic.showAll();
			} catch (SQLException e) {
				request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
			}
			request.setAttribute("productList", productList);
			request.setAttribute("trigger", "clean");
			request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
			break;
		case "productLog":
			
			break;
		case "transactions":
			
			break;
		case "userList":
			
			break;
		case "menu":
			request.getRequestDispatcher("WEB-INF/indexAdmin.jsp").forward(request, response);
		default:
			request.getRequestDispatcher("WEB-INF/indexAdmin.jsp").forward(request, response);
			break;
		}
	}

}
