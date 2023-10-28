package LoginPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import ProfilPackage.*;
import DAO.*;



public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProfilServlet() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		
		if(user==null) { // not Connected
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else { // Connected
			Profil profil =  DaoProfil.SelectProfilByUsername(user);
			
			request.setAttribute("profil", profil);
			this.getServletContext().getRequestDispatcher("/Profil.jsp" ).forward(request, response);
		}
	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

