package NewPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import DAO.DaoAimer;
import DAO.DaoProfil;



public class LikePublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public LikePublicationServlet() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		
		if(user==null) { // not Connected
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else {
			String ID_Publication = request.getParameter("ID_Publication");
			if(!DaoAimer.AlreadyLiked(ID_Publication,user)) {
				String ID_Aimer = new java.rmi.server.UID().toString();
				String Nom = DaoProfil.SelectNomByUsername(user);
				DaoAimer.InsertAimer(ID_Aimer, ID_Publication, user, Nom);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
