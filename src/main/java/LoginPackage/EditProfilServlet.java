package LoginPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.DaoProfil;


@MultipartConfig
public class EditProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditProfilServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		
		if(user==null) { // not Connected
			response.sendRedirect("/ReseauSocialE2/index.html");
		}else { // Connected
			String Username = request.getParameter("Username");
			String Nom = request.getParameter("Nom");
			String Description = request.getParameter("Description");
			String Loisirs = request.getParameter("Loisirs");
			String Ville = request.getParameter("Ville");
			// Update data to the database
			DaoProfil.UpdateDataProfil_ByUsername(Username, Nom, Description, Loisirs, Ville);
			
			
			Part file = request.getPart("image");
			// save the new image to the database
			if (file != null && file.getSize() > 0) {
				String imageFileName = file.getSubmittedFileName();
				// Extension file:
				String Extension = imageFileName.substring(imageFileName.lastIndexOf('.'));
				// Generate image Name = user + date + extension:
				DateFormat date_format = new SimpleDateFormat("ddMMyyHHmmss");
				Date date = new Date();
				String imageName = date_format.format(date);
				imageName = user + imageName + Extension;
				// Path where we have to upload our actual image
				String uploadPath = "C:/Users/Pc/eclipse-workspace/NecessaryCodeToUse/src/main/webapp/images/"+imageName;
				// uploading the selected image into images folder
				UploadImageInFolder(file,uploadPath);

				DaoProfil.UpdatePhotoProfil(user, imageName);
			}
			
			this.getServletContext().getRequestDispatcher("/ProfilServlet" ).forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
	
	public static void UploadImageInFolder(Part file, String uploadPath) {
		try {
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}