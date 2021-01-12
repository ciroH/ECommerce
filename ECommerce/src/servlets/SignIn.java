package servlets;

import entities.User;
import logic.LogicUser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
      LogicUser logic;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        logic = new LogicUser();
        // TODO Auto-generated constructor stub
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
		if (request.getParameter("inputPassword").equals(request.getParameter("inputPasswordCheck"))) {
		User userSign = new User();
		userSign.setMail(request.getParameter("inputEmail"));
		userSign.setName(request.getParameter("name"));
		userSign.setPassword(request.getParameter("inputPassword"));
		if(logic.processSignIn(userSign)) { //tries to insert the user into the DB
			
			request.getSession().setAttribute("user", logic.processLogIn(userSign)); //executes the login process, in order to save the User (Selected from the DB) into the Attribute "user" of this Session
			
			
			response.getWriter().append("success! the User was Registered");
		} else {
			response.getWriter().append("The Email is already registered");
			//response.sendRedirect(location);
		}
		
		}else {
			response.getWriter().append("verify Password"); //should be verified on the form, before sending the request.
		}
	}

}
