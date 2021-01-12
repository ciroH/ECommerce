package servlets;

import entities.User;
import logic.LogicUser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private LogicUser logic;
  
    public LogIn() {
        super();
       	logic = new LogicUser();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    User userLogIn = new User();
		userLogIn.setMail(request.getParameter("inputEmail"));
		userLogIn.setPassword(request.getParameter("inputPassword"));
		userLogIn = logic.processLogIn(userLogIn);
		response.getWriter().append("id: ").append(String.valueOf(userLogIn.getId()))
							.append("\n name: ").append(userLogIn.getName());
		
	}

}
