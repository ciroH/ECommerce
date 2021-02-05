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
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} else {
			request.setAttribute("warning","existing"); //"warning, the email address already exist in the DataBase"
			request.getRequestDispatcher("/SignInForm.jsp").forward(request, response);
		}
		
		}else {
			//response.getWriter().append("verify Password"); 
			 request.setAttribute("warning","password"); //<-- so when the servlet forwards the request to the same jsp, that jsp verifies request.getAttribute("warning"), and if it isn't null, the jsp shows a sign asking the user with the corresponding warning
			 request.getRequestDispatcher("/SignInForm.jsp").forward(request, response);
		
		}
	}

}
