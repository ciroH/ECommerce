package servlets;

import entities.User;
import data.dataUser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private dataUser du;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
       	du = new dataUser();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    User userLogIn = new User();
		userLogIn.setMail(request.getParameter("inputEmail"));
		userLogIn.setPassword(request.getParameter("inputPassword"));
		userLogIn = du.getOnLogin(userLogIn);
		response.getWriter().append("id: ").append(String.valueOf(userLogIn.getId()))
							.append("\n name: ").append(userLogIn.getName());
		
	/*	String user = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");
		response.getWriter().append("Email: ").append(user).append("; Password: ").append(password).append("at: ").append(request.getContextPath());
		//		doGet(request, response);
	*/
	}

}
