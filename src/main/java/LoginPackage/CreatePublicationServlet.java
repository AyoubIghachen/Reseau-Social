package LoginPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.DaoProfil;
import DAO.DaoPublication;



@MultipartConfig
public class CreatePublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CreatePublicationServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		
		if(user==null) { // not Connected
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else { // Connected
			// Generate Image
			Part file = request.getPart("image");
			// Commentaire
			String Commentaire = request.getParameter("Commentaire");
			
			if(Commentaire != null && !Commentaire.isEmpty() && file != null && file.getSize() > 0) {
				// get selected image file name
				String imageFileName = file.getSubmittedFileName();
				// Generate Time and Date:
				DateFormat date_format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		        Date date = new Date();
				String DateTime = date_format.format(date);
				// Generate ID_Publication
				String ID_Publication = new java.rmi.server.UID().toString();
				// Acceder au Nom
				String Nom = DaoProfil.SelectNomByUsername(user);
				// Acceder PhotoProfil
				String PhotoProfil = DaoProfil.SelectPhotoProfilByUsername(user);
				// Extension file:
				String Extension = imageFileName.substring(imageFileName.lastIndexOf('.'));
				// Generate image Name:
				DateFormat date_format_imageName = new SimpleDateFormat("ddMMyyHHmmss");
			    Date date_imageName = new Date();
				String imageName = date_format_imageName.format(date_imageName);
				imageName = user + imageName + Extension;
				// Path where we have to upload our actual image
				String uploadPath = "C:/Users/Pc/eclipse-workspace/NecessaryCodeToUse/src/main/webapp/images/"+imageName;
				// uploading our selected image into images folder
				EditProfilServlet.UploadImageInFolder(file, uploadPath);
				
				// Insert publication in DataBase
				DaoPublication.InsertPublication(ID_Publication, user, Nom, DateTime, Commentaire, imageName, PhotoProfil);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}