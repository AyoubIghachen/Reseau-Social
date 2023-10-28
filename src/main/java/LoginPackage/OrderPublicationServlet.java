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



public class OrderPublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public OrderPublicationServlet() {
        super();
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();
    	String user = (String)session.getAttribute("user");

    	if(user==null) { // not Connected
    		response.sendRedirect("/ReseauSocialE2/index.html");
    	}else { // Connected
    		String[] OrderBy = request.getParameterValues("OrderBy");
    		if(OrderBy.length == 1) {
    			List<Publication> ListPublication;
    			if(OrderBy[0].equals("Last")) {//récentes
    				ListPublication = DaoPublication.SelectListPublication_OrderBy_Last();
    			}else{//populaires
    				ListPublication = DaoPublication.SelectListPublication_OrderBy_Populaire();
    			}
    			request.setAttribute("ListPublication", ListPublication);
    			this.getServletContext().getRequestDispatcher("/OrderPublication.jsp").forward(request, response);
    		}else if(OrderBy.length == 2) {//récentes + populaires
    			List<Publication> ListPublication = DaoPublication.SelectListPublication_OrderBy_Last_Populaire();
    			request.setAttribute("ListPublication", ListPublication);
    			this.getServletContext().getRequestDispatcher("/OrderPublication.jsp").forward(request, response);
    		}else{
    			this.getServletContext().getRequestDispatcher("/Acceuil.jsp").forward(request, response);
    		}
    	}
    }
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
