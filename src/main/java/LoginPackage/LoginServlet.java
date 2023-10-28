package LoginPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.DaoUsers;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		boolean isRegistred = DaoUsers.isRegistred(Username, Password);
		
		if(isRegistred) { // Connected
			HttpSession session = request.getSession();
			session.setAttribute("user", Username);
			//session.setMaxInactiveInterval(30);
			
			response.sendRedirect("/ReseauSocialE2/AcceuilServlet");
		}else { // not Connected
			response.sendRedirect("/ReseauSocialE2/index.html");
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
