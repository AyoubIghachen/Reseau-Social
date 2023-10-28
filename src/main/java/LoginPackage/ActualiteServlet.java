package LoginPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import DAO.DaoPublication;
import ProfilPackage.Publication;


public class ActualiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ActualiteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		
		if(user==null) {
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else { // Connected
			List<Publication> ListPublication = DaoPublication.SelectListPublication_Follower(user);
			
			request.setAttribute("ListPublication", ListPublication);
			this.getServletContext().getRequestDispatcher("/Actualite.jsp" ).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
