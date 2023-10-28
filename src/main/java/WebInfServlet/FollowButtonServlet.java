package WebInfServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.DaoSuivre;



public class FollowButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public FollowButtonServlet() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		
		if(user==null) {
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else { // Connected
			String userId = request.getParameter("userId");
			boolean followState = Boolean.parseBoolean(request.getParameter("followState"));
			
			DaoSuivre.UpdateFollowState(userId, user, followState);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
