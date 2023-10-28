package LoginPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.DaoProfil;
import ProfilPackage.Profil;


public class OtherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public OtherProfilServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");

		if(user==null) {// Not connected
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else {
			String otherUser = request.getParameter("otherUser");

			Profil profil =  DaoProfil.SelectProfilByUsername(otherUser);
			request.setAttribute("profil", profil);
			this.getServletContext().getRequestDispatcher("/OtherProfil.jsp" ).forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
