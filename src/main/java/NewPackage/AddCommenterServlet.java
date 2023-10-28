package NewPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.DaoCommentaire;
import DAO.DaoProfil;


public class AddCommenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddCommenterServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		
		if(user==null) { // not Connected
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else { // Connected
			// Commentaire
			String Commentaire = request.getParameter("Commentaire");
			if(!Commentaire.equals("")) {
				String ID_Publication = request.getParameter("ID_Publication");
				
				// Generate Time and Date:
				DateFormat date_format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date date = new Date();
				String DateTime = date_format.format(date);

				// Generate ID_Commentaire
				String ID_Commentaire = new java.rmi.server.UID().toString();
				
				// Acceder au Nom
				String Nom = DaoProfil.SelectNomByUsername(user);
				
				// Upload data in DataBase
				DaoCommentaire.InsertCommentaire(ID_Commentaire, ID_Publication, user, Nom, Commentaire, DateTime);
			}
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
